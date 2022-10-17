package testingGround;

public abstract class Piece implements ChessPiece{
	protected PieceType p;
	protected Colour c;
	protected Location l;
	public Piece(PieceType p, Location l, Colour c) {
		this.p = p;
		this.c = c;
		this.l = l;
	}
	public Colour getColour() {
		return this.c;
	}
	public PieceType getName() {
		return this.p;
	}
	public Location getLocation() {
		return this.l;
	}
	public boolean moveTo(Location l) {
		if (this.l.x() > 8 || this.l.y() > 8 || this.l.x() < 0 || this.l.y() < 0) {
			throw new IllegalArgumentException("Location is out of bounds on board");
		}
		this.l = l;
		return true;
	}
}
