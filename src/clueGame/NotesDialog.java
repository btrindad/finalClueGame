package clueGame;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import GUI.OptionBox;
import GUI.PersonGuessPanel;
import GUI.RoomGuessPanel;
import GUI.WeaponGuessPanel;

public class NotesDialog extends JDialog {
	
	public NotesDialog() {
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Notes");
		setSize(500, 600);
		setLayout(new GridLayout(3, 2));
		
		JPanel peoplePanel = new JPanel(); // Start of people panel
		peoplePanel.setLayout(new GridLayout(3, 2));
		OptionBox scarlett = new OptionBox("Miss Scarlett");
		peoplePanel.add(scarlett);
		OptionBox mustard = new OptionBox("Colonel Mustard");
		peoplePanel.add(mustard);
		OptionBox green = new OptionBox("Mr. Green");
		peoplePanel.add(green);
		OptionBox white = new OptionBox("Mrs. White");
		peoplePanel.add(white);
		OptionBox peacock = new OptionBox("Mrs. Peacock");
		peoplePanel.add(peacock);
		OptionBox plum = new OptionBox("Professor Plum");
		peoplePanel.add(plum);
		peoplePanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		add(peoplePanel);		
		
		PersonGuessPanel personGuessPanel = new PersonGuessPanel();
		add(personGuessPanel);
		
		JPanel roomsPanel = new JPanel();
		roomsPanel.setLayout(new GridLayout(5,2));
		OptionBox kitchen = new OptionBox("Kitchen");
		roomsPanel.add(kitchen);
		OptionBox dining = new OptionBox("Dining Room");
		roomsPanel.add(dining);
		OptionBox lounge = new OptionBox("Lounge");
		roomsPanel.add(lounge);
		OptionBox ballroom = new OptionBox("Ballroom");
		roomsPanel.add(ballroom);
		OptionBox conservatory = new OptionBox("Conservatory");
		roomsPanel.add(conservatory);
		OptionBox hall = new OptionBox("Hall");
		roomsPanel.add(hall);
		OptionBox study = new OptionBox("Study");
		roomsPanel.add(study);
		OptionBox library = new OptionBox("Library");
		roomsPanel.add(library);
		OptionBox billiards = new OptionBox("Billiard Room");
		roomsPanel.add(billiards);
		roomsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		add(roomsPanel);
		
		RoomGuessPanel roomGuess = new RoomGuessPanel();
		add(roomGuess);
		
		JPanel weaponsPanel = new JPanel();
		OptionBox candlestick = new OptionBox("Candlestick");
		weaponsPanel.add(candlestick);
		OptionBox knife = new OptionBox("Knife");
		weaponsPanel.add(knife);
		OptionBox pipe = new OptionBox("Lead Pipe");
		weaponsPanel.add(pipe);
		OptionBox revolver = new OptionBox("Revolver");
		weaponsPanel.add(revolver);
		OptionBox rope = new OptionBox("Rope");
		weaponsPanel.add(rope);
		OptionBox wrench = new OptionBox("Wrench");
		weaponsPanel.add(wrench);
		weaponsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		add(weaponsPanel);
		
		WeaponGuessPanel weaponGuess = new WeaponGuessPanel();
		add(weaponGuess);
	}
	
	public static void main(String[] args) {
		NotesDialog nd = new NotesDialog();
	}
}
