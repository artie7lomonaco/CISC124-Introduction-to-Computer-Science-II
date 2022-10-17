package a5.commands;

import a5.Turtle;
import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

/**
 * Command that teleports the turtle to a position.
 *
 */
public class TeleportCommand extends Command {
	private Point2 location;
	public TeleportCommand(Turtle t, Point2 location) {
		super(t);
		this.location = new Point2(location);
	}
	public void execute() {
		this.turtle.teleport(this.location);
	}
}