package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card;
import clueGame.CardType;

public class MyCardsPanel extends JPanel { // myCardsPanel contains the humans cards
	private ArrayList<String> people = new ArrayList<String>();
	private ArrayList<String> rooms = new ArrayList<String>();
	private ArrayList<String> weapons = new ArrayList<String>();
	JLabel cardLabel;
	
	public MyCardsPanel(Set<Card> cards){
	getCardNames(cards);
	
	cardLabel = new JLabel("My Cards");
	add(cardLabel);
	
	JPanel cardsPanel = new JPanel(); // controlPanel is the overarching Panel

	cardsPanel.setLayout(new GridLayout(4, 1));
	add(cardsPanel);
	
	cardsPanel.add(new PeopleCards(people));
	cardsPanel.add(new RoomCards(rooms));
	cardsPanel.add(new WeaponCards(weapons));

	}
	
	public void getCardNames(Set<Card> cards){
		for (Card d : cards){
			if (d.cardType == CardType.PERSON){
				people.add(d.name);
			} else if (d.cardType == CardType.ROOM){
				rooms.add(d.name);
			} else if (d.cardType == CardType.WEAPON){
			 	weapons.add(d.name);
			}
		}
	}
	
}