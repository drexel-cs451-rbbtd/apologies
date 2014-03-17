package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    public JPanel rootPanel;
    private JButton newGameButton;
    private JButton quitButton;

    public MainMenu() {
        // add listeners
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               // System.out.println("this should switch to the player setup window");
               new PlayerSetup();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Apologies");
        frame.setContentPane(new MainMenu().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    {
        setUpGUI();
    }

    private void setUpGUI() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setUpTitle();
        rootPanel.add(Box.createVerticalStrut(10));
        setUpButtons();
    }

    private void setUpTitle() {
        JLabel titleLabel = new JLabel("Apologies");
        titleLabel.setFont(titleLabel.getFont().deriveFont(24.0f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rootPanel.add(centerJustify(titleLabel));

        rootPanel.add(Box.createVerticalStrut(3));

        JLabel subtitleLabel = new JLabel("board game");
        subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(14.0f));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rootPanel.add(centerJustify(subtitleLabel));
    }

    private Container centerJustify(Component component) {
        Box box = Box.createHorizontalBox();
        box.add(Box.createHorizontalGlue());
        box.add(component);
        box.add(Box.createHorizontalGlue());
        return box;
    }

    private void setUpButtons() {
        newGameButton = new JButton("New Game");
        newGameButton.setToolTipText("Set up a new game of Apologies");
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        rootPanel.add(centerJustify(newGameButton));

        quitButton = new JButton("Quit");
        newGameButton.setToolTipText("Exit this program");
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        rootPanel.add(centerJustify(quitButton));
    }

}
