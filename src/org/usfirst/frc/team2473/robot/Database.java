package org.usfirst.frc.team2473.robot;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database{

	private static final double LEFT_ENC_CONSTANT = .01944349; //scales encoders to inches
	private static final double RIGHT_ENC_CONSTANT = .00827586;

	static Database theInstance;
	static {
		theInstance = new Database();
	}

	private Database() {

	}
	
	public static Database getInstance() {
		return theInstance;
	}

	private ThreadSafeHolder gyroHolder = new ThreadSafeHolder();

	public double getGyroAngle() {
		return gyroHolder.getValue();
	}

	public void setGyroAngle(double newValue) {
		gyroHolder.setValue(newValue);
	}


	private ThreadSafeHolder rightEncoderHolder = new ThreadSafeHolder();

	public double getRightEncoder() {
		return rightEncoderHolder.getValue() * RIGHT_ENC_CONSTANT;
	}

	public void setRightEncoder(double newValue) {
		rightEncoderHolder.setValue(newValue);
	}

	private ThreadSafeHolder leftEncoderHolder = new ThreadSafeHolder();

	public double getLeftEncoder() {
		return leftEncoderHolder.getValue() * LEFT_ENC_CONSTANT;
	}

	public void setLeftEncoder(double newValue) {
		leftEncoderHolder.setValue(newValue);
	}

	private ThreadSafeHolder leftLightSensorHolder = new ThreadSafeHolder();

	public double getLeftLightSensor() {
		return leftLightSensorHolder.getValue();
	}

	public void setLeftLightSensor(double newValue) {
		leftLightSensorHolder.setValue(newValue);
	}

	private ThreadSafeHolder rightLightSensorHolder = new ThreadSafeHolder();

	public double getRightLightSensor() {
		return rightLightSensorHolder.getValue();
	}

	public void setRightLightSensor(double newValue) {
		rightLightSensorHolder.setValue(newValue);
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
