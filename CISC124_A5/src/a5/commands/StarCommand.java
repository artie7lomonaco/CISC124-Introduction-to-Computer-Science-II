package a5.commands;

import java.awt.Color;

import a5.Turtle;

public class StarCommand extends CompositeCommand {
	protected double length;
	public StarCommand(Turtle t, double length) {
		super(t);
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		this.length = length;
		this.addCommand(new PenRadiusCommand(this.turtle, 3));
		this.addCommand(new PenColorCommand(this.turtle, Color.CYAN));
		this.addCommand(new CircleCommand(this.turtle, length * 0.53));
		this.addCommand(new TurnRightCommand(this.turtle, 72));
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 144.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 144.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 144.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
		this.addCommand(new TurnRightCommand(this.turtle, 144.0));
		this.addCommand(new ForwardCommand(this.turtle, length));
	}
	
	
}
