package edu.drexel.cs451_rbbtd.apologies.gui; /**
 * Created by Ben on 2/8/14.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Board extends JPanel implements MouseListener{

    private Pawn[] pawns;
    private int PawnCount = 4;
    private Image img;

    public Board(Image img) {

        this.img = img;
        addMouseListener(this);
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);


        // Initialize pawns
        pawns = new Pawn[PawnCount];
        pawns[0] = new Pawn(50, 250);
        pawns[1] = new Pawn(90, 250);
        pawns[2] = new Pawn(50, 210);
        pawns[3] = new Pawn(90, 210);

    }

    // Draw Background Image
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    // Draw Pawns
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        for(int i = 0; i < PawnCount ; i++){
            g2d.drawImage(pawns[i].getImage(), pawns[i].getX(), pawns[i].getY(), this);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }


    // Handle Mouse Clicks
    public void mouseClicked(MouseEvent e) {

        // Left Click
        if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){

            // Iterate through pawns array
            for(int i = 0; i < PawnCount ; i++){
                if(e.getX() > pawns[i].getX() && e.getX() < pawns[i].getX()+50
                   && e.getY() > pawns[i].getY() && e.getY() < pawns[i].getY()+50){

                        // Move Pawn Forward a Space
                        pawns[i].moveForward();
                        repaint();
                }
            }

        }

        // Right Click
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