package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveChevalMech extends Command {

	boolean currentpos;
	
	public MoveChevalMech() {
		requires(Robot.chevalmech);
	}
	
	@Override
	protected void initialize() {
		
		currentpos = Robot.chevalmech.get(); // Gets the current position of the piston.

	}

	@Override
	protected void execute() {
		
		Robot.chevalmech.set(!currentpos); // Changes position of piston.
		
	}

	@Override
	protected boolean isFinished() {
		
		return Robot.chevalmech.get() == !currentpos; // Check if the position if fully changed
	}

	@Override
	protected void end() {
		currentpos = Robot.chevalmech.get(); //Reset Current Position

	}

	@Override
	protected void interrupted() {
		end();

	}

}
