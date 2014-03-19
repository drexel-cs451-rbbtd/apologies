package edu.drexel.cs451_rbbtd.apologies.gui;

import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.*;

public class Board extends JPanel implements MouseListener {

    private List<Pawn> pawns;
    private Image img;
    private Deck deck;
    private Card currentCard;
    private JPanel[] Panels;
    private int selectionX = 0;
    private int selectionY = 0;
    private Positions positions = new Positions();
    private JRadioButton TWO_A;
    private JRadioButton TWO_B;
    private JRadioButton SKIP;
    private int optSelected = 1;
    private Boolean isDeckClickable = true;
    private Boolean isPawnMovable = false;
    private ArrayList<PlayerColor> players = new ArrayList<PlayerColor>();
    private int specialSequence = 0;

    // For pawn swapping logic
    private Pawn pawnOne;
    private Pawn pawnTwo;
    private int indexOne;
    private int indexTwo;

    String yellowPawn = Apologies.getResourcePath("YellowPawn.png");
    String greenPawn = Apologies.getResourcePath("GreenPawn.png");
    String redPawn = Apologies.getResourcePath("RedPawn.png");
    String bluePawn = Apologies.getResourcePath("BluePawn.png");
    Image selectionBoxImage;

//    String yellowPawn = "resources/YellowPawn.png";
//    String greenPawn = "resources/GreenPawn.png";
//    String redPawn = "resources/RedPawn.png";
//    String bluePawn = "resources/BluePawn.png";

    public Board(Image img, ArrayList<PlayerColor> playerColors, PlayerColor first) {

        // Initialize positions
        int yellowPositions[][] = positions.yellowPositions;
        int greenPositions[][] = positions.greenPositions;
        int redPositions[][] = positions.redPositions;
        int bluePositions[][] = positions.bluePositions;

        // init selectionBox image
        String selectionBox = Apologies.getResourcePath("selectionBox.png");
        ImageIcon ii = new ImageIcon(selectionBox);
        selectionBoxImage = ii.getImage();

        // Add Board base components
        this.img = img;
        addMouseListener(this);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        // Create panels
        JPanel TWO_PANEL = new JPanel();
        TWO_A = new JRadioButton("Start a Pawn");
        TWO_B = new JRadioButton("Move a pawn forward 2 spaces");
        SKIP = new JRadioButton("Skip Turn");
        TWO_A.addActionListener(new buttonOneClicked());
        TWO_B.addActionListener(new buttonTwoClicked());
        SKIP.addActionListener(new skipButtonClicked());
        ButtonGroup TWO_OPTIONS = new ButtonGroup();
        TWO_OPTIONS.add(TWO_A);
        TWO_OPTIONS.add(TWO_B);
        TWO_OPTIONS.add(SKIP);
        TWO_PANEL.add(TWO_A);
        TWO_PANEL.add(TWO_B);
        TWO_PANEL.add(SKIP);
        // Set current panel
        this.setLayout(new BorderLayout());
        this.add(TWO_PANEL, BorderLayout.SOUTH);


        // Initialize pawns
        pawns = new ArrayList<Pawn>();
        if (playerColors.contains(PlayerColor.YELLOW)) {
          pawns.add(new Pawn(125, 100, yellowPositions, yellowPawn, PlayerColor.YELLOW));
          pawns.add(new Pawn(165, 100, yellowPositions, yellowPawn, PlayerColor.YELLOW));
          pawns.add(new Pawn(125, 60, yellowPositions, yellowPawn, PlayerColor.YELLOW));
          pawns.add(new Pawn(165, 60, yellowPositions, yellowPawn, PlayerColor.YELLOW));
        }
        if (playerColors.contains(PlayerColor.GREEN)) {
          pawns.add(new Pawn(430, 170, greenPositions, greenPawn, PlayerColor.GREEN));
          pawns.add(new Pawn(470, 170, greenPositions, greenPawn, PlayerColor.GREEN));
          pawns.add(new Pawn(430, 130, greenPositions, greenPawn, PlayerColor.GREEN));
          pawns.add(new Pawn(470, 130, greenPositions, greenPawn, PlayerColor.GREEN));
        }
        if (playerColors.contains(PlayerColor.RED)) {
          pawns.add(new Pawn(365, 480, redPositions, redPawn, PlayerColor.RED));
          pawns.add(new Pawn(405, 480, redPositions, redPawn, PlayerColor.RED));
          pawns.add(new Pawn(365, 440, redPositions, redPawn, PlayerColor.RED));
          pawns.add(new Pawn(405, 440, redPositions, redPawn, PlayerColor.RED));
        }
        if (playerColors.contains(PlayerColor.BLUE)) {
          pawns.add(new Pawn(60, 415, bluePositions, bluePawn, PlayerColor.BLUE));
          pawns.add(new Pawn(100, 415, bluePositions, bluePawn, PlayerColor.BLUE));
          pawns.add(new Pawn(60, 375, bluePositions, bluePawn, PlayerColor.BLUE));
          pawns.add(new Pawn(100, 375, bluePositions, bluePawn, PlayerColor.BLUE));
        }

        setupPlayers(first, playerColors);

        deck = new Deck(165, 210);
    }

    // Draw Background Image
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    // Paint Game Objects
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        // Paint Pawns
        for (Pawn pawn : pawns) {
            g2d.drawImage(pawn.getImage(), pawn.getX(), pawn.getY(), this);
        }

        // Paint Deck
        g2d.drawImage(deck.getImage(), deck.getX(), deck.getY(), this);

        // Paint current card if not null
        if (currentCard != null){
            g2d.drawImage(currentCard.getImage(), currentCard.getX(), currentCard.getY(), this);
        }

        // paint selected region
        if (selectionX != 0 && selectionY != 0){
            g2d.drawImage(selectionBoxImage, selectionX, selectionY, this);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    // Handle mouse clicks
    public void mouseClicked(MouseEvent e) {
        final int pawnClickAreaWidth = 50;
        final int pawnClickAreaHeight = 50;
        final int cardClickAreaWidth = 120;
        final int cardClickAreaHeight = 160;
        System.out.println(optSelected);

        // Left Click
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){

            // Left Click pawn - Iterate through pawns
            int count = 0;
            for (Pawn pawn: pawns) {
                count++;
                if (e.getX() > pawn.getX() && e.getX() < pawn.getX()+pawnClickAreaWidth
                   && e.getY() > pawn.getY() && e.getY() < pawn.getY()+pawnClickAreaHeight && isPawnMovable && (pawn.getColor() == players.get(0))){

                        // If card is an eleven or sorry then swap pawns process

                        if (currentCard.getNumber() == 8 || currentCard.getNumber() == 10 && specialSequence == 1){
                            selectionX = pawn.getX() + 15;
                            selectionY = pawn.getY() + 10;
                            specialSequence++;
                            pawnTwo = pawns.get(count);
                            indexTwo = count;

                            // swap pawns logic
                            int temp = pawns.get(indexOne).getSpace();
                            pawns.get(indexOne).setSpace(pawns.get(indexTwo).getSpace());
                            pawns.get(indexTwo).setSpace(temp);
                        }

                        if (currentCard.getNumber() == 8 || currentCard.getNumber() == 10 && specialSequence == 0){
                            selectionX = pawn.getX() + 15;
                            selectionY = pawn.getY() + 10;
                            specialSequence++;
                            pawnOne = pawns.get(count);
                            indexOne = count;
                            repaint();
                            pawn.errorMessage = "Select a Pawn to swap with.";
                        }

                        // Move Pawn
                        if (isPawnMovable == true){
                            pawn.Move(currentCard.getNumber());
                        }

                        // Print error message if applicable
                        if (pawn.getErrorMessage() != null){
                            JOptionPane.showMessageDialog(this, pawn.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            pawn.resetErrorMessage(); // remove error message so it won't carry over to to next card
                            break;
                        }

                        //rotate the first player to the end of the list
                        PlayerColor first = players.get(0);
                        players.remove(first);
                        players.add(first);

                        //reset the clickable flag for deck and pawn and sequence
                        isDeckClickable = true;
                        isPawnMovable = false;
                        specialSequence = 0;
                        repaint();
                }
            }

            // Left Click deck – draw card
            if (e.getX() > deck.getX() && e.getX() < deck.getX()+cardClickAreaWidth
                    && e.getY() > deck.getY() && e.getY() < deck.getY()+cardClickAreaHeight && isDeckClickable){

                    // Draw a card and paint it on the board
                    currentCard = new Card(deck.drawCard());
                    //make deck unclickable and make pawns clickable
                    isDeckClickable = false;
                    isPawnMovable = true;
                    updateMoveOptions(currentCard.getNumber() + 1);
                    repaint();
            }

        }

        // Right Click - just for debugging purposes
        if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){

            // Iterate through pawns
            for (Pawn pawn : pawns) {
                if (e.getX() > pawn.getX() && e.getX() < pawn.getX()+pawnClickAreaWidth
                        && e.getY() > pawn.getY() && e.getY() < pawn.getY()+pawnClickAreaHeight){

                    // Move pawn back a space
                    pawn.moveForward(1);
                    repaint();
                }
            }
        }

    }

    public void setupPlayers(PlayerColor first, ArrayList<PlayerColor> playerColors) {
        /***************************************************************************
         * based on which color was selected to go first, generate a list of player colors
         * in the appropriate order (play must proceed clockwise from first player)
         ***************************************************************************/


        players.add(first);
        int index = players.indexOf(first);
        //if the the first color was selected just add list in order
        if (index == 0) {
        playerColors.remove(first);
            for (PlayerColor p : playerColors)
                players.add(p);
        }
        else {
            //if first color was not selected, add colors after it, then the
            //ones before it to get the correct order
            for (int i = index + 1; i < playerColors.size(); i++) {
                players.add(playerColors.get(i));
            }
            for (int i = 0; i < index; i++) {
                players.add(playerColors.get(i));
            }
        }

    }

    public void updateMoveOptions(int cardNo)
    {
        String text1 = "";
        String text2 = "";
        String buttonText1 = "";
        String buttonText2 = "";
        String blank = "";
        String baseStr = "Move a pawn @";
        switch (cardNo) {
            case 1: text1 = "from start"; text2 = "forward 1 space"; break;
            case 2: text1 = "from start"; text2 = "back 2 spaces"; break;
            case 3: text1 = "forward 3 spaces"; text2 = blank; break;
            case 4: text1 = "back 4 spaces"; text2 = blank; break;
            case 5: text1 = "forward 5 spaces"; text2 = blank; break;
            case 6: text1 = "forward 7 spaces"; text2 = "Split between 2 pawns"; break;
            case 7: text1 = "forward 8 spaces"; text2 = blank; break;
            case 8: text1 = "forward 10 spaces"; text2 = "back 1 space"; break;
            case 9: text1 = "forward 11 spaces"; text2 = "Switch places with opposing pawn"; break;
            case 10: text1 = "forward 12 spaces"; text2 = blank; break;
            case 11: text1 = "Move pawn from start to an opponent's square"; text2 = blank; break;
        }

        if (text1.startsWith("forward") || text1.startsWith("back")) buttonText1 = baseStr.replace("@", text1);
        else buttonText1 = text1;
        if (text2.startsWith("forward") || text2.startsWith("back")) buttonText2 = baseStr.replace("@", text2);
        else buttonText2 = text2;
        TWO_A.setText(buttonText1);
        TWO_B.setText(buttonText2);
    }

    public class buttonOneClicked implements ActionListener{ public void actionPerformed(ActionEvent e){ optSelected = 1; } }
    public class buttonTwoClicked implements ActionListener{ public void actionPerformed(ActionEvent e){ optSelected = 2; } }
    public class skipButtonClicked implements ActionListener{ public void actionPerformed(ActionEvent e){ optSelected = 3; } }

    // Unused MouseListener functions
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
// end class