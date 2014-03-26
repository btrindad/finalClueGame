package GUI;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RoomGuessPanel extends JPanel {
	private JComboBox<String> roomBox;
	
	public RoomGuessPanel() {
		roomBox = createRoomCombo();
		add(roomBox);
		setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
	}
	
	private JComboBox<String> createRoomCombo() {
		JComboBox<String> c = new JComboBox<String>();
		c.addItem("Kitchen");
		c.addItem("Dining Room");
		c.addItem("Lounge");
		c.addItem("Ballroom");
		c.addItem("Conservatory");
		c.addItem("Hall");
		c.addItem("Study");
		c.addItem("Library");
		c.addItem("Billiard Room");
		return c;
	}
}
