package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroDrive extends Command {

	private double newHeading;
	
    public GyroDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	newHeading = Database.getInstance().getValue(Database.Value.GYRO);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//newHeading += Robot.oi.getJoystickLeft().getTwist() * 2 * (1-Math.abs(Robot.oi.getJoystickLeft().getY())*.4);
    	
    	//double error = Math.max(Math.min(.07*(Database.getInstance().getValue(Database.Value.GYRO) - newHeading),1),-1);
    	
    	//Robot.driveTrain.driveArcade(-Robot.oi.getJoystickLeft().getY() * 0.9, error);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
