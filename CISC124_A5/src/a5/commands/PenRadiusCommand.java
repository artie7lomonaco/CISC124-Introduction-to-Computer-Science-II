package a5.commands;

import a5.Turtle;

/**
 * Command that changes the pen radius.
 */
public class PenRadiusCommand extends Command {
	protected float r;
	public PenRadiusCommand(Turtle t, float r) {
		super(t);
		this.r = r;
	}
	public double getRadius() {
		return this.r;
	}
	public void execute() {
		this.turtle.setPenRadius(this.r);
	}
}
