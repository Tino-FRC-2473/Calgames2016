
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.Timer;
import java.util.TimerTask;

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

	boolean timerRunning;

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
    
    public static double getStart() {
    	return start;
    }
        
	public static SensorThread sensorThread;
	Timer robotControlLoop;

	double lastTime;

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		//System.out.println(System.currentTimeMillis() - lastTime);

		if (!timerRunning) {
			robotControlLoop.scheduleAtFixedRate(new TimerTask(){

				@Override
				public void run() {
					Scheduler.getInstance().run();
				}
			}, 0, 20);
			timerRunning = true;
		}
		if (sensorThread == null) {
			// create the args for sensorThread
			sensorThread = new SensorThread(2);
			sensorThread.setPriority(2);
			sensorThread.start();
		}

		oi.updateButtons();
		oi.updateJoysticks();
		
		log();
		lastTime = System.currentTimeMillis();



	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	@Override
	public void disabledPeriodic() {
		if (sensorThread != null && sensorThread.isUpdating()) {
			sensorThread.stopUpdating();
		}

		if (timerRunning) {
			// ends the timer and stops it from executing any tasks
			robotControlLoop.cancel();
			robotControlLoop = new Timer();
			timerRunning = false;
		}
	}

	public void log() {
		Database.getInstance().log();
	}

	@Override
	public void finalize() {
		try {
			super.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// set motors to 0
	}
}
