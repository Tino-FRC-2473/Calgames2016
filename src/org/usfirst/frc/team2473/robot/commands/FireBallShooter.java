package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
*
*/

public class FireBallShooter extends Command {
	
	double percentageOfSpeedOnMotor;
	
		timeOut = duration;
		requires(Robot.ballShooter);
		percentageOfSpeedOnMotor = -1.0;
	}
	
	//Startup the 
	@Override
	protected void initialize() {
		// Nothing
	}

	@Override
	protected void execute() {
		Robot.ballShooter.spinMotor(percentageOfSpeedOnMotor);	
		setTimeout(timeOut);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.ballShooter.spinMotor(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

	
	
}
