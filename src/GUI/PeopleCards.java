package GUI;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PeopleCards extends JPanel { // dieRollPanel contains the current turn
	ArrayList<JTextField> peopleField = new ArrayList<JTextField>();

	public PeopleCards(ArrayList<String> people) {
		for (String d : people){
			peopleField.add(new JTextField(d,d.length()));
		}
		
		for (JTextField d : peopleField){
			d.setEditable(false);
			add(d);
		}
		
		setBorder(new TitledBorder(new EtchedBorder(), "People"));
	}
}
