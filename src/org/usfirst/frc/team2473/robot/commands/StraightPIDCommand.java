package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StraightPIDCommand extends Command {

	double left_previous, right_previous, dL, dR, pow, total;
	double distance;
	double difference;
	double speed;
	
	final double KP = 0.1;
	final double KI = 0.1;
	
	final static double FOREVER = -1;

	public StraightPIDCommand(double distance, double speed) {
		requires(Robot.driveTrain);
		this.distance = distance;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		pow = 0.1;
		Robot.driveTrain.drive(speed, speed);
	}

	@Override
	protected void execute() {
		dL = Robot.driveTrain.getLeftPosition() - left_previous;
		dR = Robot.driveTrain.getRightPosition() - right_previous;
		left_previous = Robot.driveTrain.getLeftPosition();
		right_previous = Robot.driveTrain.getRightPosition();

		double dE = dL - dR;
		total += dE;

		difference = difference + KP*dE + KI*(total);
		Robot.driveTrain.drive(speed - difference, speed + difference);
		System.out.println("Left: " + (speed - difference) + " Right: " + (speed + difference));
	}

	@Override
	protected boolean isFinished() {
		return distance == FOREVER ? false : (Robot.driveTrain.getLeftPosition() + Robot.driveTrain.getRightPosition())/2 > distance;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.drive(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}