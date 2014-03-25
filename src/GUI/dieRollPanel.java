package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class dieRollPanel extends JPanel { // dieRollPanel contains the current turn
	public dieRollPanel() {
		JLabel dieLabel = new JLabel("Roll");
		JTextField dieField = new JTextField(3);
		add(dieLabel);
		add(dieField);
		setBorder(new TitledBorder(new EtchedBorder(), "Die"));
	}
}
