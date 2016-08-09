package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
*
*/

public class FireBallShooter extends Command {
	
	double speed;
	
	public FireBallShooter(){
		requires(Robot.ballShooter);
		speed = .5;
	}
	
	//Startup the 
	@Override
	protected void initialize() {
		// Nothing
		
	}

	@Override
	protected void execute() {
		Robot.ballShooter.spinMotor(speed);		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//Check if the BreakBeam is completed and if it is then the ball is not 
		//inside anymore
		return false;
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
