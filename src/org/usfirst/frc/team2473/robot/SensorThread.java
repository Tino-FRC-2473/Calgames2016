package org.usfirst.frc.team2473.robot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

import org.usfirst.frc.team2473.robot.Database.Value;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;


public class SensorThread extends Thread{

	//add new sensors here
	AnalogGyro gyro;
	AnalogInput leftLightSensor, rightLightSensor;
	CANTalon leftEncoder, rightEncoder;
	private volatile boolean run = true, alive = true;
	long lastTime;
	int delay;

	private Map<Database.Value, Double> tempMap;

	//a map of how each value is called
	private Map<Database.Value, DoubleSupplier> callMap;

	public SensorThread(int delay) {
		
		this.delay = delay;
		
		//add new sensors here
		this.gyro = Robot.gyro;
		this.leftLightSensor = new AnalogInput(RobotMap.leftLightSensor);
		this.leftEncoder = new CANTalon(RobotMap.leftFrontMotor);
		this.rightEncoder = new CANTalon(RobotMap.rightFrontMotor);
		this.rightLightSensor = new AnalogInput(RobotMap.rightLightSensor);

		leftEncoder.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightEncoder.setFeedbackDevice(FeedbackDevice.QuadEncoder);

		resetEncoders();

		tempMap = new HashMap<>();

		callMap = new HashMap<>();

		//add the sensor name in the Values enum and the method of the sensor that returns the sensor value.
		callMap.put(Value.GYRO, () -> gyro.getAngle());
		callMap.put(Value.LEFT_LIGHT_SENSOR, () -> leftLightSensor.getValue());
		callMap.put(Value.RIGHT_LIGHT_SENSOR, () -> rightLightSensor.getValue());
		callMap.put(Value.RIGHT_ENCODER, () -> rightEncoder.getEncPosition() * Database.RIGHT_ENC_CONSTANT);
		callMap.put(Value.LEFT_ENCODER, () ->  leftEncoder.getEncPosition() * Database.LEFT_ENC_CONSTANT);
		
		
		
		callMap = Collections.unmodifiableMap(callMap);
		super.setDaemon(true);
	}
	
	/**
	 * this method simulates the thread methods Thread.pause() and Thread.kill(). 
	 * It continuously polls sensors and then sleeps for delay length while alive and running.
	 * When it is not running it simply waits and stops running when it is not alive
	 */
	@Override
	public void run() {
		while (alive) {
			while (run && alive) {
				// System.out.println(System.currentTimeMillis() - lastTime);
				
				updateSensors();
				
				lastTime = System.currentTimeMillis();
				// Thread.yield();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

	private void updateSensors()
	{
		//snapshots a value for every sensor
		for (Database.Value v : callMap.keySet()) {
			tempMap.put(v, callMap.get(v).getAsDouble());
		}
		
		//push those values to the database
		for(Database.Value v : tempMap.keySet())
		{
			Database.getInstance().setValue(v, tempMap.get(v));
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
