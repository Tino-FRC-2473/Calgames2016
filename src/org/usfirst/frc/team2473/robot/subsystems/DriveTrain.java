package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.DriveStraight;
import org.usfirst.frc.team2473.robot.commands.GyroDrive;
import org.usfirst.frc.team2473.robot.commands.OneJoyDrive;
import org.usfirst.frc.team2473.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private CANTalon leftFrontCAN;
	private CANTalon rightFrontCAN;
	private CANTalon leftBackCAN;
	private CANTalon rightBackCAN;
	private AnalogGyro gyro;
	
	private static final double LEFT_ENC_CONSTANT = .01944349; //scales encoders to inches
	private static final double RIGHT_ENC_CONSTANT = .00827586;
	
	
	private RobotDrive drive;
	
	public DriveTrain (){
		super();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		
		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN, rightBackCAN);
		
		gyro = new AnalogGyro(RobotMap.gyro);
		
		gyro.initGyro();
		gyro.calibrate();
		
		
		leftFrontCAN.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightFrontCAN.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		reset();
		
		drive.setMaxOutput(.70);
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		drive.setInvertedMotor(MotorType.kRearRight, true);
		
	}

    public void initDefaultCommand() {
         setDefaultCommand(new OneJoyDrive());
    }
    
    public void drive(double left, double right) {
    	drive.tankDrive(left, right);
   
	}
    
    public void driveArcade(double speed, double rotate) {
    	drive.arcadeDrive(speed, rotate);
   
	}
    
    public double getRightEncoder(){
    	return rightFrontCAN.getEncPosition() * RIGHT_ENC_CONSTANT;
    }
    
    public double getLeftEncoder(){
    	return leftFrontCAN.getEncPosition() * LEFT_ENC_CONSTANT;
    }
    
    public double getHeading(){
    	return gyro.getAngle();
    }
    
    public void reset(){
    	rightFrontCAN.setEncPosition(0);
    	leftFrontCAN.setEncPosition(0);
    	gyro.reset();
    }
    
    public void log(){
    	SmartDashboard.putNumber("Left Distance", leftFrontCAN.getEncPosition() *  LEFT_ENC_CONSTANT);
		SmartDashboard.putNumber("Right Distance", rightFrontCAN.getEncPosition() *  RIGHT_ENC_CONSTANT);
	//	SmartDashboard.putNumber("Left Velocity", ((CANTalon)leftFrontCAN).getEncVelocity() *  leftEncConstant);
	//	SmartDashboard.putNumber("Right Velocity", ((CANTalon)rightFrontCAN).getEncVelocity() *  rightEncConstant);
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());

    }
}

