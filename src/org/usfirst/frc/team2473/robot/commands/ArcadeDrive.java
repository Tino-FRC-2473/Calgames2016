package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double throttle = -Robot.oi.getJoystickOne().getY();
    	double direction = Robot.oi.getJoystickOne().getX();
    	
    	double right = throttle - direction;
		double left = throttle + direction;
    	
		Robot.driveTrain.drive(
			Math.pow(left, 2),
			Math.pow(right, 2)
		);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
