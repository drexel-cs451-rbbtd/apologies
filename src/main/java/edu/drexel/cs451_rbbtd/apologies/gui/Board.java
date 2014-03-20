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

    private static List<Pawn> pawns;
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
    private JTextArea PLAYER;
    private int optSelected = 1;
    private Boolean isDeckClickable = true;
    private Boolean isPawnMovable = false;
    private ArrayList<PlayerColor> players = new ArrayList<PlayerColor>();
    private int specialSequence = 0;
    private Image drawImg = new ImageIcon(Apologies.getResourcePath("draw.png")).getImage();

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
        PLAYER = new JTextArea("");
        PLAYER.setEditable(false);
        PLAYER.setBackground(TWO_PANEL.getBackground());
        PLAYER.setForeground(Color.white);
        TWO_A.addActionListener(new buttonOneClicked());
        TWO_B.addActionListener(new buttonTwoClicked());
        TWO_A.setVisible(false);
        TWO_B.setVisible(false);
        SKIP.setVisible(false);
        SKIP.addActionListener(new skipButtonClicked());
        ButtonGroup TWO_OPTIONS = new ButtonGroup();
        TWO_OPTIONS.add(TWO_A);
        TWO_OPTIONS.add(TWO_B);
        TWO_OPTIONS.add(SKIP);
        TWO_PANEL.add(TWO_A);
        TWO_PANEL.add(TWO_B);
        TWO_PANEL.add(SKIP);
        TWO_PANEL.add(PLAYER);
        // Set current panel
        this.setLayout(new BorderLayout());
        this.add(TWO_PANEL, BorderLayout.SOUTH);


        // Initialize pawns
        pawns = new ArrayList<Pawn>();
        if (playerColors.contains(PlayerColor.YELLOW)) {
          int x1 = 130; int x2 = 170; int y1 = 50; int y2 = 90;
          pawns.add(new Pawn(x1, y2, yellowPositions, yellowPawn, PlayerColor.YELLOW));
          pawns.add(new Pawn(x2, y2, yellowPositions, yellowPawn, PlayerColor.YELLOW));
          pawns.add(new Pawn(x1, y1, yellowPositions, yellowPawn, PlayerColor.YELLOW));
          pawns.add(new Pawn(x2, y1, yellowPositions, yellowPawn, PlayerColor.YELLOW));
        }
        if (playerColors.contains(PlayerColor.GREEN)) {
          int x1 = 445; int x2 = 485; int y1 = 130; int y2 = 170;
          pawns.add(new Pawn(x1, y2, greenPositions, greenPawn, PlayerColor.GREEN));
          pawns.add(new Pawn(x2, y2, greenPositions, greenPawn, PlayerColor.GREEN));
          pawns.add(new Pawn(x1, y1, greenPositions, greenPawn, PlayerColor.GREEN));
          pawns.add(new Pawn(x2, y1, greenPositions, greenPawn, PlayerColor.GREEN));
        }
        if (playerColors.contains(PlayerColor.RED)) {
          int x1 = 365; int x2 = 405; int y1 = 440; int y2 = 480;
          pawns.add(new Pawn(x1, y2, redPositions, redPawn, PlayerColor.RED));
          pawns.add(new Pawn(x2, y2, redPositions, redPawn, PlayerColor.RED));
          pawns.add(new Pawn(x1, y1, redPositions, redPawn, PlayerColor.RED));
          pawns.add(new Pawn(x2, y1, redPositions, redPawn, PlayerColor.RED));
        }
        if (playerColors.contains(PlayerColor.BLUE)) {
          int x1 = 45; int x2 = 85; int y1 = 365; int y2 = 405;
          pawns.add(new Pawn(x1, y2, bluePositions, bluePawn, PlayerColor.BLUE));
          pawns.add(new Pawn(x2, y2, bluePositions, bluePawn, PlayerColor.BLUE));
          pawns.add(new Pawn(x1, y1, bluePositions, bluePawn, PlayerColor.BLUE));
          pawns.add(new Pawn(x2, y1, bluePositions, bluePawn, PlayerColor.BLUE));
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
        if (currentCard != null) {
            g2d.drawImage(currentCard.getImage(), currentCard.getX(), currentCard.getY(), this);
        }

        if (isDeckClickable) g2d.drawImage(drawImg, deck.getX() + 50, deck.getY() + 175, this);

        // paint selected region
        if (selectionX != 0 && selectionY != 0) {
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
        // Left Click
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {

            // Left Click pawn - check whether each pawn was at the click point
            int pawnIndexPlus1 = 0;
            for (Pawn pawn: pawns) {
                pawnIndexPlus1++;
                if (e.getX() > pawn.getX() && e.getX() < pawn.getX()+pawnClickAreaWidth
                   && e.getY() > pawn.getY() && e.getY() < pawn.getY()+pawnClickAreaHeight && isPawnMovable) {

                        // If card is an Eleven or Apologies then special sequence
                        if ((currentCard.getNumber() == 8 && optSelected == 2) ||
                            (currentCard.getNumber() == 10 && optSelected != 3)) {
                            if (specialSequence == 0) {
                                selectionX = pawn.getX() + 15;
                                selectionY = pawn.getY() + 10;
                                pawnOne = pawns.get(pawnIndexPlus1-1);
                                indexOne = pawnIndexPlus1-1;
                                pawn.errorMessage = "Select a Pawn to swap with.";
                                repaint();
                            } else if (specialSequence == 1) {
                                selectionX = pawn.getX() + 15;
                                selectionY = pawn.getY() + 10;
                                pawnTwo = pawns.get(pawnIndexPlus1-1);
                                indexTwo = pawnIndexPlus1-1;

                                // swap pawns logic
                                int space1 = pawns.get(indexOne).getSpace(); // the space of your pawn
                                int space2 = pawns.get(indexTwo).getSpace(); // the space of opponents pawns

                                int temp = pawns.get(indexOne).getIndex(pawns.get(indexTwo).getX(), pawns.get(indexTwo).getY());
                                int temp2 = pawns.get(indexTwo).getIndex(pawns.get(indexOne).getX(), pawns.get(indexOne).getY());

                                pawns.get(indexOne).moveTo(temp);
                                pawns.get(indexTwo).moveTo(temp2);

                                if (currentCard.getNumber() == 10) // if apologies, back up swapped pawn back into base
                                    pawns.get(indexTwo).moveBack(1);
                                    pawns.get(indexTwo).space = -1;
                                repaint();
                            }
                            specialSequence++;
                        }

                        if (pawn.getColor() != players.get(0)) return;

                        // Move Pawn
                        if (isPawnMovable == true) {
                            pawn.Move(currentCard.getNumber(), optSelected);
                        }

                        // Turn is over, do not show move options.
                        TWO_A.setVisible(false);
                        TWO_B.setVisible(false);
                        SKIP.setVisible(false);

                        // Print error message if applicable
                        if (pawn.getErrorMessage() != null) {
                            JOptionPane.showMessageDialog(this, pawn.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            pawn.resetErrorMessage(); // remove error message so it won't carry over to to next card
                            break;
                        }

                        //rotate the first player to the end of the list
                        PlayerColor first = players.get(0);
                        if (checkIfWon(first) == 1) gameWon(Apologies.getNames(0), first);
                        players.remove(first);
                        players.add(first);
                        Apologies.swapFirstLast();
                        PLAYER.setText(Apologies.getNames(0) + "'s Turn");
                        updateTurnLabel(players.get(0));
                        //reset the clickable flag for deck and pawn and sequence
                        isDeckClickable = true;
                        isPawnMovable = false;
                        specialSequence = 0;
                        repaint();
                }
            }

            // Left Click deck – draw card
            if (e.getX() > deck.getX() && e.getX() < deck.getX()+cardClickAreaWidth
                    && e.getY() > deck.getY() && e.getY() < deck.getY()+cardClickAreaHeight && isDeckClickable) {
                handleDeckClick();

            }

        }

        // Right Click - just for debugging purposes
        if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {

            for (Pawn pawn : pawns) {
                if (e.getX() > pawn.getX() && e.getX() < pawn.getX()+pawnClickAreaWidth
                        && e.getY() > pawn.getY() && e.getY() < pawn.getY()+pawnClickAreaHeight) {
                    handlePawnRightClick(pawn);
                }
            }
        }
    }

    private void handleDeckClick() {
        // Draw a card and paint it on the board
        currentCard = new Card(deck.drawCard());
        //make deck unclickable and make pawns clickable
        isDeckClickable = false;
        isPawnMovable = true;
        updateMoveOptions(currentCard.getNumber() + 1);
        repaint();
    }

    private void handlePawnRightClick(Pawn pawn) {
        pawn.moveForward(10);
        repaint();
    }

    public void setupPlayers(PlayerColor first, ArrayList<PlayerColor> playerColors) {
        /***************************************************************************
         * based on which color was selected to go first, generate a list of player colors
         * in the appropriate order (play must proceed clockwise from first player)
         ***************************************************************************/

        // Get the correct player order and set BG color
        for (PlayerColor p : playerColors) players.add(p);

        int swaps;
        if (first == PlayerColor.RED) swaps = 0;
        else if (first == PlayerColor.BLUE) swaps = 1;
        else if (first == PlayerColor.YELLOW) swaps = 2;
        else swaps = 3;
        updateTurnLabel(first);
        for (int i = swaps; i > 0; i--) {
            PlayerColor p = players.get(0);
            players.remove(p);
            players.add(p);
            Apologies.swapFirstLast(); }
        for (int i = 0; i < Apologies.names.size(); i++)
            if (Apologies.getNames(i).length() == 0) Apologies.names.remove(i);
        PLAYER.setText(Apologies.getNames(0) + "'s Turn");
    }

    public void updateMoveOptions(int cardNo)
    {
        TWO_A.setVisible(true);
        TWO_B.setVisible(true);
        SKIP.setVisible(true);
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

        if (text1.startsWith("forward") || text1.startsWith("back") || text1.startsWith("from")) buttonText1 = baseStr.replace("@", text1);
        else buttonText1 = text1;
        if (text2.startsWith("forward") || text2.startsWith("back") || text2.startsWith("from")) buttonText2 = baseStr.replace("@", text2);
        else buttonText2 = text2;
        TWO_A.setText(buttonText1);
        TWO_B.setText(buttonText2);

        // If there is only option, only show one button
        if (text2.equals(blank)) TWO_B.setVisible(false);
        else TWO_B.setVisible(true);
    }

    public void updateTurnLabel(PlayerColor nextPlayerCol)
    {
        PLAYER.setForeground(Color.white);
        if (nextPlayerCol == PlayerColor.RED) PLAYER.setBackground(Color.RED);
        else if (nextPlayerCol == PlayerColor.BLUE) PLAYER.setBackground(Color.BLUE);
        else if (nextPlayerCol == PlayerColor.YELLOW) { PLAYER.setBackground(Color.YELLOW); PLAYER.setForeground(Color.black); }
        else PLAYER.setBackground(Color.GREEN);
    }

    // See if all pawns of the specified color are home
    public int checkIfWon(PlayerColor col)
    {
        for (Pawn pawn: pawns) {
            if (pawn.getColor() == col)
                if (pawn.isHome == 0) return 0; }
        return 1;
    }

    public static List<Pawn> getPawns() { return pawns; }

    public void gameWon(String name, PlayerColor first) {
        Object[] options = {"Play Again with Same Settings", "Play Again with New Settings", "Quit"};
        int choice = JOptionPane.showOptionDialog(null, name + " WON!\nWhat would you like to do now?",
                "GAME WON!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        if (choice == 0) new Apologies(players, first, Apologies.names);
        else if (choice == 1) new PlayerSetup();
        else System.exit(0);
    }

    public class buttonOneClicked implements ActionListener{ public void actionPerformed(ActionEvent e) { optSelected = 1; } }
    public class buttonTwoClicked implements ActionListener{ public void actionPerformed(ActionEvent e) { optSelected = 2; } }
    public class skipButtonClicked implements ActionListener{ public void actionPerformed(ActionEvent e) { optSelected = 3; } }

    // Unused MouseListener functions
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
// end class