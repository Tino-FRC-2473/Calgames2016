package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StallFix extends Command {

	double power = 0.3;
	double current_position_right, current_position_left;
	
	public StallFix() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncoders();		
		current_position_right = Robot.driveTrain.getRightPosition();
		current_position_left = Robot.driveTrain.getLeftPosition();
	}

	@Override
	protected void execute() {
		if(isStalling()) {
			System.out.println("Stalling...");
			power += 0.05;
		}
		Robot.driveTrain.drive(power, power);
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

	boolean isStalling() {
		return Robot.driveTrain.getRightPosition() == current_position_right || Robot.driveTrain.getLeftPosition() == current_position_left;
	}
}
