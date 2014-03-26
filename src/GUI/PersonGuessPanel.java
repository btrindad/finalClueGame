package GUI;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PersonGuessPanel extends JPanel {
	private JComboBox<String> personBox;
	
	public PersonGuessPanel() {
		personBox = createPersonCombo();
		add(personBox);
		setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
	}
	
	private JComboBox<String> createPersonCombo() {
		JComboBox<String> c = new JComboBox<String>();
		c.addItem("Miss Scarlett");
		c.addItem("Colonel Mustard");
		c.addItem("Mr. Green");
		c.addItem("Mrs. White");
		c.addItem("Mrs. Peacock");
		c.addItem("Professor Plum");
		return c;
	}
}
