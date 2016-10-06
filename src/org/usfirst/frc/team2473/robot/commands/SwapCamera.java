package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwapCamera extends Command {
	
	private boolean front = false;
	
    public SwapCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	if(front){
    		CameraServer.getInstance().startAutomaticCapture("cam1");
    	}else{
    		CameraServer.getInstance().startAutomaticCapture("cam0");
    	}
    	front = !front;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
