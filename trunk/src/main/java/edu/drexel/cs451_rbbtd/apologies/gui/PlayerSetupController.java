import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class PlayerSetupController {

	private Boolean checkNumPlayers(ArrayList<JCheckBox> checkboxGroup) {
		//check to make sure atleast 2 colors are checked
		int count = 0;
		
		for (JCheckBox check : checkboxGroup) {
			if (check.isSelected())
				count ++;
		}
		
		if (count >= 2)
			return true;
		else 
			return false;
	}
	
	private Boolean checkFirst(ArrayList<JCheckBox> checkboxGroup, ArrayList<JRadioButton> buttons) {
		//check to make sure one of the checked colors is selected to go first
		for (int i = 0; i < buttons.size(); i++) { 
			if (buttons.get(i).isSelected()) {
				if (!checkboxGroup.get(i).isSelected())
					return false;
			}
		}
		
		return true;
	}
	
	private Boolean checkNames(ArrayList<JCheckBox> checkboxGroup, ArrayList<JTextField> names) {
		//check to make sure each checked color has a name
		for (int i = 0; i < checkboxGroup.size(); i++) {
			if (checkboxGroup.get(i).isSelected()) {
				if (names.get(i).getText().trim().equals(""))
					return false;
			}
		}
		
		return true;
	}
	
	public Boolean check(ArrayList<JCheckBox> checks, ArrayList<JRadioButton> buttons, ArrayList<JTextField> names) {
		//return true only if all 3 tests pass
		boolean enoughPlayers = checkNumPlayers(checks);
		boolean firstPicked = checkFirst(checks, buttons);
		boolean namesEntered = checkNames(checks, names);
		
		return (enoughPlayers && firstPicked && namesEntered);
		
	}
}
