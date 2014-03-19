package edu.drexel.cs451_rbbtd.apologies.gui;

import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


public class Board extends JPanel implements MouseListener {

    private List<Pawn> pawns;
    private Image img;
    private Deck deck;
    private Card currentCard;
    private JPanel[] Panels;
    private int selection;
    private Positions positions = new Positions();

    private Boolean isDeckClickable = true;
    private Boolean isPawnMovable = false;
    private ArrayList<PlayerColor> players = new ArrayList<PlayerColor>();

    String yellowPawn = Apologies.getResourcePath("YellowPawn.png");
    String greenPawn = Apologies.getResourcePath("GreenPawn.png");
    String redPawn = Apologies.getResourcePath("RedPawn.png");
    String bluePawn = Apologies.getResourcePath("BluePawn.png");

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

        // Add Board base components
        this.img = img;
        addMouseListener(this);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        // Create panels
        JPanel TWO_PANEL = new JPanel();
        JRadioButton TWO_A = new JRadioButton("Start a Pawn");
        JRadioButton TWO_B = new JRadioButton("Move a pawn forward 2 spaces");
        ButtonGroup TWO_OPTIONS = new ButtonGroup();
        TWO_OPTIONS.add(TWO_A);
        TWO_OPTIONS.add(TWO_B);
        TWO_PANEL.add(TWO_A);
        TWO_PANEL.add(TWO_B);

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
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){

            // Left Click pawn - Iterate through pawns
            for (Pawn pawn: pawns) {
                if (e.getX() > pawn.getX() && e.getX() < pawn.getX()+pawnClickAreaWidth
                   && e.getY() > pawn.getY() && e.getY() < pawn.getY()+pawnClickAreaHeight && isPawnMovable && (pawn.getColor() == players.get(0))){

                        // Move Pawn Forward a Space
                        pawn.Move(currentCard.getNumber());

                        //rotate the first player to the end of the list
                        PlayerColor first = players.get(0);
                        players.remove(first);
                        players.add(first);
                        //reset the clickable flag for deck and pawn
                        isDeckClickable = true;
                        isPawnMovable = false;
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
                    pawn.moveBack();
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

    // Unused MouseListener functions
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}
// end class