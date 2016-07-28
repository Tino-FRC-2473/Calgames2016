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

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	OI o = Robot.oi;
    	
    	SmartDashboard.putString("DB/String 1", "Joy1: (" +
			o.getJoystickOne().getX() + ", " +
			o.getJoystickOne().getY() + ", " +
			o.getJoystickOne().getZ() + "), Twist: " +
			o.getJoystickOne().getTwist()
    	);
    	SmartDashboard.putString("DB/String 1", "Joy2: (" +
			o.getJoystickTwo().getX() + ", " +
			o.getJoystickTwo().getY() + ", " +
			o.getJoystickTwo().getZ() + "), Twist: " +
			o.getJoystickTwo().getTwist()
    	);
    	/*
    	Robot.driveTrain.drive(
    		-Robot.oi.getJoystickLeft().getY(),
    		-Robot.oi.getJoystickRight().getY()
    	); 
    	*/
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
