package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
//import org.usfirst.frc.team2473.robot.commands.TeleOpCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**	
 *
 */
public class DriveTrain extends Subsystem {

	private SpeedController leftFrontCAN;
	private SpeedController rightFrontCAN;
	private SpeedController leftBackCAN;
	private SpeedController rightBackCAN;
	
	private RobotDrive drive;
  	
	public final double MOTOR_SCALE = 0.7;
	public final double DIR_THR_SCALE = 1.0;

	public DriveTrain() {
		super();
		
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
		//setDefaultCommand(new TankDrive());
		//setDefaultCommand(new TeleOpCommand(1));
		setDefaultCommand(Robot.teleOpCommand);
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
    
    public double scaleDirectionByThrottle(double dir, double thr) {
    	return dir * DIR_THR_SCALE * (1 - thr);
    }
	
	public void log() {
		//SmartDashboard.putNumber("Left Distance", ((CANTalon)leftFrontCAN).getEncPosition());
		//SmartDashboard.putNumber("Right Distance", ((CANTalon)rightFrontCAN).getEncPosition());
	}
}
