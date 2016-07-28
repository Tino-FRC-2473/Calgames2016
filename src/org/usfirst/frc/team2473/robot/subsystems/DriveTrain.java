package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private SpeedController leftFrontCAN;
	private SpeedController rightFrontCAN;
	private SpeedController leftBackCAN;
	private SpeedController rightBackCAN;
	private Gyro gyro;
	private RobotDrive drive;
	private Encoder left_encoder, right_encoder;
	
	public DriveTrain () {
		super();
		
		gyro.reset();
		gyro.calibrate();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		
		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN, rightBackCAN);
		
		left_encoder = new Encoder(RobotMap.leftFrontMotor,RobotMap.leftBackMotor);
		right_encoder = new Encoder(RobotMap.rightFrontMotor,RobotMap.rightBackMotor);
		
		left_encoder.setDistancePerPulse(1);
		right_encoder.setDistancePerPulse(1);
		
		drive.setMaxOutput(.70);
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		drive.setInvertedMotor(MotorType.kRearRight, true);
		
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
    
    public void drive(double left, double right) {
    	drive.tankDrive(left, right);
    	
	}
    
    public void turnRight(double pow) {
    	drive.tankDrive(-pow, pow);
    }

    public void turnLeft(double pow) {
    	drive.tankDrive(pow, -pow);    	
    }

    public double getRightSpeed(){
    	return ((CANTalon)rightFrontCAN).get();
    }
    
    public double getLeftSpeed(){
    	
    	return ((CANTalon)leftFrontCAN).get();
    }
    
    public double getLeftPosition() {
    	return ((CANTalon)(leftFrontCAN)).getEncPosition();
    }

    public double getRightPosition() {
    	return ((CANTalon)(rightFrontCAN)).getEncPosition();
    }
    
    public double getAngle() {
    	return gyro.getAngle();
    }
    
    public void log(){
    	SmartDashboard.putNumber("Left Distance", left_encoder.getDistance());
		SmartDashboard.putNumber("Right Distance", right_encoder.getDistance());
    }
}

