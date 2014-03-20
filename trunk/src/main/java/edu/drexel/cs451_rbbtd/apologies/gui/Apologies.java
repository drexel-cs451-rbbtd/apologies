package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class Apologies extends JFrame {
    public static ArrayList<JTextField> names = new ArrayList<JTextField>();

    public Apologies(ArrayList<PlayerColor> playerColors, PlayerColor first, ArrayList<JTextField> pNames) {
        names = pNames;
        // initialize art assets
        String boardIMG = getResourcePath("ApologiesBoard.png");
        //String boardIMG = "resources/ApologiesBoard.png";
        Image board = new ImageIcon(boardIMG).getImage();

        add(new Board(board, playerColors, first));
        setTitle("Apologies");
        setSize(600, 675);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static String getResourcePath(String resourceFilename) {
        return "src" + File.separator + "main" + File.separator + "resources" + File.separator + resourceFilename;
    }

    public static String getNames(int i) { return names.get(i).getText(); }

    public static void swapFirstLast() {
        JTextField nextName = names.get(0);
        names.remove(nextName);
        names.add(nextName);
    }
} // end class

