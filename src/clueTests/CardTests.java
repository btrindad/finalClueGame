/*
 * These tests are meant to check the ability to load and perform operations on card
 * objects
 * */
package clueTests;

import org.junit.*;

import clueGame.ClueGame;
import static org.junit.Assert.*;

public class CardTests {
	private ClueGame game;
	
	@BeforeClass
	public void setUp(){
		game = new ClueGame();
		game.loadConfigFiles();
	}
}
