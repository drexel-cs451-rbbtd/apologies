package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;


public class ApologiesGameWindow extends JFrame {
    public static List<String> playerNames = new ArrayList<String>();
    private JMenuBar menuBar;
    private JMenu menu;

    public ApologiesGameWindow(List<PlayerColor> playerColors, PlayerColor first, List<String> playerNames) {
        ApologiesGameWindow.playerNames = playerNames;
        // initialize art assets
        String boardIMG = getResourcePath("ApologiesBoard.png");
        Image board = new ImageIcon(boardIMG).getImage();
        setupMenu();
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

    public static String getNameOfPlayerAtIndex(int i) {
        return playerNames.get(i);
    }

    public static void cycleFirstPlayerToLast() {
        final int indexOfFirstPlayer = 0;
        String nextName = playerNames.get(indexOfFirstPlayer);
        playerNames.remove(indexOfFirstPlayer);
        playerNames.add(nextName);
    }

    private void setupMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Game");
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

} // end class

