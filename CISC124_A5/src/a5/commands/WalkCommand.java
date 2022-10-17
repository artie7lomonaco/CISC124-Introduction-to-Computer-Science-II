package a5.commands;

import a5.Turtle;

/**
 * Abstract base class for all commands that cause a turtle to
 * walk in a straight line.
 *
 */
public abstract class WalkCommand extends Command {
	protected double distance;
	public WalkCommand(Turtle t, double distance) {
		super(t);
		if (distance < 0) {
			throw new IllegalArgumentException();
		}
		this.distance = distance;
	}
	public double getDistance() {
		return this.distance;
	}
}