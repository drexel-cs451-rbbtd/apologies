package edu.drexel.cs451_rbbtd.apologies.gui;

import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Board extends JPanel implements MouseListener {

    private Pawn[] pawns;
    private int PawnCount = 16;
    private Image img;
    private Deck deck;
    private Card currentCard;
    private JPanel[] Panels;
    private int selection;
    private Positions positions = new Positions();

    String yellowPawn = Apologies.getResourcePath("YellowPawn.png");
    String greenPawn = Apologies.getResourcePath("GreenPawn.png");
    String redPawn = Apologies.getResourcePath("RedPawn.png");
    String bluePawn = Apologies.getResourcePath("BluePawn.png");

    public Board(Image img) {

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
        pawns = new Pawn[PawnCount];
        pawns[0] = new Pawn(125, 100, yellowPositions, yellowPawn);
        pawns[1] = new Pawn(165, 100, yellowPositions, yellowPawn);
        pawns[2] = new Pawn(125, 60, yellowPositions, yellowPawn);
        pawns[3] = new Pawn(165, 60, yellowPositions, yellowPawn);
        pawns[4] = new Pawn(430, 170, greenPositions, greenPawn);
        pawns[5] = new Pawn(470, 170, greenPositions, greenPawn);
        pawns[6] = new Pawn(430, 130, greenPositions, greenPawn);
        pawns[7] = new Pawn(470, 130, greenPositions, greenPawn);
        pawns[8] = new Pawn(365, 480, redPositions, redPawn);
        pawns[9] = new Pawn(405, 480, redPositions, redPawn);
        pawns[10] = new Pawn(365, 440, redPositions, redPawn);
        pawns[11] = new Pawn(405, 440, redPositions, redPawn);
        pawns[12] = new Pawn(60, 415, bluePositions, bluePawn);
        pawns[13] = new Pawn(100, 415, bluePositions, bluePawn);
        pawns[14] = new Pawn(60, 375, bluePositions, bluePawn);
        pawns[15] = new Pawn(100, 375, bluePositions, bluePawn);

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
        for(int i = 0; i < PawnCount ; i++){
            g2d.drawImage(pawns[i].getImage(), pawns[i].getX(), pawns[i].getY(), this);
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


    // Handle Mouse Clicks
    public void mouseClicked(MouseEvent e) {

        final int pawnClickAreaWidth = 50;
        final int pawnClickAreaHeight = 50;
        final int cardClickAreaWidth = 120;
        final int cardClickAreaHeight = 160;

        // Left Click
        if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){

            // Left Click pawn - Iterate through pawns array
            for(int i = 0; i < PawnCount ; i++){
                if(e.getX() > pawns[i].getX() && e.getX() < pawns[i].getX()+pawnClickAreaWidth
                   && e.getY() > pawns[i].getY() && e.getY() < pawns[i].getY()+pawnClickAreaHeight){

                        // Move Pawn Forward a Space
                        pawns[i].Move(currentCard.getNumber());
                        repaint();
                }
            }

            // Left Click Deck - draw card
            if(e.getX() > deck.getX() && e.getX() < deck.getX()+cardClickAreaWidth
                    && e.getY() > deck.getY() && e.getY() < deck.getY()+cardClickAreaHeight){

                    // Draw a card and paint it on the board
                    currentCard = new Card(deck.drawCard());
                    repaint();
            }

        }

        // Right Click - just for debugging purposes
        if((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){

            // Iterate through pawns array
            for(int i = 0; i < PawnCount ; i++){
                if(e.getX() > pawns[i].getX() && e.getX() < pawns[i].getX()+pawnClickAreaWidth
                   && e.getY() > pawns[i].getY() && e.getY() < pawns[i].getY()+pawnClickAreaHeight){

                        // Move Pawn Back a Space
                        pawns[i].moveBack();
                        repaint();
                }
            }
        }

    }

    // Unused MouseListener functions
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}


} // end class