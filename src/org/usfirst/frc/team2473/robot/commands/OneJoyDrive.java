package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OneJoyDrive extends Command {

    public OneJoyDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = -Robot.oi.getJoystickLeft().getY() + Robot.oi.getJoystickLeft().getTwist() * 0.7;
    	double right = -Robot.oi.getJoystickLeft().getY() - Robot.oi.getJoystickLeft().getTwist() * 0.7;
    	Robot.driveTrain.drive(left, right);
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
