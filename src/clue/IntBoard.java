package clue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private static final int BOARD_ROWS = 4;
	private static final int BOARD_COLUMNS = 4;
	
	private Map<Integer, ArrayList<Integer>> adjMtx;
	private Set<Integer> targets;
	private boolean[] visited;
	
	public IntBoard() {
		targets = new HashSet<Integer>();
		visited = new boolean[BOARD_ROWS * BOARD_COLUMNS];
		
	}
	
	public void calcAdjacencies() {
		adjMtx = new HashMap<Integer, ArrayList<Integer>>();
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				int index = calcIndex(row, column);
				adjMtx.put(index, new ArrayList<Integer>());
				// ABOVE
				if (row > 0) {
					adjMtx.get(index).add(calcIndex(row - 1, column));
				}
				// BELOW
				if (row < (BOARD_ROWS - 1)) {
					adjMtx.get(index).add(calcIndex(row + 1, column));
				}
				// LEFT
				if (column > 0) {
					adjMtx.get(index).add(calcIndex(row, column - 1));
				}
				// RIGHT
				if (column < (BOARD_COLUMNS - 1)) {
					adjMtx.get(index).add(calcIndex(row, column + 1));
				}
			}
		}
		
	}
	
	public void startTargets(int location, int distance) {
		// Setup
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		if (adjMtx.isEmpty()) {
			calcAdjacencies();
		}
		targets.clear();
		visited[location] = true;
		calcTargets(location, distance);
	}
	
	private void calcTargets(int location, int distance) {
		ArrayList<Integer> adjacentCells = new ArrayList<Integer>();
		for (Integer cell : getAdjList(location)) {
			if (!visited[cell]) {
				adjacentCells.add(cell);
			}
		}
		for (Integer cell : adjacentCells) {
			visited[cell] = true;
			if (distance == 1) {
				targets.add(cell);
			}
			else {
				calcTargets(cell, (distance - 1));
			}
			visited[cell] = false;
		}
		
	}
	
	public Set<Integer> getTargets() {
		return targets;
	}
	
	public ArrayList<Integer> getAdjList(int location) {
		return adjMtx.get(location);
	}
	
	public int calcIndex(int row, int column) {
		return (4 * row) + column;
	}

}
