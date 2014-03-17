package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class PlayerSetup extends JFrame {

	private JFrame mainFrame;

	private JPanel controlPanel;
	
	private JButton start;
	private JButton cancel;
	
	private JCheckBox checkRed;
	private JCheckBox checkYellow;
	private JCheckBox checkGreen;
	JCheckBox checkBlue;
	
	private JRadioButton firstRed;
	private JRadioButton firstYellow;
	private JRadioButton firstGreen;
	private JRadioButton firstBlue;
	private ButtonGroup radioGroup;
	private ArrayList<JCheckBox> checkGroup = new ArrayList<JCheckBox>();
	private ArrayList<JRadioButton> rads = new ArrayList<JRadioButton>();
	private ArrayList<JTextField> names = new ArrayList<JTextField>();

	private JTextField nameRed;
	private JTextField nameYellow;
	private JTextField nameGreen;
	private JTextField nameBlue;
	
	private PlayerSetupController c = new PlayerSetupController();
	
	public PlayerSetup() {
		prepareGUI();
		setupButtons();
		addComponents();
	}
	
	private void prepareGUI() {
		this.setTitle("Player Setup");
		this.setSize(500, 375);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(6, 1));
		
		this.add(controlPanel);
		this.setVisible(true);
	}
	
	private void setupButtons(){
		start = new JButton("Start");
		cancel = new JButton("Cancel");
		
		start.setPreferredSize(new Dimension(150, 45));
		cancel.setPreferredSize(new Dimension(150, 45));

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Boolean isSetup = c.check(checkGroup, rads, names);
                if (isSetup) {
                    ArrayList<PlayerColor> colors = c.getChecked(checkGroup);
                    new Apologies(colors);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(controlPanel, "Setup not finished", "Setup Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				dispose();
			}
        });
		
		//initialize check boxes and add to groups
		checkRed = new JCheckBox();
		checkYellow = new JCheckBox();
		checkGreen = new JCheckBox();
		checkBlue = new JCheckBox();
		checkGroup.add(checkRed);
		checkGroup.add(checkYellow);
		checkGroup.add(checkGreen);
		checkGroup.add(checkBlue);
		
		//initialize radio buttons and add to groups
		firstRed = new JRadioButton();
		firstYellow = new JRadioButton();
		firstGreen = new JRadioButton();
		firstBlue = new JRadioButton();
		rads.add(firstRed);
		rads.add(firstYellow);
		rads.add(firstGreen);
		rads.add(firstBlue);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(firstRed);
		radioGroup.add(firstYellow);
		radioGroup.add(firstGreen);
		radioGroup.add(firstBlue);
	}
	
	private JPanel addLayout(JCheckBox check, JRadioButton radio, Color color, JTextField text) {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 20));
		p.add(check);
		p.add(radio);
		
		JLabel label = new JLabel("        ");
		label.setOpaque(true);
		label.setBackground(color);
		p.add(label);
		
		p.add(text);
		return p;
	}
	
	private JPanel addLabels() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 45, 20));
		
		panel.add(new JLabel("Playing"));
		panel.add(new JLabel("First"));
		panel.add(new JLabel("Color"));
		panel.add(new JLabel("Name"));
		
		return panel;
	}
	
	private JPanel addButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 0));
		panel.add(start);
		panel.add(cancel);
		
		return panel;
	}
	
	private void addComponents() {
		nameRed = new JTextField(15);
		nameYellow = new JTextField(15);
		nameGreen = new JTextField(15);
		nameBlue = new JTextField(15);
		names.add(nameRed);
		names.add(nameYellow);
		names.add(nameGreen);
		names.add(nameBlue);
		
		controlPanel.add(addLabels());
		controlPanel.add(addLayout(checkRed, firstRed, Color.RED, nameRed));
		controlPanel.add(addLayout(checkYellow, firstYellow, Color.YELLOW, nameYellow));
		controlPanel.add(addLayout(checkGreen, firstGreen, Color.GREEN, nameGreen));
		controlPanel.add(addLayout(checkBlue, firstBlue, Color.BLUE, nameBlue));
		controlPanel.add(addButtons());
		
		this.setVisible(true);
	}
	
	private void clearForm() {
		// clear checkboxes
		for (JCheckBox check : checkGroup) {
			check.setSelected(false);
		}
		// clear text fields
		for (JTextField name : names) {
			name.setText("");
		}
		// clear radio buttons
		radioGroup.clearSelection();
	}

}
