package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import GUI.ControlGUI;
import GUI.MyCardsPanel;
import GUI.NotesDialog;
import clueBoard.Board;

public class ClueGame extends JFrame {
	private Solution theSolution;
	private Board theBoard;
	private ArrayList<Player> players;
	private Set<Card> deck;
	private boolean gameOver = false;
	private boolean turnFinished = false;
	private int die = 0;
	private Random gen = new Random();
	
	//GUI attributes
	private NotesDialog notesDialog;
	private JMenuBar menuBar;
	private ControlGUI controller;
	private MyCardsPanel myCards;
	
	/*
	 * a blank game, initialize all attributes
	 */
	public ClueGame() {
		//initialize game attributes
		theBoard = new Board("ClueLayout.csv", "ClueLegend.txt");
		theBoard.loadConfigFiles();
		theBoard.calcAdjacencies();
		players = new ArrayList<Player>();
		deck = new HashSet<Card>();
		theSolution = new Solution();
		controller = new ControlGUI();
		
		//set size of the screen and basic options
		setSize(theBoard.getBoardWidth() + MyCardsPanel.WIDTH, 
				theBoard.getBoardHeight() + controller.getHeight());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//build the display
		add(theBoard, BorderLayout.CENTER);
		notesDialog = new NotesDialog();
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		controller.setWidth(getWidth());
		add(controller, BorderLayout.SOUTH);
		controller.nextPlayer.addActionListener(new ButtonListener());
		controller.makeAccusation.addActionListener(new ButtonListener());
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
		for(Player p : players){
			theBoard.addPlayerMarker(p);
		}
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
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createShowNotesItem());
		menu.add(createFileExitItem());
		return menu;
	}
	
	private JMenuItem createShowNotesItem() {
		JMenuItem notesItem  = new JMenuItem("Show Notes");
		class NotesItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				notesDialog.setVisible(true);
			}
		}
		notesItem.addActionListener(new NotesItemListener());
		return notesItem; 
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem exitItem = new JMenuItem("Exit");
		class ExitItemListener implements ActionListener {
		    public void actionPerformed(ActionEvent e)
		    {
		       System.exit(0);
		    }
		  }
		  exitItem.addActionListener(new ExitItemListener());
		  return exitItem;
	}
	
	public void playThrough(){
		Card suggestSol = null;
		while (gameOver == false){
			for (int i = 0; i < players.size(); i++){
				turnFinished = false;
				dieRoll();
				controller.setDieRoll(die);
				controller.setTurn(players.get(i).getName());
				theBoard.startTargets(players.get(i).currentLocation, die);
				if (i == 0){
					((HumanPlayer) players.get(i)).makeMove(theBoard);
				}else {
					if(((ComputerPlayer) players.get(i)).makeAcc == true){
						if (checkAccusation(((ComputerPlayer) players.get(i)).solution)){
							gameOver = true;
							JOptionPane.showMessageDialog(theBoard, players.get(i).getName() + " is the winner.", 
									"Winner", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					((ComputerPlayer) players.get(i)).makeMove(theBoard);
					if(((ComputerPlayer)players.get(i)).solution != null){
						suggestSol = handleSuggestion(((ComputerPlayer)players.get(i)).solution.personCard,
							((ComputerPlayer)players.get(i)).solution.roomCard, 
							((ComputerPlayer)players.get(i)).solution.weaponCard, players.get(i));
					}else{
						suggestSol = null;
					}
					if(suggestSol != null){
						((ComputerPlayer)players.get(i)).updateSeen(suggestSol, false);
						controller.setResult(suggestSol.name);
					}else {
						controller.setResult("");
					}
					if(((ComputerPlayer)players.get(i)).solution != null){
						controller.setGuess(((ComputerPlayer)players.get(i)).solution, false);
					}else{
						controller.setGuess(((ComputerPlayer)players.get(i)).solution, true);
					}
					
					if(suggestSol == null && ((ComputerPlayer)players.get(i)).solution != null){
						((ComputerPlayer) players.get(i)).makeAcc = true;
					}
				}
				while(turnFinished == false){
					
				}
			}
		}
	}
	
	public void dieRoll(){
		die = gen.nextInt(6)+1;
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == controller.nextPlayer){
				if(((HumanPlayer) players.get(0)).humanMustFinish == false){
					turnFinished = true;
				}
			} else {
				if(((HumanPlayer) players.get(0)).accuseTime == false){
					JOptionPane.showMessageDialog(null, "Can't accuse right now.", 
							"Fail", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	//draw human player's card panel
	public void drawCardPanel(){
		myCards = new MyCardsPanel(players.get(0).getCards());
		myCards.display(theBoard);
		add(myCards, BorderLayout.EAST);
	}
	
	// MAIN
	public static void main(String[] args) {
		ClueGame mainGame = new ClueGame();
		mainGame.loadConfigFiles();
		mainGame.deal();
		mainGame.drawCardPanel();
		mainGame.setVisible(true);
		JOptionPane.showMessageDialog(mainGame, "You are Miss Scarlett, press next player to begin.", 
				"Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);
		
		mainGame.playThrough();
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
