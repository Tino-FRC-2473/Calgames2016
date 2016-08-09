package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.OI;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WheelDrive extends Command {

    public WheelDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	OI o = Robot.oi;
    	
    	SmartDashboard.putString("DB/String 0", "Joy1");
    	SmartDashboard.putString("DB/String 1", "X: " + o.getJoystickOne().getX());
    	SmartDashboard.putString("DB/String 2", "Y: " + o.getJoystickOne().getY());
    	SmartDashboard.putString("DB/String 3", "Z: " + o.getJoystickOne().getZ());
    	SmartDashboard.putString("DB/String 4", "Tw: " + o.getJoystickOne().getTwist());
    	
    	SmartDashboard.putString("DB/String 5", "Joy2");
    	SmartDashboard.putString("DB/String 6", "X: " + o.getJoystickTwo().getX());
    	SmartDashboard.putString("DB/String 7", "Y: " + o.getJoystickTwo().getY());
    	SmartDashboard.putString("DB/String 8", "Z: " + o.getJoystickTwo().getZ());
    	SmartDashboard.putString("DB/String 9", "Tw: " + o.getJoystickTwo().getTwist());
    	
    	//left = limitRangeAndScaleAndSquare(left);
    	//right = limitRangeAndScaleAndSquare(right);
		//Robot.driveTrain.drive(left, right);
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
    
    /*private double limitRangeAndScaleAndSquare(double x) {
    	if(x < -1.0) x = -1.0;
    	if(x > 1.0) x = 1.0;
    	
    	x = Math.abs(x) * x;
    	
    	x = Robot.driveTrain.MOTOR_SCALE * x;
    	
    	return x;
    }*/
}
