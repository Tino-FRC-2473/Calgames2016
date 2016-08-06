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
    	double motorScale = Robot.driveTrain.MOTOR_SCALE;
    	
    	double throttle = -Robot.oi.getJoystickOne().getY();
    	double direction = Robot.oi.getJoystickOne().getX();
    	double left = throttle + direction;
    	double right = throttle - direction;
    	
    	if(left > 1.0) left = 1.0;
    	if(right > 1.0) right = 1.0;
    	if(left < -1.0) left = -1.0;
    	if(right < -1.0) right = -1.0;
    	
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
