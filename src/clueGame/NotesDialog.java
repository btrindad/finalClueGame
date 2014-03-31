package clueGame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import GUI.PersonGuessPanel;
import GUI.RoomGuessPanel;
import GUI.WeaponGuessPanel;

public class NotesDialog extends JDialog {
	
	public NotesDialog() {
		setVisible(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE); // 
		setTitle("Notes");
		setLayout(new GridLayout(3, 2));
		
		JPanel peoplePanel = new JPanel(); // Start of people panel
		peoplePanel.setLayout(new GridLayout(3, 2));
		JCheckBox scarlett = new JCheckBox("Miss Scarlett");
		peoplePanel.add(scarlett);
		JCheckBox mustard = new JCheckBox("Colonel Mustard");
		peoplePanel.add(mustard);
		JCheckBox green = new JCheckBox("Mr. Green");
		peoplePanel.add(green);
		JCheckBox white = new JCheckBox("Mrs. White");
		peoplePanel.add(white);
		JCheckBox peacock = new JCheckBox("Mrs. Peacock");
		peoplePanel.add(peacock);
		JCheckBox plum = new JCheckBox("Professor Plum");
		peoplePanel.add(plum);
		peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		add(peoplePanel);		
		
		PersonGuessPanel personGuessPanel = new PersonGuessPanel();
		add(personGuessPanel);
		
		JPanel roomsPanel = new JPanel();
		roomsPanel.setLayout(new GridLayout(5,2));
		JCheckBox kitchen = new JCheckBox("Kitchen");
		roomsPanel.add(kitchen);
		JCheckBox dining = new JCheckBox("Dining Room");
		roomsPanel.add(dining);
		JCheckBox lounge = new JCheckBox("Lounge");
		roomsPanel.add(lounge);
		JCheckBox ballroom = new JCheckBox("Ballroom");
		roomsPanel.add(ballroom);
		JCheckBox conservatory = new JCheckBox("Conservatory");
		roomsPanel.add(conservatory);
		JCheckBox hall = new JCheckBox("Hall");
		roomsPanel.add(hall);
		JCheckBox study = new JCheckBox("Study");
		roomsPanel.add(study);
		JCheckBox library = new JCheckBox("Library");
		roomsPanel.add(library);
		JCheckBox billiards = new JCheckBox("Billiard Room");
		roomsPanel.add(billiards);
		roomsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		add(roomsPanel);
		
		RoomGuessPanel roomGuess = new RoomGuessPanel();
		add(roomGuess);
		
		JPanel weaponsPanel = new JPanel();
		JCheckBox candlestick = new JCheckBox("Candlestick");
		weaponsPanel.add(candlestick);
		JCheckBox knife = new JCheckBox("Knife");
		weaponsPanel.add(knife);
		JCheckBox pipe = new JCheckBox("Lead Pipe");
		weaponsPanel.add(pipe);
		JCheckBox revolver = new JCheckBox("Revolver");
		weaponsPanel.add(revolver);
		JCheckBox rope = new JCheckBox("Rope");
		weaponsPanel.add(rope);
		JCheckBox wrench = new JCheckBox("Wrench");
		weaponsPanel.add(wrench);
		weaponsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		add(weaponsPanel);
		
		WeaponGuessPanel weaponGuess = new WeaponGuessPanel();
		add(weaponGuess);
		
		setSize(500, 600);
	}
	
	//public static void main(String[] args) { // For debugging
		//NotesDialog nd = new NotesDialog(); // For Debugging, enable with DISPOSE_ON_CLOSE
	//}
}
