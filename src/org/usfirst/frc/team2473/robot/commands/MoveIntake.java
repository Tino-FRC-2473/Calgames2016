/*
 * This command will run when the robot needs a boulder and stop when the boulder is in the robot.
 */


package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2473.robot.Robot;

/**
 *
 */
public class MoveIntake extends Command {

	long startTime;
	
	static byte counter = 0;
	
    public MoveIntake() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (counter == 0) {
        	Robot.pickup.togglePiston(true);    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (System.currentTimeMillis() - startTime <= 100) {
			return true;
		}
		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	counter += 1;
    	Robot.pickup.togglePiston(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
     
    public static void resetCounter() {
    	counter = 0;
    }
}
