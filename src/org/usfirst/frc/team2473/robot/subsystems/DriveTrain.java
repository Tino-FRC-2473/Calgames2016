package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.ArcadeDrive;
import org.usfirst.frc.team2473.robot.commands.TankDrive;
import org.usfirst.frc.team2473.robot.commands.WheelDrive;
import org.usfirst.frc.team2473.robot.commands.ZDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	CANTalon leftFrontCAN;
	CANTalon rightFrontCAN;
	CANTalon leftBackCAN;
	CANTalon rightBackCAN;
	
	private RobotDrive drive;
	
	public final double MOTOR_SCALE = 0.7;
	
	private int driveType = 0;

	public DriveTrain(int d) {
		super();
		
		driveType = d;
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		
		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN, rightBackCAN);
		
		((CANTalon)leftFrontCAN).setFeedbackDevice(FeedbackDevice.QuadEncoder);
		((CANTalon)rightFrontCAN).setFeedbackDevice(FeedbackDevice.QuadEncoder);

		drive.setMaxOutput(.70);
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		drive.setInvertedMotor(MotorType.kRearRight, true);
	}

	public void initDefaultCommand() {
		setDriveType(driveType);
	}
	
	public void changeDriveType(int d) {
		setDriveType(driveType);
	}

	public void setDriveType(int d) {
		switch(d) {
		case 2:
			setDefaultCommand(new TankDrive());
			break;
		case 3:
			setDefaultCommand(new ArcadeDrive());
			break;
		case 4:
			setDefaultCommand(new ZDrive());
			break;
		case 5:
			setDefaultCommand(new WheelDrive());
			break;
		default:
			setDefaultCommand(new TankDrive());
			break;
		}
	}
	
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}
	
	public double getRightEncoder(){
    	return ((CANTalon)rightFrontCAN).getEncPosition();
    }
    
    public double getLeftEncoder(){
    	return ((CANTalon)leftFrontCAN).getEncPosition();
    }
	
	public void log() {
		SmartDashboard.putNumber("Left Distance", ((CANTalon)leftFrontCAN).getEncPosition());
		SmartDashboard.putNumber("Right Distance", ((CANTalon)rightFrontCAN).getEncPosition());
	}
}
