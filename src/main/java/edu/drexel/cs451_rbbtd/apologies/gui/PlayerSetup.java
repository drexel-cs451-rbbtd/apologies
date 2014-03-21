package edu.drexel.cs451_rbbtd.apologies.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
	private Map<PlayerColor, JCheckBox> playingCheckboxes = new HashMap<PlayerColor, JCheckBox>();
	private Map<PlayerColor, JRadioButton> firstRadioButtons = new HashMap<PlayerColor, JRadioButton>();
	private Map<PlayerColor, JTextField> nameFields = new HashMap<PlayerColor, JTextField>();

	private JTextField nameRed;
	private JTextField nameYellow;
	private JTextField nameGreen;
	private JTextField nameBlue;
	
	private PlayerSetupController controller = new PlayerSetupController();
	
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
	
	private void setupButtons() {
		start = new JButton("Start");
		cancel = new JButton("Cancel");
		
		start.setPreferredSize(new Dimension(150, 45));
		cancel.setPreferredSize(new Dimension(150, 45));

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Boolean isSetup = controller.checkReadyToStart(playingCheckboxes, firstRadioButtons, nameFields);
                if (isSetup) {
                    List<PlayerColor> colorsOfPlayingPlayers = controller.getCheckedPlayerColors(playingCheckboxes);
                    PlayerColor first = controller.getColorSelectedAsFirst(firstRadioButtons);
                    List<String> effectiveNames = controller.getEffectiveNamesOfPlayingPlayers(nameFields, colorsOfPlayingPlayers);
                    new ApologiesGameWindow(colorsOfPlayingPlayers, first, effectiveNames);
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
		playingCheckboxes.put(PlayerColor.RED, checkRed);
        playingCheckboxes.put(PlayerColor.BLUE, checkBlue);
        playingCheckboxes.put(PlayerColor.YELLOW, checkYellow);
		playingCheckboxes.put(PlayerColor.GREEN, checkGreen);

		//initialize radio buttons and add to groups
		firstRed = new JRadioButton();
        firstBlue = new JRadioButton();
        firstYellow = new JRadioButton();
		firstGreen = new JRadioButton();
		firstRadioButtons.put(PlayerColor.RED, firstRed);
        firstRadioButtons.put(PlayerColor.BLUE, firstBlue);
        firstRadioButtons.put(PlayerColor.YELLOW, firstYellow);
		firstRadioButtons.put(PlayerColor.GREEN, firstGreen);

		radioGroup = new ButtonGroup();
		radioGroup.add(firstRed);
        radioGroup.add(firstBlue);
        radioGroup.add(firstYellow);
		radioGroup.add(firstGreen);
	}
	
	private JPanel addLayout(JCheckBox check, JRadioButton radio, Color color, JTextField text) {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 20));
		p.add(check);
		p.add(radio);
		
		JLabel colorRectangle = new JLabel("        ");
		colorRectangle.setOpaque(true);
		colorRectangle.setBackground(color);
		p.add(colorRectangle);
		
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
        panel.add(cancel);
        panel.add(start);
        controlPanel.getRootPane().setDefaultButton(start);

        return panel;
	}
	
	private void addComponents() {
		nameRed = new JTextField(15);
		nameYellow = new JTextField(15);
		nameGreen = new JTextField(15);
		nameBlue = new JTextField(15);
		nameFields.put(PlayerColor.RED, nameRed);
        nameFields.put(PlayerColor.BLUE, nameBlue);
        nameFields.put(PlayerColor.YELLOW, nameYellow);
		nameFields.put(PlayerColor.GREEN, nameGreen);

		controlPanel.add(addLabels());
		controlPanel.add(addLayout(checkRed, firstRed, Color.RED, nameRed));
        controlPanel.add(addLayout(checkBlue, firstBlue, Color.BLUE, nameBlue));
        controlPanel.add(addLayout(checkYellow, firstYellow, Color.YELLOW, nameYellow));
		controlPanel.add(addLayout(checkGreen, firstGreen, Color.GREEN, nameGreen));
		controlPanel.add(addButtons());
		
		this.setVisible(true);
	}
	
	private void clearForm() {
		// clear checkboxes
		for (JCheckBox check : playingCheckboxes.values()) {
			check.setSelected(false);
		}
		// clear text fields
		for (JTextField name : nameFields.values()) {
			name.setText("");
		}
		// clear radio buttons
		radioGroup.clearSelection();
	}
}
