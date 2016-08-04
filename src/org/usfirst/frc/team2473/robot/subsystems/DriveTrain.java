package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;
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
	private AnalogGyro gyro;
	private AnalogInput ir;
	
	private double leftEncConstant = .018461; //scales encoders to inches
	private double rightEncConstant = .00827586;
	
	
	private RobotDrive drive;
	
	public DriveTrain (){
		super();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		
		drive = new RobotDrive(leftFrontCAN, leftBackCAN, rightFrontCAN, rightBackCAN);
		
		gyro = new AnalogGyro(RobotMap.gyro);
		ir = new AnalogInput(RobotMap.ir);
		
		gyro.initGyro();
		gyro.calibrate();
		
		((CANTalon)leftFrontCAN).setFeedbackDevice(FeedbackDevice.QuadEncoder);
		((CANTalon)rightFrontCAN).setFeedbackDevice(FeedbackDevice.QuadEncoder);

		reset();
		
		drive.setMaxOutput(.70);
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
		drive.setInvertedMotor(MotorType.kRearRight, true);
		
	}

    public void initDefaultCommand() {
        setDefaultCommand(new OneJoyDrive());
    }
    
    public void drive(double left, double right) {
    	drive.tankDrive(left, right);
   
	}
    
    public double getRightEncoder(){
    	return ((CANTalon)rightFrontCAN).getEncPosition() * rightEncConstant;
    }
    
    public double getLeftEncoder(){
    	return ((CANTalon)leftFrontCAN).getEncPosition() * leftEncConstant;
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
    	SmartDashboard.putNumber("Left Distance", ((CANTalon)leftFrontCAN).getEncPosition() *  leftEncConstant);
		SmartDashboard.putNumber("Right Distance", ((CANTalon)rightFrontCAN).getEncPosition() *  rightEncConstant);
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());

    }
}

