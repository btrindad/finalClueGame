package GUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class turnPanel extends JPanel { // turnPanel contains and handles the current turn
	public turnPanel(){
		JPanel turnPanel = new JPanel();
		JLabel turnLabel = new JLabel("Whose turn?");
		JTextField turnField = new JTextField(15);
		add(turnLabel);
		add(turnField);
	}
}
