/*
 * This command will run when the robot needs a boulder and stop when the boulder is in the robot.
 */


package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2473.robot.Robot;

/**
 *
 */
public class IntakePosition extends Command {
	
	private String previousPosition = "Reverse";

    public IntakePosition() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (Robot.oi.getJoystickLeft().getRawButton(1) && previousPosition.equals("Reverse")) {
//    		Robot.pickup.togglePiston(true);
//    		previousPosition = "Forward";
//    	}
//    	else if (Robot.oi.getJoystickLeft().getRawButton(1) && previousPosition.equals("Forward")) {
//    		Robot.pickup.togglePiston(false);
//    		previousPosition = "Reverse";
//    	}
    	if (Robot.oi.getJoystickLeft().getRawButton(1)){
    		Robot.pickup.togglePiston(true);
    	}else if (Robot.oi.getJoystickLeft().getRawButton(3)){
    		Robot.pickup.togglePiston(false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (Robot.oi.getJoystickLeft().getRawButton(2)) {
			return true;
		}
		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
