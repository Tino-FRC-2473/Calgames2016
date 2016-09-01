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
	private SpeedController intakeCAN;
	private AnalogGyro gyro;
	
	
	private RobotDrive drive;
	
	public DriveTrain (){
		super();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		intakeCAN = new CANTalon(RobotMap.intakeMotor);
		
		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN, rightBackCAN);
		
		gyro = new AnalogGyro(RobotMap.gyro);
		
		gyro.initGyro();
		gyro.calibrate();
		
		((CANTalon)leftFrontCAN).setFeedbackDevice(FeedbackDevice.QuadEncoder);
		((CANTalon)rightFrontCAN).setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
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
    
    public void moveIntake(double speed) {
    	intakeCAN.set(speed);
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
}

