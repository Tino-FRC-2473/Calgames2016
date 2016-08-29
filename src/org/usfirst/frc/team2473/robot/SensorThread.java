package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;

public class SensorThread extends Thread {

	AnalogGyro gyro;
	AnalogInput leftLightSensor, rightLightSensor;
	CANTalon leftEncoder, rightEncoder;
	private volatile boolean run = true, alive = true;
	long lastTime;

	public SensorThread() {
		this.gyro = Robot.gyro;
		this.leftLightSensor = new AnalogInput(RobotMap.leftLightSensor);
		this.leftEncoder = new CANTalon(RobotMap.leftFrontMotor);
		this.rightEncoder = new CANTalon(RobotMap.rightFrontMotor);
		this.rightLightSensor = new AnalogInput(RobotMap.rightLightSensor);

		leftEncoder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightEncoder.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		resetEncoders();

		super.setDaemon(true);
	}

	@Override
	public void run() {
		while (alive) {
			while (run && alive) {
				//System.out.println(System.currentTimeMillis() - lastTime);
				for (Database.Value v : Database.Value.values()) {
					switch (v) {
					case GYRO:
						Database.getInstance().setValue(v, gyro.getAngle());
						break;
					case LEFT_ENCODER:
						Database.getInstance().setValue(v, leftEncoder.getEncPosition() * Database.LEFT_ENC_CONSTANT);
						break;
					case RIGHT_ENCODER:
						Database.getInstance().setValue(v, rightEncoder.getEncPosition() * Database.RIGHT_ENC_CONSTANT);
						break;
					case LEFT_LIGHT_SENSOR:
						Database.getInstance().setValue(v, leftLightSensor.getValue());
						break;
					case RIGHT_LIGHT_SENSOR:
						Database.getInstance().setValue(v, rightLightSensor.getValue());
						break;
					}
				}
				lastTime = System.currentTimeMillis();
			}
			if (alive) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * stops this thread from updating Database. The thread may still finish its
	 * current loop.
	 */
	public void stopUpdating() {
		run = false;
	}

	/**
	 * Makes this thread start updating Database with values from the sensors.
	 * Does nothing if it is already running
	 */
	public void resumeUpdating() {
		notify();
	}

	/**
	 * kills this thread. It may run one last loop. Stops any future looping.
	 */
	public void kill() {
		alive = false;
		notify();
	}

	public boolean isDead() {
		return !alive;
	}

	public boolean isUpdating() {
		return run;
	}

	public void resetEncoders() {
		rightEncoder.setEncPosition(0);
		leftEncoder.setEncPosition(0);
	}

	public void resetGyro() {
		gyro.reset();
	}
}
