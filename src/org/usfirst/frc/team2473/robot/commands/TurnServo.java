package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnServo extends Command {

	final double DEG;

	public TurnServo(double deg) {
		requires(Robot.system);
		DEG = deg;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		System.out.println("Angle: " + Robot.system.servo.getAngle());
		Robot.system.turnServo(DEG);
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
