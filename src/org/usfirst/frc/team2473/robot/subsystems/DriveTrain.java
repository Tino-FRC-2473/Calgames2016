package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.Drive;
import org.usfirst.frc.team2473.robot.commands.DriveStraightForward;
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
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private SpeedController  leftFrontCAN;
	private SpeedController rightFrontCAN;
	private SpeedController leftBackCAN;
	private SpeedController rightBackCAN;
	private AnalogGyro gyro;

	private RobotDrive drive;
	
	public DriveTrain (){
		super();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		
		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN, rightBackCAN);
		
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

    public double getRightEncoder(){
    	return ((CANTalon)rightFrontCAN).getEncPosition();
    }
    
    public double getLeftEncoder(){
    	return ((CANTalon)leftFrontCAN).getEncPosition();
    }
    
    public double getHeading(){
    	return gyro.getAngle();
    }
    
    public void reset(){
    	((CANTalon)rightFrontCAN).setEncPosition(0);
    	((CANTalon)leftFrontCAN).setEncPosition(0);
    	gyro.reset();
    }
    
    public void log(){
    	SmartDashboard.putNumber("Left Distance", ((CANTalon)leftFrontCAN).getEncPosition());
		SmartDashboard.putNumber("Right Distance", ((CANTalon)rightFrontCAN).getEncPosition());
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
    }

    public void driveArcade(double speed, double rotate) {
    	drive.arcadeDrive(speed, rotate);
   
	}
}

