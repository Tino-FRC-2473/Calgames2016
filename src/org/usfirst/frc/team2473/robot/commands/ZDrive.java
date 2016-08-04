package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZDrive extends Command {

    public ZDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double motorScale = Robot.driveTrain.MOTOR_SCALE;
    	
    	double throttle = -Robot.oi.getJoystickOne().getY();
    	double direction = Robot.oi.getJoystickOne().getZ();
    	double left = throttle + direction;
    	double right = throttle - direction;
    	
    	left = Math.abs(left) * left;
		right = Math.abs(right) * right;
    	
		Robot.driveTrain.drive(motorScale*left, motorScale*right);
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
