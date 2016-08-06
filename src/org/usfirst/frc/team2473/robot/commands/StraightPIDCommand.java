package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StraightPIDCommand extends Command {

	double left_previous, right_previous, dL, dR, pow, pow_r, pow_l, total, k;
	
	public StraightPIDCommand() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		left_previous = 0;
		right_previous = 0;
		dL = 0;
		dR = 0;
		pow = 0.1;
		pow_r = pow;
		pow_l = pow;
		total = 0;
		k = 0.1;
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		dL = Robot.driveTrain.getLeftPosition() - left_previous;
		dR = Robot.driveTrain.getRightPosition() - right_previous;
		left_previous = Robot.driveTrain.getLeftPosition();
		right_previous = Robot.driveTrain.getRightPosition();

		double dE = dL - dR;
		total += dE;

		pow_r = pow_r + k*(total);
		Robot.driveTrain.drive(pow_l, pow_r);
		System.out.println("Left: " + pow_l + " Right: " + pow_r);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
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