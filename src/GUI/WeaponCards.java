package GUI;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class WeaponCards extends JPanel { // dieRollPanel contains the current turn
	ArrayList<JTextField> weaponField = new ArrayList<JTextField>();

	public WeaponCards(ArrayList<String> people) {
		for (String d : people){
			weaponField.add(new JTextField(d, d.length()));
		}
		
		for (JTextField d : weaponField){
			d.setEditable(false);
			add(d);
		}
		
		setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
	}
}
