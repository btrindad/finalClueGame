/*
 *These tests are to check the mechanics of the gameplay. Things like accusations,
 *suggestions, and player movement.
 **/
package clueTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import clueGame.*;

public class MechanicsTests {
	private static ClueGame testGame;
	private final int SIZE_OF_HAND = 3;
	private final int NUM_CARDS_IN_PLAY = 18;
	
	@BeforeClass
	public static void setUp(){
		testGame =  new ClueGame();
		testGame.loadConfigFiles();
	}
	
	/*
	 * test that the deck is dealt out and that each player gets the same number of
	 * cards
	 */
	@Test
	public void testDeal(){
		testGame.deal();
		for(Player p : testGame.getPlayers()){
			assertEquals(p.getCards().size(), SIZE_OF_HAND);
		}
		assertTrue(testGame.getDeck().isEmpty());
	}
	
	/*
	 * test that players are not dealt any duplicates
	 */
	@Test
	public void testDeckDuplicates(){
		testGame.deal();
		Set<Card> newDeck = new HashSet<Card>();
		for(Player p : testGame.getPlayers()){
			newDeck.addAll(p.getCards());
		}
		assertEquals(newDeck.size(), NUM_CARDS_IN_PLAY);
	}
	
	// Test that checkAccusation returns true in response to an accusation that matches the solution
	@Test
	public void testCheckAccusationAllCorrect() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mr. White", "Candlestick", "Library");
		Solution temp = testGame.getSolution();
		testGame.setSolution(s);
		assertTrue(testGame.checkAccusation(accusation));
		testGame.setSolution(temp);
	}
	
	// Test that checkAccusation returns false in response to an accusation that matches the solution
	// except for an incorrect room
	@Test
	public void testCheckAccusationWrongRoom() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mr. White", "Candlestick", "Hall");
		Solution temp = testGame.getSolution();
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
		testGame.setSolution(temp);
	}
	
	// Test that checkAccusation returns false in response to an accusation that matches the solution
	// except for an incorrect person
	@Test
	public void testCheckAccusationWrongPerson() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mrs. Peacock", "Candlestick", "Library");
		Solution temp = testGame.getSolution();
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
		testGame.setSolution(temp);
	}
	
	// Test that checkAccusation returns false in response to an accusation that matches the solution
	// except for an incorrect weapon
	@Test
	public void testCheckAccusationWrongWeapon() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mr. White", "Knife", "Library");
		Solution temp = testGame.getSolution();
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
		testGame.setSolution(temp);
	}
	
	// Test that checkAccusation returns false in response to an accusation that in no way matches the solution
	@Test
	public void testCheckAccusationAllWrong() {
		Solution s = new Solution("Mr. White", "Candlestick", "Library");
		Solution accusation = new Solution("Mrs. Peacock", "Knife", "Hall");
		Solution temp = testGame.getSolution();
		testGame.setSolution(s);
		assertFalse(testGame.checkAccusation(accusation));
		testGame.setSolution(temp);
	}
	
	/*
	 * test the function that picks cards to push into the solution and
	 * removes them from the deck
	 */
	@Test
	public void testSelectAnswers(){
		setUp();
		assertEquals(testGame.getDeck().size(), NUM_CARDS_IN_PLAY + 3);
		testGame.selectAnswer();
		System.out.println(testGame.getSolution());
		assertEquals(testGame.getDeck().size(), NUM_CARDS_IN_PLAY);
		assertEquals(CardTests.countCards(CardType.PERSON, testGame), CardTests.PLAYER_CARDS-1);
		assertEquals(CardTests.countCards(CardType.WEAPON, testGame), CardTests.WEAPON_CARDS-1);
		assertEquals(CardTests.countCards(CardType.ROOM, testGame), CardTests.ROOM_CARDS-1);		
	}

}
