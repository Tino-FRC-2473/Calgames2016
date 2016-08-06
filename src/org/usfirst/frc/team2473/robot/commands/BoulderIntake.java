/*
 * This command will run when the robot needs a boulder and stop when the boulder is in the robot.
 */


package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2473.robot.Robot;

/**
 *
 */
public class BoulderIntake extends Command {

    public BoulderIntake() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pickup.startIntake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isBoulderInside();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pickup.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean isBoulderInside() {
    	//Get data from the shooter's breakbeam to determine when to stop running execute()
    	return false;
    }
}
