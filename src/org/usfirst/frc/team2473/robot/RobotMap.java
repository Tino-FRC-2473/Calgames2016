package org.usfirst.frc.team2473.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;

	public static final int leftFrontMotor = 2;
	public static final int leftBackMotor = 3;
	public static final int rightFrontMotor = 4;
	public static final int rightBackMotor = 5;
	public static final int ballShooterMotor = 6;
	public static final int pickUpMotor = 7;
	
	public static final int pistonLeft = 0;
	public static final int pistonRight = 1;

	public static final int gyro = 0;
	public static final int leftLightSensor = 1;
	public static final int rightLightSensor = 2;

	
}
