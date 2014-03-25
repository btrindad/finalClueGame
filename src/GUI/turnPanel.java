package GUI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class turnPanel extends JPanel {
	public turnPanel(){
		JLabel whoseTurn = new JLabel("Whose Turn?");
		JTextField input = new JTextField(20);
		input.setFont(new Font("SansSerif", Font.PLAIN, 12));
		setLayout(new GridLayout(2, 0));
		add(whoseTurn);
		add(input);
	}
}
