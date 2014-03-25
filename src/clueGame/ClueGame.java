package clueGame;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import clueBoard.Board;
import clueBoard.BoardCell;

public class ClueGame extends JFrame {
	private Solution theSolution;
	private Board theBoard;
	private ArrayList<Player> players;
	private Set<Card> deck;
	
	/*
	 * a blank game, initialize all attributes
	 */
	public ClueGame() {
		theBoard = new Board("ClueLayout.csv", "ClueLegend.txt");
		theBoard.loadConfigFiles();
		players = new ArrayList<Player>();
		deck = new HashSet<Card>();
		theSolution = new Solution();
		setSize(BoardCell.boardWidth, BoardCell.boardHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(theBoard, BorderLayout.CENTER);
	}

	/*
	 * deal a deck to the players, afterwards the deck is empty and each player has about
	 * the same number of cards
	 */
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

	/*
	 * load the cards in the deck from a text file
	 */
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

	/*
	 * load the players in a game and their attributes from a text file
	 */
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

	/*
	 * call all the config methods to load initial data into the game
	 */
	public void loadConfigFiles() {
		loadDeck();
		loadPlayers();
	}

	/*
	 * remove one card of each type from the deck and set as the solution. 
	 * NOTE: this function should be run before giving any cards to players
	 */
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

	/*
	 * when any player makes a suggestion, go to the other players and ask them to disprove
	 * it if they have a card from the suggestion
	 */
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

	/*
	 * when a player makes an accusation, check and see if it matches the solution
	 * true if the accusation was correct, otherwise false
	 */
	public boolean checkAccusation(Solution sol) {
		if (sol.equals(theSolution)) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * a getter, return a player at index n from the collection of players
	 */
	public Player getPlayer(int n) {
		return players.get(n);
	}

	/*
	 * get the number of players currently in the game
	 */
	public int getNumPlayers() {
		return players.size();
	}
	
	public static void main(String[] args) {
		ClueGame mainGame = new ClueGame();
		mainGame.setVisible(true);
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
