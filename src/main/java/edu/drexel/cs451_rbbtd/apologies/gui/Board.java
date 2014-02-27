package edu.drexel.cs451_rbbtd.apologies.gui; /**
 * Created by Ben on 2/8/14.
 */

import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Board extends JPanel implements MouseListener{

    private Pawn[] pawns;
    private int PawnCount = 16;
    private Image img;
    private Deck deck;
    private Card currentCard;
    private JPanel[] Panels;
    private int selection;
    private int t = 40; // tile size

    String yellowPawn = "src\\main\\resources\\YellowPawn.png";
    String greenPawn = "src\\main\\resources\\GreenPawn.png";
    String redPawn = "src\\main\\resources\\RedPawn.png";
    String bluePawn = "src\\main\\resources\\BluePawn.png";

    public Board(Image img) {

        // Initialize positions
                int yellowPositions[][] = {
                {t*4-15, t*0-10}, // first square out of home
                {t*5-15, t*0-10},
                {t*6-15, t*0-10},
                {t*7-15, t*0-10},
                {t*8-15, t*0-10},
                {t*9-15, t*0-10},
                {t*10-15, t*0-10},
                {t*11-15, t*0-10},
                {t*12-15, t*0-10},
                {t*13-15, t*0-10},
                {t*14-15, t*0-10},
                {t*14-15, t*1-10},
                {t*14-15, t*2-10},
                {t*14-15, t*3-10},
                {t*14-15, t*4-10},
                {t*14-15, t*5-10},
                {t*14-15, t*6-10},
                {t*14-15, t*7-10},
                {t*14-15, t*8-10},
                {t*14-15, t*9-10},
                {t*14-15, t*10-10},
                {t*14-15, t*11-10},
                {t*14-15, t*12-10},
                {t*14-15, t*13-10},
                {t*14-15, t*14-10},
                {t*13-15, t*14-10},
                {t*12-15, t*14-10},
                {t*11-15, t*14-10},
                {t*10-15, t*14-10},
                {t*9-15, t*14-10},
                {t*8-15, t*14-10},
                {t*7-15, t*14-10},
                {t*6-15, t*14-10},
                {t*5-15, t*14-10},
                {t*4-15, t*14-10},
                {t*3-15, t*14-10},
                {t*2-15, t*14-10},
                {t*1-15, t*14-10},
                {t*0-15, t*14-10},
                {t*0-15, t*13-10},
                {t*0-15, t*12-10},
                {t*0-15, t*11-10},
                {t*0-15, t*10-10},
                {t*0-15, t*9-10},
                {t*0-15, t*8-10},
                {t*0-15, t*7-10},
                {t*0-15, t*6-10},
                {t*0-15, t*5-10},
                {t*0-15, t*4-10},
                {t*0-15, t*3-10},
                {t*0-15, t*2-10},
                {t*0-15, t*1-10},
                {t*0-15, t*0-10},
                {t*1-15, t*0-10},
                {t*2-15, t*0-10},
                {t*2-15, t*1-10},
                {t*2-15, t*2-10},
                {t*2-15, t*3-10},
                {t*2-15, t*4-10},
        };

        int greenPositions[][] = {
                {t*14-15, t*4-10}, // first square out of home
                {t*14-15, t*5-10},
                {t*14-15, t*6-10},
                {t*14-15, t*7-10},
                {t*14-15, t*8-10},
                {t*14-15, t*9-10},
                {t*14-15, t*10-10},
                {t*14-15, t*11-10},
                {t*14-15, t*12-10},
                {t*14-15, t*13-10},
                {t*14-15, t*14-10},
                {t*13-15, t*14-10},
                {t*12-15, t*14-10},
                {t*11-15, t*14-10},
                {t*10-15, t*14-10},
                {t*9-15, t*14-10},
                {t*8-15, t*14-10},
                {t*7-15, t*14-10},
                {t*6-15, t*14-10},
                {t*5-15, t*14-10},
                {t*4-15, t*14-10},
                {t*3-15, t*14-10},
                {t*2-15, t*14-10},
                {t*1-15, t*14-10},
                {t*0-15, t*14-10},
                {t*0-15, t*13-10},
                {t*0-15, t*12-10},
                {t*0-15, t*11-10},
                {t*0-15, t*10-10},
                {t*0-15, t*9-10},
                {t*0-15, t*8-10},
                {t*0-15, t*7-10},
                {t*0-15, t*6-10},
                {t*0-15, t*5-10},
                {t*0-15, t*4-10},
                {t*0-15, t*3-10},
                {t*0-15, t*2-10},
                {t*0-15, t*1-10},
                {t*0-15, t*0-10},
                {t*1-15, t*0-10},
                {t*2-15, t*0-10},
                {t*3-15, t*0-10},
                {t*4-15, t*0-10},
                {t*5-15, t*0-10},
                {t*6-15, t*0-10},
                {t*7-15, t*0-10},
                {t*8-15, t*0-10},
                {t*9-15, t*0-10},
                {t*10-15, t*0-10},
                {t*11-15, t*0-10},
                {t*12-15, t*0-10},
                {t*13-15, t*0-10},
                {t*14-15, t*0-10},
                {t*14-15, t*1-10},
                {t*14-15, t*2-10},
                {t*13-15, t*2-10},
                {t*12-15, t*2-10},
                {t*11-15, t*2-10},
                {t*10-15, t*2-10},
        };

        int redPositions[][] = {
                {t*10-15, t*14-10}, // first position out of base
                {t*9-15, t*14-10},
                {t*8-15, t*14-10},
                {t*7-15, t*14-10},
                {t*6-15, t*14-10},
                {t*5-15, t*14-10},
                {t*4-15, t*14-10},
                {t*3-15, t*14-10},
                {t*2-15, t*14-10},
                {t*1-15, t*14-10},
                {t*0-15, t*14-10},
                {t*0-15, t*13-10},
                {t*0-15, t*12-10},
                {t*0-15, t*11-10},
                {t*0-15, t*10-10},
                {t*0-15, t*9-10},
                {t*0-15, t*8-10},
                {t*0-15, t*7-10},
                {t*0-15, t*6-10},
                {t*0-15, t*5-10},
                {t*0-15, t*4-10},
                {t*0-15, t*3-10},
                {t*0-15, t*2-10},
                {t*0-15, t*1-10},
                {t*0-15, t*0-10},
                {t*1-15, t*0-10},
                {t*2-15, t*0-10},
                {t*3-15, t*0-10},
                {t*4-15, t*0-10},
                {t*5-15, t*0-10},
                {t*6-15, t*0-10},
                {t*7-15, t*0-10},
                {t*8-15, t*0-10},
                {t*9-15, t*0-10},
                {t*10-15, t*0-10},
                {t*11-15, t*0-10},
                {t*12-15, t*0-10},
                {t*13-15, t*0-10},
                {t*14-15, t*0-10},
                {t*14-15, t*1-10},
                {t*14-15, t*2-10},
                {t*14-15, t*3-10},
                {t*14-15, t*4-10},
                {t*14-15, t*4-10},
                {t*14-15, t*5-10},
                {t*14-15, t*6-10},
                {t*14-15, t*7-10},
                {t*14-15, t*8-10},
                {t*14-15, t*9-10},
                {t*14-15, t*10-10},
                {t*14-15, t*11-10},
                {t*14-15, t*12-10},
                {t*14-15, t*13-10},
                {t*14-15, t*14-10},
                {t*13-15, t*14-10},
                {t*12-15, t*14-10},
                {t*12-15, t*13-10},
                {t*12-15, t*12-10},
                {t*12-15, t*11-10},
                {t*12-15, t*10-10},
        };

        int bluePositions[][] = {
                {t*0-15, t*10-10}, // first position out of base
                {t*0-15, t*9-10},
                {t*0-15, t*8-10},
                {t*0-15, t*7-10},
                {t*0-15, t*6-10},
                {t*0-15, t*5-10},
                {t*0-15, t*4-10},
                {t*0-15, t*3-10},
                {t*0-15, t*2-10},
                {t*0-15, t*1-10},
                {t*0-15, t*0-10},
                {t*1-15, t*0-10},
                {t*2-15, t*0-10},
                {t*3-15, t*0-10},
                {t*4-15, t*0-10},
                {t*5-15, t*0-10},
                {t*6-15, t*0-10},
                {t*7-15, t*0-10},
                {t*8-15, t*0-10},
                {t*9-15, t*0-10},
                {t*10-15, t*0-10},
                {t*11-15, t*0-10},
                {t*12-15, t*0-10},
                {t*13-15, t*0-10},
                {t*14-15, t*0-10},
                {t*14-15, t*1-10},
                {t*14-15, t*2-10},
                {t*14-15, t*3-10},
                {t*14-15, t*4-10},
                {t*14-15, t*4-10},
                {t*14-15, t*5-10},
                {t*14-15, t*6-10},
                {t*14-15, t*7-10},
                {t*14-15, t*8-10},
                {t*14-15, t*9-10},
                {t*14-15, t*10-10},
                {t*14-15, t*11-10},
                {t*14-15, t*12-10},
                {t*14-15, t*13-10},
                {t*14-15, t*14-10},
                {t*13-15, t*14-10},
                {t*12-15, t*14-10},
                {t*11-15, t*14-10},
                {t*10-15, t*14-10},
                {t*9-15, t*14-10},
                {t*8-15, t*14-10},
                {t*7-15, t*14-10},
                {t*6-15, t*14-10},
                {t*5-15, t*14-10},
                {t*4-15, t*14-10},
                {t*3-15, t*14-10},
                {t*2-15, t*14-10},
                {t*1-15, t*14-10},
                {t*0-15, t*14-10},
                {t*0-15, t*13-10},
                {t*0-15, t*12-10},
                {t*1-15, t*12-10},
                {t*2-15, t*12-10},
                {t*3-15, t*12-10},
                {t*4-15, t*12-10},
        }; // integer initializations

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
        TWO_PANEL.add(TWO_A); TWO_PANEL.add(TWO_B);

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

        // Left Click
        if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){

            // Left Click pawn - Iterate through pawns array
            for(int i = 0; i < PawnCount ; i++){
                if(e.getX() > pawns[i].getX() && e.getX() < pawns[i].getX()+50
                   && e.getY() > pawns[i].getY() && e.getY() < pawns[i].getY()+50){

                        // Move Pawn Forward a Space
                        pawns[i].moveForward();
                        repaint();
                }
            }

            // Left Click Deck - draw card
            if(e.getX() > deck.getX() && e.getX() < deck.getX()+120
                    && e.getY() > deck.getY() && e.getY() < deck.getY()+160){

                    // Draw a card and paint it on the board
                    currentCard = new Card(deck.drawCard());
                    repaint();
            }

        }

        // Right Click - just for debugging purposes
        if((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){

            // Iterate through pawns array
            for(int i = 0; i < PawnCount ; i++){
                if(e.getX() > pawns[i].getX() && e.getX() < pawns[i].getX()+50
                   && e.getY() > pawns[i].getY() && e.getY() < pawns[i].getY()+50){

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