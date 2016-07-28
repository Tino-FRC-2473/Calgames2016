package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.TankDrive;

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

	public DriveTrain() {
		super();

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
		setDefaultCommand(new TankDrive());
		//setDefaultCommand(new ArcadeDrive());
		//setDefaultCommand(new ZDrive());
		//setDefaultCommand(new WheelDrive());
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
