package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import clueBoard.Board;
import clueGame.Card;
import clueGame.CardType;

public class MyCardsPanel extends JPanel { // myCardsPanel contains the humans
											// cards
	private ArrayList<String> weapons;
	private ArrayList<String> rooms;
	private ArrayList<String> people;
	private ArrayList<CardsDisplay> displayPanels;
	public static final int WIDTH = 250;
	JLabel cardLabel;

	public MyCardsPanel(Set<Card> cards) {
		weapons = new ArrayList<String>();
		rooms = new ArrayList<String>();
		people = new ArrayList<String>();
		displayPanels = new ArrayList<CardsDisplay>();
		
		getCardNames(cards);
		cardLabel = new JLabel("My Cards");
		
		displayPanels.add(new CardsDisplay(weapons, "Weapons"));
		displayPanels.add(new CardsDisplay(rooms, "Rooms"));
		displayPanels.add(new CardsDisplay(people, "People"));
	}
	
	public void display(Board b){
		setSize(WIDTH, b.getHeight());
		JPanel cardsPanel = new JPanel(); // controlPanel is the overarching panel
		cardsPanel.setLayout(new GridLayout(4, 1));
		add(cardsPanel);
		cardsPanel.add(cardLabel);
		for(CardsDisplay d : displayPanels){
			d.display();
			cardsPanel.add(d);
		}
	}

	public void getCardNames(Set<Card> cards) {
		for (Card d : cards) {
			if (d.cardType == CardType.PERSON) {
				people.add(d.name);
			} else if (d.cardType == CardType.ROOM) {
				rooms.add(d.name);
			} else if (d.cardType == CardType.WEAPON) {
				weapons.add(d.name);
			}
		}
	}

}