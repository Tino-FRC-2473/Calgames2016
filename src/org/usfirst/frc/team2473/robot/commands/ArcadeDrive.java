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
    	double left = throttle + direction;
    	double right = throttle - direction;
    	
    	left = limitRangeAndScaleAndSquare(left);
    	right = limitRangeAndScaleAndSquare(right);
		Robot.driveTrain.drive(left, right);
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
    
    private double limitRangeAndScaleAndSquare(double x) {
    	if(x < -1.0) x = -1.0;
    	if(x > 1.0) x = 1.0;
    	
    	x = Math.abs(x) * x;
    	
    	x = Robot.driveTrain.MOTOR_SCALE * x;
    	
    	return x;
    }
}
