/*
 * This command will run when the robot needs a boulder and stop when the boulder is in the robot.
 */

package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2473.robot.Robot;

/**
 *
 */
public class ToggleIntake extends Command {
	
	public ToggleIntake() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (!Robot.pickup.extended) {
    		Robot.pickup.togglePiston(true);
    		Robot.pickup.extended = true;
    	}else{
    		Robot.pickup.togglePiston(false);
        	Robot.pickup.extended = false;
    	}
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(!Robot.pickup.extended){
    		Robot.pickup.stopIntake();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
