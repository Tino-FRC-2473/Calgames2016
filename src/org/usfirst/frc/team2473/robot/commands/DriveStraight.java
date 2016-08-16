package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

    public DriveStraight() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*double error = Robot.driveTrain.getRightEncoder() - Robot.driveTrain.getLeftEncoder();
    	double baseSpeed = .35;
    	error /= 100;
    	
    	Robot.driveTrain.drive(baseSpeed + error,   baseSpeed - error);*/
    	
    	//double direction = Math.abs(Robot.driveTrain.getHeading())/Robot.driveTrain.getHeading();
    	//Robot.driveTrain.driveArcade(.6, Math.min(Math.abs(Robot.driveTrain.getHeading()*.3),.5)*direction);
    	
    	double error = Robot.driveTrain.getHeading();
    	
    	Robot.driveTrain.driveArcade(.7, error*.3);
    	
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
