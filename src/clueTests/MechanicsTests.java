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
	
	@Test
	public void testSelectAnswers(){
		testGame.selectAnswer();
		assertEquals(testGame.getDeck().size(), NUM_CARDS_IN_PLAY);
		assertEquals(CardTests.countCards(CardType.PERSON), CardTests.PLAYER_CARDS-1);
		assertEquals(CardTests.countCards(CardType.WEAPON), CardTests.WEAPON_CARDS-1);
		assertEquals(CardTests.countCards(CardType.ROOM), CardTests.ROOM_CARDS-1);
				
	}

}
