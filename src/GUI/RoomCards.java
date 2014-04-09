package GUI;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RoomCards extends JPanel { // dieRollPanel contains the current turn
		ArrayList<JTextField> roomField = new ArrayList<JTextField>();

		public RoomCards(ArrayList<String> rooms) {
			for (String d : rooms){
				roomField.add(new JTextField(d, d.length()));
			}
			
			for (JTextField d : roomField){
				d.setEditable(false);
				add(d);
			}
			
			setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		}
}
