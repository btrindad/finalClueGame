package GUI;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionBox extends JPanel{
	public OptionBox(String s) {
		JCheckBox optionBox = new JCheckBox(s);
		add(optionBox);
	}
}
