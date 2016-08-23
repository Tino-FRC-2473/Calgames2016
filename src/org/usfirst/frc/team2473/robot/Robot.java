
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2473.robot.commands.*;
import org.usfirst.frc.team2473.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static BallShooter ballShooter;
	public static OI oi;
	public static AnalogGyro gyro;
	public static Timer timer;
	public static double start;
    Command autonomousCommand;

    public static Logger.LogLevel log;

    public void robotInit() {
    	driveTrain = new DriveTrain();
    	ballShooter = new BallShooter();

    	oi = new OI();

		timer = new Timer();
		SmartDashboard.putData(driveTrain);
		autonomousCommand = new AutonomousCommand();
		log = Logger.LogLevel.Debug;
    }

    public void autonomousInit() {

    	if (autonomousCommand != null) {
        	timer.start();
        	start = timer.get();
        	autonomousCommand.start();
        }
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public static double getStart() {
    	return start;
    }
        
}
