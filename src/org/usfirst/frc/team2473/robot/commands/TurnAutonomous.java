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
		Logger.getInstance().log(Robot.log, Double.toString(Robot.driveTrain.getAngle()));
//		turnAngle(ANGLE);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//return Robot.driveTrain.getAngle() == ANGLE;
		if(Robot.driveTrain.getAngle() == ANGLE) {
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
	
	private void turnAngle(double ang) {
		//if(Robot.driveTrain.getAngle() != ANGLE) {
		//	Robot.driveTrain.turnRight(0.2);
		//}
		if(!(Robot.driveTrain.getAngle() - ANGLE <=  5)) {
			Robot.driveTrain.turnRight(0.2);
		}
	}

}
