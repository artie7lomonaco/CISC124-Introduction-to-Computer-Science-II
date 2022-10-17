package a5.commands;

import a5.Turtle;

/**
 * Command that walks the turtle forwards.
 *
 */
public class ForwardCommand extends WalkCommand {
	public ForwardCommand(Turtle t, double distance) {
		super(t, distance);
	}
	public void execute() {
		this.turtle.forward(distance);
	}
}