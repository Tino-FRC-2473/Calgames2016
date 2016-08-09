package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.TeleOpCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	private SpeedController leftFrontCAN;
	private SpeedController rightFrontCAN;
	private SpeedController leftBackCAN;
	private SpeedController rightBackCAN;
	
	private RobotDrive drive;
	
	public final double MOTOR_SCALE = 0.6;
	
	private Command driveType;

	public DriveTrain(Command c) {
		super();
		
		driveType = c;
		
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
		setDefaultCommand(new TeleOpCommand(driveType));
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
