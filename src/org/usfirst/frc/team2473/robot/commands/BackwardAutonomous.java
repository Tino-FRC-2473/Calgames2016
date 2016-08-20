package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Logger;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class BackwardAutonomous extends Command {

	final double ENCODER_RIGHT_VAL;
	final double ENCODER_LEFT_VAL;
	final double POW;
	final double DEFAULT_POW = 0.5;

	private static final double LEFT_ENC_CONSTANT = .01944349; //scales encoders to inches
	private static final double RIGHT_ENC_CONSTANT = .00827586;
	
	public BackwardAutonomous() {
		requires(Robot.driveTrain); //runs using drive train mechanism
		ENCODER_LEFT_VAL = 500;
		ENCODER_RIGHT_VAL = 500;
		POW = DEFAULT_POW;
	}

	public BackwardAutonomous(int val){
		requires(Robot.driveTrain); //runs using drive train mechanism
		ENCODER_RIGHT_VAL = val*12*RIGHT_ENC_CONSTANT;
		ENCODER_LEFT_VAL = val*12*LEFT_ENC_CONSTANT;
		POW = DEFAULT_POW;
	}
	
	public BackwardAutonomous(int val, double pow) {
		requires(Robot.driveTrain);
		ENCODER_RIGHT_VAL = val*12*RIGHT_ENC_CONSTANT;
		ENCODER_LEFT_VAL = val*12*LEFT_ENC_CONSTANT;
		POW = pow;
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.resetEncoders();		
	}

	@Override
	protected void execute() {
		Robot.driveTrain.drive(-POW, -POW);
	}

	@Override
	protected boolean isFinished() {
		if (Robot.driveTrain.getRightPosition() <= -ENCODER_RIGHT_VAL && Robot.driveTrain.getLeftPosition() <= -ENCODER_LEFT_VAL) {
			System.out.println("FINISHED!");
			return true;
		} else if(Robot.driveTrain.detectedElement()) {
			return true;			
		} else {
			return false;
		}
	}

	@Override
	protected void end() {
		Robot.driveTrain.drive(0, 0);
	}

	@Override
	protected void interrupted() {
		
	}

}
