package testingGround;

import java.awt.Color;

public interface ChessPiece {
	
	/**
	 * returns color of responding piece
	 * @return
	 */
	Colour getColour();
	
	/**
	 * gets the name of the responding piece
	 * @return
	 */
	PieceType getName();
	
	/**
	 * returns boolean value
	 * Parameters are coordinates on chess board
	 * @return
	 */
	boolean moveTo(Location l);
}
