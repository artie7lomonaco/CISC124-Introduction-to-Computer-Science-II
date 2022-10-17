package a5.commands;

import a5.Turtle;

/**
 * Commands a turtle to draw a square of non-zero length.
 * The square edges are drawn in clockwise order with the
 * turtle moving forward from its current position and heading.
 *
 */
public class SquareCommand extends CompositeCommand {
	protected double length;
	public SquareCommand(Turtle t, double length) {
		super(t);
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		this.length = length;
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 90.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 90.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 90.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
	}
	
	
}
