package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class dieRollPanel extends JPanel { // dieRollPanel contains the current turn
	JLabel dieLabel;
	JTextField dieField;

	public dieRollPanel() {
		dieLabel = new JLabel("Roll");
		dieField = new JTextField(3);
		dieField.setEditable(false);
		add(dieLabel);
		add(dieField);
		setBorder(new TitledBorder(new EtchedBorder(), "Die"));
	}
}
