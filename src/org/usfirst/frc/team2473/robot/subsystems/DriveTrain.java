package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;

import org.usfirst.frc.team2473.robot.commands.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem{

	private CANTalon leftFrontCAN;
	private CANTalon rightFrontCAN;
	private CANTalon leftBackCAN;
	private CANTalon rightBackCAN;
	private AnalogGyro gyro;

	private RobotDrive drive;

	public enum Motor {
		LEFT_FRONT, RIGHT_FRONT, LEFT_BACK, RIGHT_BACK;
	}

	public DriveTrain() {
		super();

//		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
//		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
//		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
//		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);

		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN,
				rightBackCAN);

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

	public void driveArcade(double speed, double rotate) {
		drive.arcadeDrive(speed, rotate);

	}

	public double getMotorVoltage(Motor m) {
		switch (m) {
			case LEFT_BACK:
				return leftBackCAN.getOutputVoltage();
			case LEFT_FRONT:
				return leftFrontCAN.getOutputVoltage();
			case RIGHT_BACK:
				return rightBackCAN.getOutputVoltage();
			case RIGHT_FRONT:
				return rightFrontCAN.getOutputVoltage();
			default:
				throw new IllegalArgumentException();
		}
	}

}

