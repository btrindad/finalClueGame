package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class turnPanel extends JPanel { // turnPanel contains and handles the current turn
	JTextField turnField;
	
	public turnPanel(){
		turnField = new JTextField(15);
		add(turnField);
		setBorder(new TitledBorder(new EtchedBorder(), "Whose turn?"));
	}
}
