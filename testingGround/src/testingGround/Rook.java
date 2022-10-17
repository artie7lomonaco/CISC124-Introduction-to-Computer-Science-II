package testingGround;

public class Rook extends Piece{
	public Rook (PieceType n, Colour c, Location l) {
		super(n,l,c);
	}
	public Location moveTo(int dist, String dir) {
		Location newLoc = new Location(this.l.x(),this.l.x() + dist);
		if (dir.equals("UP")) {
			newLoc = new Location(this.l.x(),this.l.x() + dist);
			if (moveTo(newLoc)) {
				this.l = newLoc;
			}
			
		}
		if (dir.equals("DOWN")) {
			newLoc = new Location(this.l.x(),this.l.x() - dist);
			if (moveTo(newLoc)) {
				this.l = newLoc;
			}
		}
		if (dir.equals("LEFT")) {
			newLoc = new Location(this.l.x() - dist,this.l.x());
			if (moveTo(newLoc)) {
				this.l = newLoc;
			}
		}
		if (dir.equals("RIGHT")) {
			newLoc = new Location(this.l.x() + dist,this.l.x());
			if (moveTo(newLoc)) {
				this.l = newLoc;
			}
		}
		return this.l;
	}
	
	public String toString() {
		return "Name: ROOK, Location: " + this.l.toString() + ", Colour: " + this.c;
	}
	
}
