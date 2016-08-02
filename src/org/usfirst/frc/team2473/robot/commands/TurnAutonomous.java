package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Logger;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAutonomous extends Command {
	
	private final double ANGLE;
	
	public TurnAutonomous() {
		// TODO Auto-generated constructor stub
		ANGLE = 90;
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		System.out.println("Angle: " + Math.floor(Robot.driveTrain.getAngle()));
//		Logger.getInstance().log(Robot.log, "Angle: " + Double.toString(Robot.driveTrain.getAngle()));
    	Robot.driveTrain.turnRight(0.6);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return Robot.driveTrain.getAngle() == ANGLE;
		if((Math.abs(Math.floor(Robot.driveTrain.getAngle()) - ANGLE)) == 0) {
			System.out.println("FINISHED!");
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
