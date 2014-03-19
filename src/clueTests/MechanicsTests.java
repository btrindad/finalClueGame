/*
 *These tests are to check the mechanics of the gameplay. Things like accusations,
 *suggestions, and player movement.
 **/
package clueTests;

import static org.junit.Assert.*;

import org.junit.*;

import clueGame.ClueGame;

public class MechanicsTests {
	ClueGame testGame;
	
	@BeforeClass
	public void setUp(){
		testGame =  new ClueGame();
		testGame.loadConfigFiles();
	}
	
	@Test
	public void testDeal(){
		
	}
	

}
