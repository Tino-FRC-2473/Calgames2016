package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SensorReader extends Command {

	public SensorReader() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		System.out.println("Value 1: " + Robot.driveTrain.getSensorOne());
		System.out.println("Value 2: " + Robot.driveTrain.getSensorTwo());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}