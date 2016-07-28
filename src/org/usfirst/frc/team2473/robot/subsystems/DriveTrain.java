package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
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
    
	private SpeedController leftFrontCAN;
	private SpeedController rightFrontCAN;
	private SpeedController leftBackCAN;
	private SpeedController rightBackCAN;
	private AnalogGyro gyro;
	
	
	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;
	
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
		
		leftEncoder = new Encoder(RobotMap.leftFrontMotor,RobotMap.leftBackMotor);
		rightEncoder = new Encoder(RobotMap.rightFrontMotor,RobotMap.rightBackMotor);
		
		leftEncoder.setDistancePerPulse(1);
		rightEncoder.setDistancePerPulse(1);
		
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
    
    public double getRightEncoder(){
    	return rightEncoder.getDistance();
    }
    
    public double getLeftEncoder(){
    	return leftEncoder.getDistance();
    }
    
    public double getHeading(){
    	return gyro.getAngle();
    }
    
    public void reset(){
    	rightEncoder.reset();
    	leftEncoder.reset();
    	gyro.reset();
    }
    
    public void log(){
    	SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
    }
}

