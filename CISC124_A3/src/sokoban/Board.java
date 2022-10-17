package sokoban;

import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * A class that represents a Sokoban level board.
 * 
 * <p>
 * The class can read a Sokoban level from a plain text file where the following
 * symbols are used:
 * 
 * <ul>
 * <li>space is an empty square
 * <li># is a wall
 * <li>@ is the player
 * <li>$ is a box
 * <li>. is a storage location
 * <li>+ is the player on a storage location
 * <li>* is a box on a storage location
 * </ul>
 * 
 * <p>
 * The class manages a single {@code Player} object and a number of {@code Box},
 * {@code Wall}, and {@code Storage} objects. The class determines whether the
 * player can move to a specified square depending on the configuration of boxes
 * and walls.
 * 
 * <p>
 * The level is won when every box is moved to a storage location.
 * 
 * <p>
 * The class provides several methods that return information about a location
 * on the board .
 *
 */
public class Board {
	/*
	 * ADD SOME FIELDS HERE TO STORE THE WALLS, BOXES, AND STORAGE SITES
	 */

	private Player player;
	private int width;
	private int height;
	private List<Wall> wall;
	private List<Box> box;
	private List<Storage> storage;

	/**
	 * Initialize a board of width 11 and height 11 with a {@code Player} located at
	 * (4, 5), a {@code Box} located at (5, 5), and a storage location located at
	 * (6, 5).
	 */
	public Board() {
		this.width = 11;
		this.height = 11;
		this.wall = new ArrayList<Wall>();
		this.box = new ArrayList<Box>();
		this.storage = new ArrayList<Storage>();
		Box b = new Box(new Location(5, 5));
		Storage s = new Storage(new Location(6, 5));
		this.box.add(b);
		this.storage.add(s);
		this.player = new Player(new Location(3, 5));
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				if (i == 0 || j == 0 || i == this.width - 1 || j == this.height - 1) {
					Location wallLoc = new Location(i, j);
					Wall newWall = new Wall(wallLoc);
					wall.add(newWall);
				}
			}
		}
		// System.out.println((new Location(6, 5)).toString() + hasStorage(new
		// Location(6,5)));
	}

	/**
	 * Initialize a board by reading a level from the file with the specified
	 * filename.
	 * 
	 * <p>
	 * The height of the board is determined by the number of lines in the file. The
	 * width of the board is determined by the longest line in the file where
	 * trailing spaces in a line are ignored.
	 * 
	 * @param filename the filename of the level
	 * @throws IOException if the level file cannot be read
	 */
	public Board(String filename) throws IOException {
		this.wall = new ArrayList<Wall>();
		this.box = new ArrayList<Box>();
		this.storage = new ArrayList<Storage>();
		// call readLevel after initializing your fields
		this.readLevel(filename);
	}

	private final void readLevel(String filename) throws IOException {
		Path path = FileSystems.getDefault().getPath("src", "sokoban", filename);
		List<String> level = Files.readAllLines(path);
		this.height = level.size();
		this.width = 0;
		for (int y = 0; y < this.height; y++) {
			String row = level.get(y);
			if (row.length() > this.width) {
				this.width = row.length();
			}
			for (int x = 0; x < row.length(); x++) {
				// the location of this square
				Location loc = new Location(x, y);
				// the symbol at location (x, y)
				char c = row.charAt(x);
				if (c == ' ') {
					continue;
				} else if (c == '#') {
					// there is a wall here
					// make a Wall object and add it to your collection
					Wall w = new Wall(loc);
					this.wall.add(w);
				} else if (c == '@') {
					Player p = new Player(loc);
					this.player = p;
				} else if (c == '$') {
					// there is a box here
					// make a Box object and add it to your collection
					Box b = new Box(loc);
					this.box.add(b);
				} else if (c == '.') {
					// there is a storage site here
					// make a Storage object and add it to your collection
					Storage s = new Storage(loc);
					this.storage.add(s);
				} else if (c == '+') {
					Player p = new Player(loc);
					this.player = p;
					// there is also storage site here
					// make a Storage object and add it to your collection
					Storage s = new Storage(loc);
					this.storage.add(s);
				} else if (c == '*') {
					// there is a box and a storage site here
					// make a Box object and a Storage object and add them
					// to your collections
					Box b = new Box(loc);
					this.box.add(b);
					Storage s = new Storage(loc);
					this.storage.add(s);
				}
			}
		}
	}

	/**
	 * Returns the width of this board.
	 * 
	 * @return the width of this board
	 */
	public int width() {
		// ALREADY DONE FOR YOU
		return this.width;
	}

	/**
	 * Returns the height of this board.
	 * 
	 * @return the height of this board
	 */
	public int height() {
		// ALREADY DONE FOR YOU
		return this.height;
	}

	/**
	 * Returns a list of the walls in this board. The order of the walls is
	 * unspecified in the returned list.
	 * 
	 * @return a list of the walls in this board
	 */
	public List<Wall> getWalls() {
		return this.wall;
	}

	/**
	 * Returns a list of the boxes in this board. The order of the boxes is
	 * unspecified in the returned list.
	 * 
	 * @return a list of the boxes in this board
	 */
	public List<Box> getBoxes() {
		return this.box;
	}

	/**
	 * Returns a list of the storage locations in this board. The order of the
	 * storage locations is unspecified in the returned list.
	 * 
	 * @return a list of the storage locations in this board
	 */
	public List<Storage> getStorage() {
		return this.storage;
	}

	/**
	 * Returns the player.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		// ALREADY DONE FOR YOU
		return this.player;
	}

	/**
	 * Returns the {@code Box} object located at the specified location on the
	 * board, or {@code null} if there is no such object.
	 * 
	 * @param loc a location
	 * @return the box object located at the specified location on the board, or
	 *         {@code null} if there is no such object
	 */
	public Box getBox(Location loc) {
		for (int i = 0; i < box.size(); i++) {
			if (box.get(i).location() == loc) {
				return box.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns {@code true} if there is a wall, player, or box at the specified
	 * location, {@code false} otherwise. Note that storage locations are considered
	 * unoccupied if there is no player or box on the location.
	 * 
	 * @param loc a location
	 * @return {@code true} if there is a wall, player, or box at the specified
	 *         location, {@code false} otherwise
	 */
	public boolean isOccupied(Location loc) {
		for (int i = 0; i < wall.size(); i++) {
			if (wall.get(i).location().equals(loc)) {
				return true;
			}
		}
		for (int i = 0; i < box.size(); i++) {
			if (box.get(i).location().equals(loc)) {
				return true;
			}
		}
		if (player.location().equals(loc)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location is unoccupied or has only a
	 * storage location, {@code false} otherwise
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location is unoccupied or has only a
	 *         storage location, {@code false} otherwise
	 */
	public boolean isFree(Location loc) {
		if (isOccupied(loc) == false) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns {@code true} if the specified location has a wall on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a wall on it,
	 *         {@code false} otherwise
	 */
	public boolean hasWall(Location loc) {
		for (int i = 0; i < wall.size(); i++) {
			if (wall.get(i).location().equals(loc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a box on it, {@code false}
	 * otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a box on it, {@code false}
	 *         otherwise
	 */
	public boolean hasBox(Location loc) {
		for (int i = 0; i < box.size(); i++) {
			if (box.get(i).location().equals(loc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a storage location on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a storage location on it,
	 *         {@code false} otherwise
	 */
	public boolean hasStorage(Location loc) {
		for (int i = 0; i < storage.size(); i++) {
			if (storage.get(i).location().equals(loc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns {@code true} if the specified location has a player on it,
	 * {@code false} otherwise.
	 * 
	 * @param loc a location
	 * @return {@code true} if the specified location has a player on it,
	 *         {@code false} otherwise
	 */
	public boolean hasPlayer(Location loc) {
		if (player.location().equals(loc)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns {@code true} if every storage location has a box on it, {@code false}
	 * otherwise.
	 * 
	 * @return {@code true} if every storage location has a box on it, {@code false}
	 *         otherwise
	 */
	public boolean isSolved() {
		int c = 0;
		int size = storage.size();
		for (int i = 0; i < box.size(); i++) {
			for (int j = 0; j < box.size(); j++) {
				if (storage.get(i).location().equals(box.get(j).location())) {
					c += 1;
				}
			}
		}
		if (c == size) {
			return true;
		}
		return false;
	}

	/**
	 * Moves the player to the left adjacent location if possible. If there is a box
	 * in the left adjacent location then the box is pushed to the adjacent location
	 * left of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the left adjacent location
	 * (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the left adjacent location, false
	 *         otherwise
	 */

	public boolean movePlayerLeft() {
		Location pLoc = player.location();
		Location nextLoc = new Location(pLoc.x() - 1, pLoc.y());
		Location nextOver = new Location(pLoc.x() - 2, pLoc.y());
		if (isFree(nextLoc)) {
			player.moveLeft();
			return true;
		} else if (!isFree(nextLoc)) {
			if (hasBox(nextLoc) && isFree(nextOver)) {
				for (int i = 0; i < box.size(); i++) {
					if (box.get(i).location().equals(nextLoc)) {
						player.moveLeft();
						box.get(i).moveLeft();
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Moves the player to the right adjacent location if possible. If there is a
	 * box in the right adjacent location then the box is pushed to the adjacent
	 * location right of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the right adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the right adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerRight() {
		Location pLoc = player.location();
		Location nextLoc = new Location(pLoc.x() + 1, pLoc.y());
		Location nextOver = new Location(pLoc.x() + 2, pLoc.y());
		if (isFree(nextLoc)) {
			player.moveRight();
			return true;
		} else if (!isFree(nextLoc)) {
			if (hasBox(nextLoc) && isFree(nextOver)) {
				for (int i = 0; i < box.size(); i++) {
					if (box.get(i).location().equals(nextLoc)) {
						player.moveRight();
						box.get(i).moveRight();
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Moves the player to the above adjacent location if possible. If there is a
	 * box in the above adjacent location then the box is pushed to the adjacent
	 * location above the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the above adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the above adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerUp() {
		Location pLoc = player.location();
		Location nextLoc = new Location(pLoc.x(), pLoc.y() - 1);
		Location nextOver = new Location(pLoc.x(), pLoc.y() - 2);
		if (isFree(nextLoc)) {
			player.moveUp();
			return true;
		} else if (!isFree(nextLoc)) {
			if (hasBox(nextLoc) && isFree(nextOver)) {
				for (int i = 0; i < box.size(); i++) {
					if (box.get(i).location().equals(nextLoc)) {
						player.moveUp();
						box.get(i).moveUp();
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Moves the player to the below adjacent location if possible. If there is a
	 * box in the below adjacent location then the box is pushed to the adjacent
	 * location below of the box.
	 * 
	 * <p>
	 * Returns {@code false} if the player cannot move to the below adjacent
	 * location (leaving the player location unchanged).
	 * 
	 * @return true if the player is moved to the below adjacent location, false
	 *         otherwise
	 */
	public boolean movePlayerDown() {
		Location pLoc = player.location();
		Location nextLoc = new Location(pLoc.x(), pLoc.y() + 1);
		Location nextOver = new Location(pLoc.x(), pLoc.y() + 2);
		if (isFree(nextLoc)) {
			player.moveDown();
			return true;
		} else if (!isFree(nextLoc)) {
			if (hasBox(nextLoc) && isFree(nextOver)) {
				for (int i = 0; i < box.size(); i++) {
					if (box.get(i).location().equals(nextLoc)) {
						player.moveDown();
						box.get(i).moveDown();
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Returns a string representation of this board. The string representation is
	 * identical to file format describing Sokoban levels.
	 * 
	 * @return a string representation of this board
	 */
	@Override
	public String toString() {
		// ALREADY DONE FOR YOU
		StringBuilder b = new StringBuilder();
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				Location loc = new Location(x, y);
				// System.out.println(loc.toString() + hasStorage(loc) + " " +isFree(loc));
				if (this.isFree(loc)) {
					if (this.hasStorage(loc)) {

						b.append(".");
					} else {
						b.append(" ");
					}
				} else if (this.hasWall(loc)) {
					b.append("#");
				} else if (this.hasBox(loc)) {
					if (this.hasStorage(loc)) {
						b.append("*");
					} else {
						b.append("$");
					}
				} else if (this.hasPlayer(loc)) {
					if (this.hasStorage(loc)) {
						b.append("+");
					} else {
						b.append("@");
					}
				}
			}
			b.append('\n');
		}
		return b.toString();
	}

	public static void main(String[] args) throws IOException {
		// "level01.txt"
		Board b = new Board("level08.txt");
		System.out.println(b);
	}
}