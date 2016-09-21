package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.AutonomousConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand() {
		addSequential(new ForwardAutonomous(2));
	}

	public AutonomousCommand(String defense) {
		// addSequential(new ForwardAutonomous(2));
		// addSequential(new CrossDefense(defense));
		// addSequential(new ForwardAutonomous(5));
		// addSequential(new Align(AutonomousConstants.line));
		// addSequential(new ForwardAutonomous(5));
		// addSequential(new TurnAutonomous("right", 50));
		// addSequential(new ForwardAutonomous(15));
		// addSequential(new ForwardAutonomous(2, 0.3));
		// addSequential(new FireBallShooter());
		// addSequential(new BackwardAutonomous(17));
		// addSequential(new TurnAutonomous("left", 0));
		// addSequential(new BackwardAutonomous(5));
	}

	@Override
	protected boolean isFinished() {
		boolean returner = false;
		return returner;
	}

}
