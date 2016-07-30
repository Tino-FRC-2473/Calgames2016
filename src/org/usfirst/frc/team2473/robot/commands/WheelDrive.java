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
    	
    	SmartDashboard.putString("DB/String 1", "Joy1: (" +
			o.getJoystickOne().getX() + ", " +
			o.getJoystickOne().getY() + ", " +
			o.getJoystickOne().getZ() + "), Twist: " +
			o.getJoystickOne().getTwist()
    	);
    	
    	SmartDashboard.putString("DB/String 2", "Joy2: (" +
			o.getJoystickTwo().getX() + ", " +
			o.getJoystickTwo().getY() + ", " +
			o.getJoystickTwo().getZ() + "), Twist: " +
			o.getJoystickTwo().getTwist()
    	);
    	
    	/*
    	Robot.driveTrain.drive(
    		Math.pow(Robot.oi.getJoystickOne().getY(), 2),
    		Math.pow(Robot.oi.getJoystickTwo().getY(), 2)
    	);
    	*/
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
