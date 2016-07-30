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
	
	public ForwardAutonomous() {
		requires(Robot.driveTrain); //runs using drive train mechanism
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("Status", "Initializing..."); //print status message
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
//		Logger.getInstance().log(Robot.log, Double.toString(Robot.timer.get())); //print the running time
		Logger.getInstance().log(Robot.log, Double.toString(Robot.driveTrain.getRightPosition())); //print the right position
		Logger.getInstance().log(Robot.log, Double.toString(Robot.driveTrain.getLeftPosition())); //print the left position
//		Robot.driveTrain.drive(pow, pow);
//		Robot.driveTrain.drive(0.4, 0.4);
	}

	@Override
	protected boolean isFinished() {
		return (Robot.timer.get() == (Robot.start + 5));
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
