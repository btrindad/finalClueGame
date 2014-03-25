package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class resultsPanel extends JPanel {
	JLabel resultLabel;
	JTextField resultField;
	
	public resultsPanel(){
		resultLabel = new JLabel("Response");
		resultField = new JTextField(10);
		add(resultLabel);
		add(resultField);
		setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
	}
}
