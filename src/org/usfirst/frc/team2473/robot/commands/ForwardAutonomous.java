package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Logger;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/* The purpose of this code is to make the robot able to do the following:
 * run forward for any amount of encoder clicks
 * run forward for any amount of seconds
 * turn for any amount of degrees
 * this is just to enrich our own capabilities as wplib beginners
 * */

public class ForwardAutonomous extends Command {
	int seconds = 1;
	int counter = 0;
	double curr_right = 0;
	double curr_left = 0;	
	double encoder_val = 500;
	double pow = 0.4;

	public ForwardAutonomous() {
		requires(Robot.driveTrain); //runs using drive train mechanism
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Logger.getInstance().logLevel = Logger.LogLevel.Debug;
		SmartDashboard.putNumber("Status", Robot.timer.get()); //print status message
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

//		Robot.driveTrain.drive(pow, pow);
//		counter++;

		Logger.getInstance().log(Robot.log, Double.toString(Robot.timer.get())); //print the running time
//		System.out.println("Something printing");
//		Robot.driveTrain.drive(-pow, -pow);

//		Logger.getInstance().log(Robot.log, Double.toString(Robot.timer.get())); //print the running time
		Logger.getInstance().log(Robot.log, "Right: " + Double.toString(Robot.driveTrain.getRightPosition())); //print the right position
		Logger.getInstance().log(Robot.log, "Left: " + Double.toString(Robot.driveTrain.getLeftPosition())); //print the left position
		Robot.driveTrain.drive(pow, pow);
//		Robot.driveTrain.drive(0.4, 0.4);
	}

	@Override
	protected boolean isFinished() {
//		return false;
//		return (Robot.timer.get() == (Robot.start + 5));
		if (Robot.driveTrain.getRightPosition() >= encoder_val && Robot.driveTrain.getLeftPosition() >= encoder_val) {
			System.out.println("FINISHED!");
			return true;
		} else {
			return false;
		}
//		return (Robot.driveTrain.getRightPosition() == 300 && Robot.driveTrain.getLeftPosition() == 300);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.drive(0, 0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
