package a5.commands;

import a5.Turtle;

/**
 * Commands a turtle to draw a circle of non-zero radius.
 * The circle is drawn in clockwise order starting from the
 * turtle's current position. After drawing the circle, the
 * turtle returns to its position and heading that it had
 * immediately before drawing the circle.
 * 
 * <p>
 * A turtle can draw only straight lines so drawing a circle
 * involves drawing a sequence of straight lines to approximate
 * a circle. 
 *
 */
public class CircleCommand extends CompositeCommand {
	protected double r;
	public CircleCommand(Turtle t, double r) {
		super(t);
		if (r <= 0) {
			throw new IllegalArgumentException();
		}
		this.r = r;
		double theta = 5 * (Math.PI / 180);
		double tmp = ((r * r) + (r * r)) - (2 * r * r * Math.cos(theta));
		double c = Math.sqrt(tmp);
		this.addCommand(new TurnRightCommand(this.turtle, 2.5));
		for (int i = 0; i < 72; i++) {
			this.addCommand(new ForwardCommand(this.turtle, c));
			this.addCommand(new TurnRightCommand(this.turtle, 5));
		}
		this.addCommand(new TurnLeftCommand(this.turtle, 2.5));
	}
	public double getRadius() {
		return this.r;
	}
}
