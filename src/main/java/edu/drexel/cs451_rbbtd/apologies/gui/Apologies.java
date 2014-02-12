package edu.drexel.cs451_rbbtd.apologies.gui; /**
 * Created by Ben on 2/8/14.
 */

import javax.swing.*;
import java.awt.*;


public class Apologies extends JFrame{


    public Apologies() {

        // Initialize Art Assets
        String boardIMG = "src\\main\\resources\\ApologiesBoard.png";
        Image board = new ImageIcon(boardIMG).getImage();

        add(new Board(board));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 625);
        setLocationRelativeTo(null);
        setTitle("Apologies");
        setResizable(false);
        setVisible(true);

    }

    public static void main(String[] args) {
        Apologies apologies = new Apologies();
    }







} // end class
