package edu.drexel.cs451_rbbtd.apologies.gui;

import java.util.*;
import javax.swing.*;


public class PlayerSetupController {

	private Boolean checkNumPlayers(Map<PlayerColor, JCheckBox> checkboxGroup) {
		// check to make sure at least 2 colors are checked
		int count = 0;
		
		for (JCheckBox check : checkboxGroup.values()) {
			if (check.isSelected())
				count ++;
		}
		
		if (count >= 2)
			return true;
		else 
			return false;
	}
	
	private Boolean checkFirstIsPicked(Map<PlayerColor, JCheckBox> playingCheckboxes, Map<PlayerColor, JRadioButton> firstRadioButtons) {
		// check to make sure one of the checked colors is selected to go first
        for (PlayerColor color : firstRadioButtons.keySet()) {
            if (firstRadioButtons.get(color).isSelected() && playingCheckboxes.get(color).isSelected()) {
                return true;
            }
        }
		return false;
	}

	public Boolean checkReadyToStart(Map<PlayerColor, JCheckBox> checks, Map<PlayerColor, JRadioButton> buttons, Map<PlayerColor, JTextField> names) {
		// return true only if all tests pass
		boolean enoughPlayers = checkNumPlayers(checks);
		boolean firstIsPicked = checkFirstIsPicked(checks, buttons);

        return (enoughPlayers && firstIsPicked);
	}

    // returns colors in no particular order
    public ArrayList<PlayerColor> getCheckedPlayerColors(Map<PlayerColor, JCheckBox> checks) {
        ArrayList<PlayerColor> playerColors = new ArrayList<PlayerColor>();
        for (Map.Entry<PlayerColor, JCheckBox> colorAndCheckBoxEntry : checks.entrySet()) {
            if (colorAndCheckBoxEntry.getValue().isSelected()) {
                playerColors.add(colorAndCheckBoxEntry.getKey());
            }
        }
        return playerColors;
    }

    public PlayerColor getColorSelectedAsFirst(Map<PlayerColor, JRadioButton> buttons) {
        for (Map.Entry<PlayerColor, JRadioButton> colorAndRadioButtonEntry : buttons.entrySet()) {
            if (colorAndRadioButtonEntry.getValue().isSelected()) {
                return colorAndRadioButtonEntry.getKey();
            }
        }
        return null;
    }


    public List getEffectiveNamesOfPlayingPlayers(Map<PlayerColor, JTextField> names, Collection<PlayerColor> colorsOfPlayingPlayers) {
        List<String> effectiveNames = new LinkedList<String>();
        for (Map.Entry<PlayerColor, JTextField> playerColorAndTextFieldEntry : names.entrySet()) {
            PlayerColor color = playerColorAndTextFieldEntry.getKey();
            JTextField nameTextField = playerColorAndTextFieldEntry.getValue();

            if (colorsOfPlayingPlayers.contains(color)) {
                String enteredName = nameTextField.getText().trim();
                String effectiveName = effectiveNameForPlayerWithEnteredName(color, enteredName);
                effectiveNames.add(effectiveName);
            }
        }
        return effectiveNames;
    }

    private String effectiveNameForPlayerWithEnteredName(PlayerColor color, String enteredName) {
        String effectiveName;
        if (enteredName.isEmpty()) {
            effectiveName = playerNameForColor(color);
        } else {
            effectiveName = enteredName;
        }
        return effectiveName;
    }

    private String playerNameForColor(PlayerColor color) {
        switch (color) {
            case RED:
                return "Red";
            case GREEN:
                return "Green";
            case BLUE:
                return "Blue";
            case YELLOW:
                return "Yellow";
            default:
                return "???";
        }
    }
}
