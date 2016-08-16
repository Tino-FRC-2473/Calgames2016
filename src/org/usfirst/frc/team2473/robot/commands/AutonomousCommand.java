package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand() {
<<<<<<< HEAD
//		addSequential(new ForwardAutonomous());
//		addSequential(new TurnAutonomous());
		addSequential(new SensorReader());
//		addSequential(new ForwardAutonomous());
=======
		addSequential(new StraightPIDCommand(StraightPIDCommand.FOREVER, 0.5));
>>>>>>> c5aa69fc4918bacaa4cd4e16a3a4a14d694e652f
	}
	
	@Override
	protected boolean isFinished() {
		boolean returner = false;
		return returner;
	}
	
	
	
	
	
}
