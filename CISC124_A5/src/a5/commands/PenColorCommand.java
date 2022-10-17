package a5.commands;

import java.awt.Color;
import java.util.Objects;

import a5.Turtle;

/**
 * Command that changes the pen color.
 */
public class PenColorCommand extends Command {
	protected Color c;
	public PenColorCommand(Turtle t, Color c) {
		super(t);
		this.c = c;
	}
	public Color getColor() {
		return this.c;
	}
	public void execute() {
		this.turtle.setPenColor(this.c);
	}
}
