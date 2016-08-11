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
		
		currentpos = Robot.chevalmech.get();

	}

	@Override
	protected void execute() {
		
		Robot.chevalmech.set(!currentpos);
		
	}

	@Override
	protected boolean isFinished() {
		
		return Robot.chevalmech.get() == !currentpos;
	}

	@Override
	protected void end() {
		currentpos = Robot.chevalmech.get();

	}

	@Override
	protected void interrupted() {
		end();

	}

}
