package GUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class turnPanel extends JPanel {
	JLabel turnLabel;
	JTextField turnField;
	
	public turnPanel(){
		turnLabel = new JLabel("Whose turn?");
		turnField = new JTextField(15);
		add(turnLabel);
		add(turnField);
	}
}
