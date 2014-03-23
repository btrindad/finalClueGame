package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import clueBoard.Board;

public class ClueGame {
	private Solution theSolution;
	private Board theBoard;
	private ArrayList<Player> players;
	private Set<Card> deck;
	
	public ClueGame() {
		theBoard = new Board("ClueLayout.csv", "ClueLegend.txt");
		theBoard.loadConfigFiles();
		players = new ArrayList<Player>();
		deck = new HashSet<Card>();
		theSolution = new Solution();
	}

	public void deal() {
		selectAnswer();

		int counter = 0;
		Player currentPlayer;
		while (!deck.isEmpty()) {
			Card topCard = (Card) deck.toArray()[0];
			currentPlayer = players.get(counter % players.size());
			currentPlayer.addCard(topCard);
			deck.remove(topCard);
			counter++;
		}
	}

	public void loadDeck() {
		try {
			FileReader reader = new FileReader("ClueDeck.txt");
			Scanner inScanner = new Scanner(reader);
			try {
				while (inScanner.hasNextLine()) {
					String s = inScanner.nextLine();
					String[] queue = s.split(",");
					Card c = new Card(queue[0], queue[1]);
					deck.add(c);
				}
			} finally {
				inScanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void loadPlayers() {
		try {
			FileReader reader = new FileReader("CluePlayers.txt");
			Scanner inScanner = new Scanner(reader);
			try {
				boolean firstLoaded = true;
				while (inScanner.hasNextLine()) {
					String s = inScanner.nextLine();
					String[] queue = s.split(",");
					if (firstLoaded) {
						HumanPlayer h = new HumanPlayer(queue[0], queue[1],
								Integer.parseInt(queue[2]));
						players.add(h);
						firstLoaded = false;
					} else {
						ComputerPlayer c = new ComputerPlayer(queue[0],
								queue[1], Integer.parseInt(queue[2]));
						players.add(c);
					}
				}
			} finally {
				inScanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadConfigFiles() {
		loadDeck();
		loadPlayers();
	}

	public void selectAnswer() {
		Set<Card> toRemove = new HashSet<Card>();
		for (Card c : deck) {
			if (theSolution.person == null && c.cardType == CardType.PERSON) {
				theSolution.person = c.name;
				toRemove.add(c);
			} else if (theSolution.room == null && c.cardType == CardType.ROOM) {
				theSolution.room = c.name;
				toRemove.add(c);
			} else if (theSolution.weapon == null && c.cardType == CardType.WEAPON) {
				theSolution.weapon = c.name;
				toRemove.add(c);
			}
		}
		deck.removeAll(toRemove);
	}

	public Card handleSuggestion(Card person, Card room,Card weapon,
			Player accusingPerson) {
		for (Player p : players) {
			if (p != accusingPerson) {
				if (p.disproveSuggestion(person, room, weapon) != null)  {
					return p.disproveSuggestion(person, room, weapon);
				}
			}
		}

		return null;
	}

	public boolean checkAccusation(Solution sol) {
		if (sol.equals(theSolution)) {
			return true;
		}
		else {
			return false;
		}
	}

	public Player getPlayer(int n) {
		return players.get(n);
	}

	public int getNumPlayers() {
		return players.size();
	}

	/*-----------Getters and Setters for Testing Purposes ONLY ------*/

	
	public Set<Card> getDeck() {
		return deck;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setSolution(Solution s) {
		theSolution = s;
	}
	
	public Solution getSolution() {
		return theSolution;
	}
	
	public void setPlayers(ArrayList<Player> p) {
		players = p;
	}

}
