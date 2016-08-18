package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;

public class SensorThread extends Thread{

	AnalogGyro gyro;
	AnalogInput leftLightSensor, rightLightSensor;
	CANTalon leftEncoder, rightEncoder;
	private volatile boolean run = true, alive = true;

	public SensorThread(AnalogInput gyro, AnalogInput leftLightSensor,
			AnalogInput rightLightSensor, CANTalon leftEncoder,
			CANTalon rightEncoder) {
		this.gyro = new AnalogGyro(gyro);
		this.leftLightSensor = leftLightSensor;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
		this.rightLightSensor = rightLightSensor;
		super.setDaemon(true);
	}

	@Override
	public void run() {
		while (alive) {
			while (run && alive) {
				//update sensors
			}
			if(alive)
			{
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
	public void kill()
	{
		alive = false;
		notify();
	}
	
	
	public boolean isDead()
	{
		return !alive;
	}
	
	public boolean isUpdating()
	{
		return run;
	}
}
