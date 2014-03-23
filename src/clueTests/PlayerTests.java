/*
 * These tests are meant to check the ability to load players from a config file and
 * perform operations on player objects
 * */
package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import clueBoard.Board;
import clueBoard.BoardCell;
import clueBoard.RoomCell;
import clueBoard.WalkwayCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class PlayerTests {
	private static ClueGame testGame;
	
	// these will be needed later
	private static Card mustardCard;
	private static Card knifeCard;
	private static Card plumCard;
	private static Card revolverCard;
	private static Card kitchenCard;
	private static Card libraryCard;
	private static Card ballroomCard;
	private static Card studyCard;
	private static Card greenCard;
	private static Card whiteCard;
	private static Card ropeCard;
	private static Card wrenchCard;
	
	@BeforeClass
	public static void setUp() {
		testGame = new ClueGame();
		testGame.loadConfigFiles();
		mustardCard = new Card("Colonel Mustard", CardType.PERSON);
		knifeCard = new Card("Knife", CardType.WEAPON);
		plumCard = new Card("Professor Plum", CardType.PERSON);
		revolverCard = new Card("Revolver", CardType.WEAPON);
		kitchenCard = new Card("Kitchen", CardType.ROOM);
		libraryCard = new Card("Library", CardType.ROOM);
		ballroomCard = new Card("Ballroom", CardType.ROOM);
		studyCard = new Card("Study", CardType.ROOM);
		greenCard = new Card("Mr. Green", CardType.PERSON);
		whiteCard = new Card("Mrs. White", CardType.PERSON);
		ropeCard = new Card("Rope", CardType.WEAPON);
		wrenchCard = new Card("Wrench", CardType.WEAPON);
		
	}
	
	// Test that the number of players loaded is correct
	@Test
	public void testNumPlayers() {
		Assert.assertEquals(6, testGame.getNumPlayers());
	}
	
	// Test that the first player stored in the file is loaded correctly
	@Test
	public void testLoadingFirstPlayer() {
		Assert.assertEquals("Miss Scarlett", testGame.getPlayer(0).getName());
		Assert.assertEquals("Red", testGame.getPlayer(0).getColor());
		Assert.assertEquals(160, testGame.getPlayer(0).getStartingLocation());
		
	}
	
	// Test that a player near the middle of the file is loaded correctly
	@Test
	public void testLoadingPlayerInMiddle() {
		Assert.assertEquals("Mrs. White", testGame.getPlayer(2).getName());
		Assert.assertEquals("White", testGame.getPlayer(2).getColor());
		Assert.assertEquals(499, testGame.getPlayer(2).getStartingLocation());
	}
	
	// Test that the last player in the file is loaded correctly
	@Test
	public void testLoadingLastPlayer() {
		Assert.assertEquals("Professor Plum", testGame.getPlayer(5).getName());
		Assert.assertEquals("Purple", testGame.getPlayer(5).getColor());
		Assert.assertEquals(13, testGame.getPlayer(5).getStartingLocation());
	}
	
	@Test
	public void testDisproveSuggestion_OnePlayerOneMatch() {
		Player testPlayer = new Player("TEST PLAYER", "ORANGE", 0);
		Card personCard = new Card("Mrs. White", CardType.PERSON);
		Card roomCard = new Card("Study", CardType.ROOM);
		Card weaponCard = new Card("Revolver", CardType.WEAPON);
		testPlayer.addCard(mustardCard);
		testPlayer.addCard(knifeCard);
		testPlayer.addCard(libraryCard);
		testPlayer.addCard(kitchenCard);
		testPlayer.addCard(revolverCard);
		testPlayer.addCard(plumCard);
		Assert.assertEquals(revolverCard, testPlayer.disproveSuggestion(personCard, roomCard, weaponCard));
		weaponCard = new Card("Rope", CardType.WEAPON);
		roomCard = new Card("Library", CardType.ROOM);
		Assert.assertEquals(libraryCard, testPlayer.disproveSuggestion(personCard, roomCard, weaponCard));
		roomCard = new Card("Study", CardType.ROOM);
		personCard = new Card("Colonel Mustard", CardType.PERSON);
		Assert.assertEquals(mustardCard, testPlayer.disproveSuggestion(personCard, roomCard, weaponCard));
		personCard = new Card("Mrs. White", CardType.PERSON);
		Assert.assertEquals(null, testPlayer.disproveSuggestion(personCard, roomCard, weaponCard));
	}
	
	@Test
	public void testDisproveSuggestion_OnePlayerMultipleMatches() {
		Player testPlayer = new Player("TEST PLAYER", "ORANGE", 0);
		Set<Card> testSet = new HashSet<Card>();
		testPlayer.addCard(mustardCard);
		testPlayer.addCard(knifeCard);
		testPlayer.addCard(libraryCard);
		Card personCard = new Card("Colonel Mustard", CardType.PERSON);
		Card roomCard = new Card("Library", CardType.ROOM);
		Card weaponCard = new Card("Knife", CardType.WEAPON);		
		testSet.add(personCard);
		testSet.add(roomCard);
		testSet.add(weaponCard);
		int numCardOne = 0;
		int numCardTwo = 0;
		int numCardThree = 0;
		for (int i = 0; i < 100; i++) {
			//Assert.assertTrue(testSet.contains(testPlayer.disproveSuggestion(personCard, roomCard, weaponCard))); ?
			Card returnedCard = testPlayer.disproveSuggestion(personCard, roomCard, weaponCard);
			if (returnedCard.equals(mustardCard)) {
				numCardOne++;
			}
			else if (returnedCard.equals(libraryCard)) {
				numCardTwo++;
			}
			else if (returnedCard.equals(knifeCard)) {
				numCardThree++;
			}
			else {
				Assert.fail();
			}
		}
		
		Assert.assertTrue(numCardOne > 0 && numCardTwo > 0 && numCardThree > 0);
		
	}
	
	@Test
	public void testDisproveSuggestion_AllPlayersQueried() {
		ArrayList<Player> testPlayers = new ArrayList<Player>();
		HumanPlayer H = new HumanPlayer("HUMAN TEST PLAYER", "ORANGE", 0);
		testPlayers.add(H);
		for (int i = 0; i < 2; i++) {
			Player p = new Player("TEST COMP PLAYER", "BLACK", i + 1);
			testPlayers.add(p);
		}
		
		testPlayers.get(0).addCard(mustardCard);
		testPlayers.get(0).addCard(revolverCard);
		testPlayers.get(0).addCard(studyCard);
		testPlayers.get(1).addCard(plumCard);
		testPlayers.get(1).addCard(kitchenCard);
		testPlayers.get(1).addCard(knifeCard);
		testPlayers.get(2).addCard(whiteCard);
		testPlayers.get(2).addCard(wrenchCard);
		testPlayers.get(2).addCard(libraryCard);
		
		for (Player p : testPlayers) {
			Assert.assertNull(p.disproveSuggestion(greenCard, ballroomCard, ropeCard));
		}
		
		assertEquals(mustardCard, testPlayers.get(0).disproveSuggestion(mustardCard, ballroomCard, ropeCard));
		
		ClueGame testGame = new ClueGame();
		testGame.setPlayers(testPlayers);
		Assert.assertNull(testGame.handleSuggestion(mustardCard, revolverCard, studyCard, testPlayers.get(0)));
		
		Assert.assertEquals(knifeCard, testGame.handleSuggestion(whiteCard, ballroomCard, knifeCard, testPlayers.get(0)));
		Assert.assertEquals(libraryCard, testGame.handleSuggestion(greenCard, libraryCard, ropeCard, testPlayers.get(0)));
	}
	
	@Test
	public void testCreateSuggestion() {
		ComputerPlayer c = new ComputerPlayer("COMPUTER PLAYER", "BLACK", 34);
		c.clearAllCards();
		c.updateAllCards(kitchenCard, true);
		c.updateAllCards(libraryCard, true);
		c.updateAllCards(plumCard, true);
		c.updateAllCards(knifeCard, true);
		c.updateAllCards(mustardCard, false);
		c.updateAllCards(revolverCard, false);
		
		Solution testSuggestion = new Solution(mustardCard, revolverCard, libraryCard);
		System.out.println(testSuggestion);
		System.out.println(c.createSuggestion('L'));
		c.printAllCards();
		Assert.assertEquals(testSuggestion, c.createSuggestion('L'));
	}
	
	@Test
	public void testTargetSelectionRoom() {
		Board B = new Board("ClueLayout.csv", "ClueLegend.txt");
		B.loadConfigFiles();
		B.calcAdjacencies();
		B.calcTargets(B.calcIndex(4, 4), 1);
		ComputerPlayer testCP = new ComputerPlayer("TEST", "CP", 0);
		testCP.setLastRoomVisited('L');
		
		for (int i = 0; i < 100; i++) {
			RoomCell temp = (RoomCell)testCP.pickLocation(B.getTargets());
			assertEquals(B.getRoomCellAt(4, 3), temp);
		}
	}
	
	@Test
	public void testTargetSelectionNoRooms() {
		Board B = new Board("ClueLayout.csv", "ClueLegend.txt");
		B.loadConfigFiles();
		B.calcAdjacencies();
		B.calcTargets(B.calcIndex(21, 5), 1);
		ComputerPlayer testCP = new ComputerPlayer("TEST", "CP", 0);
		
		int cell21_4 = 0;
		int cell20_5 = 0;
		int cell21_6 = 0;
		
		for (int i = 0; i < 100; i++) {
			BoardCell temp = testCP.pickLocation(B.getTargets());
			if (temp == B.getCellAt(B.calcIndex(21, 4))) {
				cell21_4++;
			}
			else if (temp == B.getCellAt(B.calcIndex(20, 5))) {
				cell20_5++;
			}
			else if (temp == B.getCellAt(B.calcIndex(21, 6))) {
				cell21_6++;
			}
			else {
				fail("Invalid target selected.");
			}
		}
		
		assertEquals(100, cell21_4 + cell20_5 + cell21_6);
		assertTrue(cell21_4 > 10);
		assertTrue(cell20_5 > 10);
		assertTrue(cell21_6 > 10);
	}
	
	@Test
	public void testTargetsLastRoom() {
		Board B = new Board("ClueLayout.csv", "ClueLegend.txt");
		B.loadConfigFiles();
		B.calcAdjacencies();
		B.calcTargets(B.calcIndex(2, 11), 1);
		ComputerPlayer testCP = new ComputerPlayer("TEST", "CP", 0);
		testCP.setLastRoomVisited('L');
		
		int room1_11 = 0;
		int cell2_10 = 0;
		int cell2_12 = 0;
		int cell3_11 = 0;

		for (int i = 0; i < 100; i++) {
			BoardCell temp = testCP.pickLocation(B.getTargets());
			if (temp == B.getCellAt(B.calcIndex(2, 10))) {
				cell2_10++;
			}
			else if (temp == B.getCellAt(B.calcIndex(2, 12))) {
				cell2_12++;
			}
			else if (temp == B.getCellAt(B.calcIndex(3, 11))) {
				cell3_11++;
			}
			else if (temp == B.getCellAt(B.calcIndex(1, 11))) {
				room1_11++;
			}
			else {
				fail("Invalid target selected.");
			}
		}
		
		assertEquals(100, cell2_10 + cell2_12 + cell3_11 + room1_11);
		assertTrue(cell2_10 > 10);
		assertTrue(cell2_12 > 10);
		assertTrue(cell3_11 > 10);
		assertTrue(room1_11 > 10);
		
	}
	
}
