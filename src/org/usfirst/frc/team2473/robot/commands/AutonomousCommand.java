package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand() {
//		addSequential(new ForwardAutonomous());
//		addSequential(new TurnAutonomous());
		addSequential(new SensorReader());
//		addSequential(new ForwardAutonomous());
		addSequential(new StraightPIDCommand(StraightPIDCommand.FOREVER, 0.5));
	}
	
	@Override
	protected boolean isFinished() {
		boolean returner = false;
		return returner;
	}
	
	
	
	
	
}