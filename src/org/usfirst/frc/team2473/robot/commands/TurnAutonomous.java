package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Logger;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAutonomous extends Command {
	
	private final double ANGLE;
	private final String TURN;
	private final double TURN_POWER = 0.6;

	/**
	 * @param turn The direction of the turn.
	 * @param amt The angle of the turn in degrees.
	 */
	public TurnAutonomous(String turn, double amt) {
		ANGLE = amt;
		TURN = turn;
		requires(Robot.driveTrain);
	}

	public TurnAutonomous() {
		ANGLE = 90;
		TURN = "right";
		requires(Robot.driveTrain);
	}
	
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() { 
		if(TURN.equals("right")) {
			Robot.driveTrain.turnRight(TURN_POWER);
		} else if(TURN.equals("left")) {
			Robot.driveTrain.turnLeft(TURN_POWER);
		}
	}

	@Override
	protected boolean isFinished() {
		if((Math.abs(Math.floor(Robot.driveTrain.getAngle()) - ANGLE)) <= 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
