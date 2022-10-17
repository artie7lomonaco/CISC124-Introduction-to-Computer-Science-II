package chess;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Tour {
	/**
	 * Knight's current location
	 */
	private static Location knight;
	
	/**
	 * Collections of visited locations.
	 */
	private static List<Location> visited = new ArrayList<>();
	
	/**
	 * Initialize board dimensions. x is the width, y is the height of the board.
	 */
	public static final int x = 8;
	public static final int y = 8;
	
	/**
	 * Returns a list of possible moves starting from the specified
	 * location.
	 * @param from starting location
	 * @return a list of possible moves from the starting location.
	 */
	public static List<Location> moves(Location from){
		List<Location> result = new ArrayList<>();
		int [][] offset = {{-2, 1}, {-2, -1}, 
							{-1, 2}, {-1, -2}, 
							{2, 1}, {2, -1},
							{1, 2}, {1, -2}};
		for (int[] delta : offset) {
			Location newLoc = new Location(from.x() + delta[0], from.y() + delta[1]);	
			if (Tour.isValid(newLoc) && !visited.contains(newLoc)) {
				result.add(newLoc);
			}
		}
		return result;
	}
	
	/**
	 * Returns true if the specified location is valid on a 8x8 board.
	 * @param loc a location
	 * @return true if the specified location is valid on an 8x8 board and false otherwise.
	 */
	public static boolean isValid(Location loc) {
		if (loc.x() < 1 || loc.x() > x || loc.y() < 1 || loc.y() > y) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the location in the tour from the knight's current
	 * location. Returns null if there is no next move.
	 * @return
	 */
	
	public static Location next() {
		List<Location> moves = moves(knight);  
		if (moves.isEmpty()) {
			return null;
		}
		int currentSize = 9;
		Location moveChoice = new Location();
		for (Location loc : moves) {
			List<Location> nextMoves = moves(loc);
			for (int i = 0; i <= nextMoves.size(); i++) {
				if (nextMoves.size() < currentSize) {
						currentSize = nextMoves.size();
						moveChoice = loc;
				}
			}
		}
		knight = moveChoice;
		visited.add(moveChoice);
		return moveChoice;
	}
	
	/**
	 * Returns true if a point on the board has a next move,
	 * returns false if there is no move to go.
	 * @return
	 */
	public boolean hasNext() {
		if (moves(knight).size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param loc
	 */
	public void startTour(Location loc) {
		if (!Tour.isValid(loc)) {
			throw new IllegalArgumentException("Knight starting place must be within board dimensions");
		}
		knight = loc;
		visited.add(loc);
	}
	
	/**
	 * Main method to test out methods of the class Tour.
	 * @param args
	 */
	public static void main(String[] args) {
	}
}
