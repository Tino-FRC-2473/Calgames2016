package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.ArcadeDrive;
import org.usfirst.frc.team2473.robot.commands.TankDrive;
import org.usfirst.frc.team2473.robot.commands.WheelDrive;
import org.usfirst.frc.team2473.robot.commands.ZDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	CANTalon leftFrontCAN;
	CANTalon rightFrontCAN;
	CANTalon leftBackCAN;
	CANTalon rightBackCAN;
	
	public final double MOTOR_SCALE = 0.7;
	
	private int driveType = 0;

	public DriveTrain(int drive) {
		super();
		
		driveType = drive;
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);

		setUpDriveMotors(leftFrontCAN);
		setUpDriveMotors(rightFrontCAN);
		setUpDriveMotors(leftBackCAN);
		setUpDriveMotors(rightBackCAN);	
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
		leftFrontCAN.set(-left);
		leftBackCAN.set(-left);
		rightFrontCAN.set(right);
		rightBackCAN.set(right);
	}
	
	private void setUpDriveMotors(CANTalon tal) {
		tal.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPosition(0);
		tal.enableControl();
	}
}
