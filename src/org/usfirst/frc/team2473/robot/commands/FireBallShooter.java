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
	private DigitalInput breakbeam;
	
	public FireBallShooter(){
		requires(Robot.ballShooter);
		percentageOfSpeedOnMotor = .5;
		breakbeam = new DigitalInput(RobotMap.breakbeamChannel);
	}
	
	//Startup the 
	@Override
	protected void initialize() {
		// Nothing
		
	}

	@Override
	protected void execute() {
		Robot.ballShooter.spinMotor(percentageOfSpeedOnMotor);		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//Check if the BreakBeam is completed and if it is then the ball is not 
		//inside anymore
		return !breakbeam.get();
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
