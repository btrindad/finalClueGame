/*
 * These tests are meant to check the ability to load players from a config file and
 * perform operations on player objects
 * */
package clueTests;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import clueBoard.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ClueGame;
import clueGame.Player;

public class PlayerTests {
	private static ClueGame testGame;
	
	private static Card mustardCard;
	private static Card knifeCard;
	private static Card plumCard;
	private static Card revolverCard;
	private static Card kitchenCard;
	private static Card libraryCard;
	
	@BeforeClass
	public static void setUp() {
		System.out.println("YOLO");
		testGame = new ClueGame();
		System.out.println("BAHAHAHA");
		testGame.loadConfigFiles();
		System.out.println("UNLIMITED POWER!!!");
		mustardCard = new Card("Colonel Mustard", CardType.PERSON);
		knifeCard = new Card("Knife", CardType.WEAPON);
		plumCard = new Card("Professor Plum", CardType.PERSON);
		revolverCard = new Card("Revolver", CardType.WEAPON);
		kitchenCard = new Card("Kitchen", CardType.ROOM);
		libraryCard = new Card("Library", CardType.ROOM);
		
	}
	
	@Test
	public void testNumPlayers() {
		Assert.assertEquals(6, testGame.getNumPlayers());
	}
	
	@Test
	public void testLoadingFirstPlayer() {
		Assert.assertEquals("Miss Scarlett", testGame.getPlayer(0).getName());
		Assert.assertEquals("Red", testGame.getPlayer(0).getColor());
		Assert.assertEquals(160, testGame.getPlayer(0).getStartingLocation());
		
	}
	
	@Test
	public void testLoadingPlayerInMiddle() {
		Assert.assertEquals("Mrs. White", testGame.getPlayer(2).getName());
		Assert.assertEquals("White", testGame.getPlayer(2).getColor());
		Assert.assertEquals(499, testGame.getPlayer(2).getStartingLocation());
	}
	
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
		
		for (int i = 0; i < 30; i++) { // 30? More/less?
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
	
	//@Test
	//public void testDisproveSuggestion_AllPlayersQueried() {
		
	//}
	
	
	
}
