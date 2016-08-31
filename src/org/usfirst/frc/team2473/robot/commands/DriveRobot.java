package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class DriveRobot extends Command {
	
	private double lValue;
	private double rValue;
	private double encValue;

	/**
	 * This command moves the robot given the left and right motor speeds until the distance (in encoder ticks) given.
	 * 
	 * @param leftSpeed		the speed at which to run the left motor
	 * @param rightSpeed	the speed at which to run the right motor
	 * @param distance		the target encoder value to reach
	 */
    public DriveRobot(double leftSpeed, double rightSpeed, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	lValue = leftSpeed;
    	rValue = rightSpeed;
    	encValue = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(lValue, rValue);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean rightDistReached = Robot.driveTrain.getRightEncoder() >= encValue;
    	boolean leftDistReached = Robot.driveTrain.getLeftEncoder() >= encValue;
        return rightDistReached && leftDistReached;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
