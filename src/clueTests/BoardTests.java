package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.RoomCell;

public class BoardTests {
	private static Board board;
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("ClueLayout.csv", "ClueLegend.txt");
		board.loadConfigFiles();
	}

	@Test
	public void roomLegendTest() {
		Map<Character, String> rooms = board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		assertEquals("Conservatory", rooms.get('C'));
		assertEquals("Library", rooms.get('L'));
		assertEquals("Lounge", rooms.get('O'));
		assertEquals("Ballroom", rooms.get('B'));
	}
	
	@Test
	public void testDimension() {
		assertEquals(NUM_ROWS, board.getRows());
		assertEquals(NUM_COLUMNS, board.getColumns());		
	}
	
	@Test
	public void testDoorDirections() {
		RoomCell room = board.getRoomCellAt(17, 20);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(12, 18);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(4, 21);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(1, 11);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(20, 12);
		assertFalse(room.isDoorway());	
		BoardCell cell = board.getCellAt(board.calcIndex(8, 6));
		assertFalse(cell.isDoorway());		

	}
	
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		int totalCells = board.getColumns() * board.getRows();
		assertEquals(506, totalCells);
		for (int i=0; i<totalCells; i++)
		{
			BoardCell cell = board.getCellAt(i);
			if (cell.isDoorway()) {
				numDoors++;
			}
		}
		assertEquals(9, numDoors);
	}
	
	@Test
	public void testCalcIndex() {
		assertEquals(0, board.calcIndex(0, 0));
		assertEquals(NUM_COLUMNS-1, board.calcIndex(0, NUM_COLUMNS-1));
		assertEquals(483, board.calcIndex(NUM_ROWS-1, 0));
		assertEquals(505, board.calcIndex(NUM_ROWS-1, NUM_COLUMNS-1));
		assertEquals(24, board.calcIndex(1, 1));
		assertEquals(66, board.calcIndex(2, 20));		
	}
	
	@Test
	public void testRoomInitials() {
		assertEquals('R', board.getRoomCellAt(0, 0).getInitial());
		assertEquals('L', board.getRoomCellAt(1, 9).getInitial());
		assertEquals('B', board.getRoomCellAt(21, 0).getInitial());
		assertEquals('O', board.getRoomCellAt(19, 11).getInitial());
		assertEquals('K', board.getRoomCellAt(5, 22).getInitial());
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		b.loadConfigFiles();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("ClueLayoutBadRoom.csv", "ClueLegend.txt");
		b.loadConfigFiles();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("ClueLayout.csv", "ClueLegendBadFormat.txt");
		b.loadConfigFiles();
	}

}
