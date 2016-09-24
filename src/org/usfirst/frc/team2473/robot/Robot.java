
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

	Command autonomousCommand;
	boolean timerRunning;

	public static DriveTrain driveTrain;
	public static OI oi;
	public static AnalogGyro gyro;

	public static SensorThread sensorThread;
	Timer robotControlLoop;

	double lastTime;

	private Command runningCommand;// the command currently running

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveTrain = new DriveTrain();
		oi = new OI();
		gyro = new AnalogGyro(RobotMap.gyro);

		robotControlLoop = new Timer(false);
		timerRunning = false;

		PIThread thread = new PIThread();
		thread.start();
		
		SmartDashboard.putData(driveTrain);
	}

	/**
	 */
	public void autonomousInit() {

		if (runningCommand != null) {
			runningCommand.cancel();
		}

		runningCommand = new AutonomousCommand();
		runningCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		if (!timerRunning) {
			robotControlLoop.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					Scheduler.getInstance().run();
				}
			}, 0, 20);
			timerRunning = true;
		}

		if (sensorThread == null || sensorThread.isAlive()) {
			// create the args for sensorThread
			sensorThread = new SensorThread(2);
			sensorThread.setPriority(2);
			sensorThread.start();
		} else if (!sensorThread.isUpdating()) {
			sensorThread.resumeUpdating();
		}
		log();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (runningCommand != null) {
			runningCommand.cancel();
		}

		runningCommand = new Drive();
		runningCommand.start();
		


	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {

		// System.out.println(System.currentTimeMillis() - lastTime);

		if (!timerRunning) {
			robotControlLoop.scheduleAtFixedRate(new TimerTask() {

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
		} else if (!sensorThread.isUpdating()) {
			sensorThread.resumeUpdating();
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

	/**
	 * called as the system shuts down
	 */
	@Override
	public void finalize() {
		try {
			super.finalize();
			driveTrain.drive(0, 0);
			// other resetting stuff
		} catch (Throwable e) {

			e.printStackTrace();
		}
		// set motors to 0
	}
}
