package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Apologies extends JFrame{


    public Apologies() {

        // Initialize Art Assets
        String boardIMG = getResourcePath("ApologiesBoard.png");

        Image board = new ImageIcon(boardIMG).getImage();

        add(new Board(board));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 675);
        setLocationRelativeTo(null);
        setTitle("Apologies");
        setResizable(false);
        setVisible(true);

    }

    public static String getResourcePath(String resourceFilename) {
        return "src" + File.separator + "main" + File.separator + "resources" + File.separator + resourceFilename;
    }

    public static void main(String[] args) {
        Apologies apologies = new Apologies();
    }


} // end class
