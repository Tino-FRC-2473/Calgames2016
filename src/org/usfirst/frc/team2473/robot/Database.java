package org.usfirst.frc.team2473.robot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database{

	public static final double LEFT_ENC_CONSTANT = .01944349; // scales encoders
																// to inches
	public static final double RIGHT_ENC_CONSTANT = .00827586;




	public enum Value {
		GYRO, LEFT_LIGHT_SENSOR, RIGHT_LIGHT_SENSOR, LEFT_ENCODER, RIGHT_ENCODER, JOY1_X, JOY1_Y, JOY1_Z, JOY2_X, JOY2_Y, JOY2_Z;// add
																																	// buttons



	}


	static Database theInstance;
	static {
		theInstance = new Database();
	}

	public static Database getInstance()
	{
		return theInstance;
	}
	
	private Map<Value, ThreadSafeHolder> map;

	private Database() {
		HashMap<Value, ThreadSafeHolder>  tempMap = (new HashMap<>());
		map = Collections.synchronizedMap(tempMap);
		for (Value v : Value.values())
		{
			map.put(v, new ThreadSafeHolder());
		}
	}

	public double getValue(Value v) {
		return map.get(v).getValue();
	}


	public void setValue(Value v, double newValue) {
		
		
		map.get(v).setValue(newValue);
	}


	 public void log(){
	    	SmartDashboard.putNumber("Left Distance", getLeftEncoder());
			SmartDashboard.putNumber("Right Distance", getRightEncoder());
			SmartDashboard.putNumber("Gyro Angle", getGyroAngle());

	 }

}

class ThreadSafeHolder{

	private volatile double value;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	public double getValue() {
		try {
			lock.readLock().lock();
			return value;
		} finally {
			lock.readLock().unlock();
		}

	}

	public void setValue(double newValue) {
		try {
			lock.writeLock().lock();
			value = newValue;
		} finally {
			lock.writeLock().unlock();
		}
	}
}
