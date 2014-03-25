package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class guessPanel extends JPanel { // guessPanel contains the current guess
	public guessPanel(){
		JLabel guessLabel = new JLabel("Guess");
		JTextField guessField = new JTextField(30);
		add(guessLabel);
		add(guessField);
		setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
	}
}
