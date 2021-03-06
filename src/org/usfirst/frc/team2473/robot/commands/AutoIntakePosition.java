/*
 * This command will run when the robot needs a boulder and stop when the boulder is in the robot.
 */

package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2473.robot.Robot;

/**
 *
 */
public class AutoIntakePosition extends Command {
	
	private double robot_timeout;
	
	/**
	 * This command toggles the piston on for the timeout duration before toggling the piston off.
	 * @param timeout the amount of time to pause before toggling the solenoid off (in MILLISECONDS WESLEY!!!!!)
	 */
	public AutoIntakePosition(double timeout) {
    	robot_timeout = timeout;
    	requires(Robot.pickup);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pickup.togglePiston(true);
    	setTimeout(robot_timeout);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pickup.togglePiston(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pickup.togglePiston(false);
    }
}
