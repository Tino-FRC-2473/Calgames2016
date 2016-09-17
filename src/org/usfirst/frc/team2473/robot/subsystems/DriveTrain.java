package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.AutonomousConstants;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.AutonomousCommand;


import org.usfirst.frc.team2473.robot.commands.ForwardAutonomous;
import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.Drive;
import org.usfirst.frc.team2473.robot.commands.DriveStraightForward;
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
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
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
	private Encoder left_encoder, right_encoder;
	private AnalogInput one, two;

	private RobotDrive drive;
	
	public DriveTrain () {
		super();

		gyro = Robot.gyro;
		gyro.reset();
		gyro.calibrate();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);

		one = new AnalogInput(RobotMap.leftLightSensor);
		two = new AnalogInput(RobotMap.rightLightSensor);
		
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
         setDefaultCommand(new Drive());
    }
    
    public void drive(double left, double right) {
    	drive.tankDrive(left, right);    	
	}
    
    public void resetEncoders() {
    	leftFrontCAN.changeControlMode(CANTalon.TalonControlMode.Position);
    	leftFrontCAN.setEncPosition(0);
    	rightFrontCAN.changeControlMode(CANTalon.TalonControlMode.Position);
    	rightFrontCAN.setEncPosition(0);

    	leftFrontCAN.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	rightFrontCAN.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    public void turnRight(double pow) {
    	drive(pow, -pow);
    }

    public void turnLeft(double pow) {
    	drive.tankDrive(-pow, pow);    	
    }

    public void pivotRight(double pow) {
    	drive.tankDrive(pow, 0);
    }

    public void pivotLeft(double pow) {
    	drive.tankDrive(0, pow);    	
    }
    
    
    public int getSensorOne() {
    	return one.getValue();
    }

    public int getSensorTwo() {
    	return two.getValue();
    }
    
    public double getRightSpeed(){
    	return rightFrontCAN.get();
    }
    
    public double getLeftSpeed(){    	
    	return leftFrontCAN.get();
    }
    
    public double getLeftPosition() {
    	return leftFrontCAN.getEncPosition();
    }

    public double getRightPosition() {
    	return rightFrontCAN.getEncPosition();
    }
    
    public double getAngle() {
    	return gyro.getAngle();
    }
    
    public boolean detectedElement() {
    	int one = getSensorOne();
    	int two = getSensorTwo();
    	return (one == AutonomousConstants.cleat || one == AutonomousConstants.line || one == AutonomousConstants.ramp) || (two == AutonomousConstants.cleat || two == AutonomousConstants.line || two == AutonomousConstants.ramp);
    }

    public boolean detectedFloor() {
    	int one = getSensorOne();
    	int two = getSensorTwo();
    	return (one == AutonomousConstants.floor && two == AutonomousConstants.floor);
    }
    
    
    public void halt() {
    	Robot.driveTrain.drive(0,0);
    }

    public void driveArcade(double speed, double rotate) {
    	drive.arcadeDrive(speed, rotate);
   
	}

}

