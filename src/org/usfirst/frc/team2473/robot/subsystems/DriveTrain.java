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
	
	private enum DriveType {
		TANK, ARCADE, Z, WHEEL
	}
	
	private DriveType driveType = null;

	public DriveTrain(int drive) {
		super();
		
		driveType = intToDriveType(drive);
		
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
		/*switch(driveType) {
		case TANK:*/
			setDefaultCommand(new TankDrive());
		/*case ARCADE:
			setDefaultCommand(new ArcadeDrive());
		case Z:
			setDefaultCommand(new ZDrive());
		case WHEEL:
			setDefaultCommand(new WheelDrive());
		}*/
	}

	public void drive(double left, double right) {
		leftFrontCAN.set(-left);
		leftBackCAN.set(-left);
		rightFrontCAN.set(right);
		rightBackCAN.set(right);
	}

	private DriveType intToDriveType(int c) {
		switch(c) {
		case 2:
			return DriveType.TANK;
		case 3:
			return DriveType.ARCADE;
		case 4:
			return DriveType.Z;
		case 5:
			return DriveType.WHEEL;
		}
			
		return DriveType.TANK;
	}
	
	private void setUpDriveMotors(CANTalon tal) {
		tal.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		tal.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		tal.setPosition(0);
		tal.enableControl();
	}
}
