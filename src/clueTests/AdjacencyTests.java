package clueTests;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueBoard.Board;
import clueBoard.BoardCell;

public class AdjacencyTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board("ClueLayout.csv", "ClueLegend.txt");
		board.loadConfigFiles();
		board.calcAdjacencies();

	}

	//tests walkways that only are adjacent to walkways
	@Test
	public void testOnlyWalkways()
	{
		//upper mid walkway
		ArrayList<Integer> testList = board.getAdjList(board.calcIndex(3, 11));
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(3, 12)));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 10)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 11)));
		Assert.assertTrue(testList.contains(board.calcIndex(2, 11)));
		//mid right walkway
		testList = board.getAdjList(board.calcIndex(8, 18));
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(8, 19)));
		Assert.assertTrue(testList.contains(board.calcIndex(8, 17)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 18)));
		Assert.assertTrue(testList.contains(board.calcIndex(9, 18)));
	}
	//tests edges
	@Test
	public void testEdges()
	{
		//edge in room on left, no walkways
		ArrayList<Integer> testList = board.getAdjList(board.calcIndex(16, 0));
		Assert.assertEquals(0, testList.size());
		//edge on upper left, 2 walkways
		testList = board.getAdjList(board.calcIndex(8, 0));
		Assert.assertEquals(2, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(8, 1)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 0)));
		//top edge, no walkways
		testList = board.getAdjList(board.calcIndex(0, 11));
		Assert.assertEquals(0, testList.size());
		//right edge, 1 walkway, in a room
		testList = board.getAdjList(board.calcIndex(17, 22));
		Assert.assertEquals(0, testList.size());
	}
	//tests walkways by room cells that aren't doorways
	@Test
	public void testAdjacentWalls(){
		//Above room O
		ArrayList<Integer> testList = board.getAdjList(board.calcIndex(17, 12));
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(17, 13)));
		Assert.assertTrue(testList.contains(board.calcIndex(17, 11)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 12)));
		//Below Room C
		testList = board.getAdjList(board.calcIndex(13, 2));
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(13, 3)));
		Assert.assertTrue(testList.contains(board.calcIndex(13, 1)));
		Assert.assertTrue(testList.contains(board.calcIndex(14, 2)));
		//Next to Room R
		testList = board.getAdjList(board.calcIndex(0, 4));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(0, 5)));	
	}
	//tests locations adjacent to doorways
	@Test
	public void testAdjacentDoors(){
		//next to door direction up
		ArrayList<Integer> testList = board.getAdjList(board.calcIndex(16, 20));
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(16, 21)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 19)));
		Assert.assertTrue(testList.contains(board.calcIndex(15, 20)));
		Assert.assertTrue(testList.contains(board.calcIndex(17, 20)));
		//next to door direction left
		testList = board.getAdjList(board.calcIndex(8, 19));
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(8, 20)));
		Assert.assertTrue(testList.contains(board.calcIndex(8, 18)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 19)));
		Assert.assertTrue(testList.contains(board.calcIndex(9, 19)));
		//next to door direction down
		testList = board.getAdjList(board.calcIndex(19, 4));
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(19, 5)));
		Assert.assertTrue(testList.contains(board.calcIndex(19, 3)));
		Assert.assertTrue(testList.contains(board.calcIndex(18, 4)));
		Assert.assertTrue(testList.contains(board.calcIndex(20, 4)));
		//next to door direction right
		testList = board.getAdjList(board.calcIndex(12, 19));
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(12, 20)));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 18)));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 19)));
		Assert.assertTrue(testList.contains(board.calcIndex(13, 19)));
	}
	//tests doorways
	@Test
	public void testDoorways(){
		//doorway room R Right
		ArrayList<Integer> testList = board.getAdjList(board.calcIndex(4, 3));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(4, 4)));
		//doorway room K Left
		testList = board.getAdjList(board.calcIndex(4, 21));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(4, 20)));
		//doorway room D Up
		testList = board.getAdjList(board.calcIndex(17, 20));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(16, 20)));
		//doorway room B Down
		testList = board.getAdjList(board.calcIndex(18, 4));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(19, 4)));
	}
	//test targets along walkways at various distances
	@Test
	public void testTargetWalkways(){
		//upper right, one step
		board.startTargets(board.calcIndex(2, 16), 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(1, 16))));
		//upper left, 2 steps
		board.startTargets(board.calcIndex(2, 7), 2);
		targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(0, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(1, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(1, 6))));
		//middle left, 3 steps
		board.startTargets(board.calcIndex(11, 8), 3);
		targets= board.getTargets();
		Assert.assertEquals(12, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 7))));
		//bottom right, 5 steps
		board.startTargets(board.calcIndex(21, 17), 5);
		targets= board.getTargets();
		Assert.assertEquals(9, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 17))));
		//bottom middle, 2 steps
		board.startTargets(board.calcIndex(16, 13), 2);
		targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 12))));
	}
	//tests targets that can be in rooms
	@Test
	public void testRoomTargets(){
		//left above room C, 2 steps
		board.startTargets(board.calcIndex(8, 4), 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 2))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 3))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 5))));
		//bottom next to room O, 3 steps
		board.startTargets(board.calcIndex(18, 8), 3);
		targets= board.getTargets();
		Assert.assertEquals(12, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 8))));
		//right between rooms H and D
		board.startTargets(board.calcIndex(15, 20), 2);
		targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 22))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 21))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 18))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 21))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 20))));
	}
	//tests targets when leaving a room
	@Test
	public void testLeavingRoom(){
		//Room O door, left
		board.startTargets(board.calcIndex(19, 9), 4);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(10, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 8))));
		//room C door, Up
		board.startTargets(board.calcIndex(9, 4), 3);
		targets= board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 2))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 3))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 4))));
		//room H door, left
		board.startTargets(board.calcIndex(8, 20), 1);
		targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 19))));
	}
}