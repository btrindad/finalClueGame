package GUI;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class WeaponGuessPanel extends JPanel{
	private JComboBox<String> weaponBox;
	
	public WeaponGuessPanel() {
		weaponBox = createWeaponCombo();
		add(weaponBox);
		setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
	}
	
	private JComboBox<String> createWeaponCombo() {
		JComboBox<String> c = new JComboBox<String>();
		c.addItem("Candlestick");
		c.addItem("Knife");
		c.addItem("Lead Pipe");
		c.addItem("Revolver");
		c.addItem("Rope");
		c.addItem("Wrench");
		return c;
	}
}
