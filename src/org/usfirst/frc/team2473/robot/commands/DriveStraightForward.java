package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Database.Value;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightForward extends Command {

	
	private double kP;
	private double speed;
	private double distance;
	
    /**
	 * @deprecated Use {@link #DriveStraightForward(double,double)} instead
	 */
	public DriveStraightForward() {
		this(0.7, 0.3, 100);
	}

	public DriveStraightForward(double speed, double kP, double distance) {
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	this.kP = kP;
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensorThread.resetEncoders();
    	Robot.sensorThread.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	/*double error = Robot.driveTrain.getRightEncoder() - Robot.driveTrain.getLeftEncoder();
    	double baseSpeed = .35;
    	error /= 100;
    	
    	Robot.driveTrain.drive(baseSpeed + error,   baseSpeed - error);*/
    	
    	//double direction = Math.abs(Robot.driveTrain.getHeading())/Robot.driveTrain.getHeading();
    	//Robot.driveTrain.driveArcade(.6, Math.min(Math.abs(Robot.driveTrain.getHeading()*.3),.5)*direction);
    	
    	double error = Database.getInstance().getValue(Value.GYRO);
    	
    	Robot.driveTrain.driveArcade(speed, error*kP);
    	
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
