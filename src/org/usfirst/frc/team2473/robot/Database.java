package org.usfirst.frc.team2473.robot;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database{


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
		return rightEncoderHolder.getValue();
	}

	public void setRightEncoder(double newValue) {
		rightEncoderHolder.setValue(newValue);
	}

	private ThreadSafeHolder leftEncoderHolder = new ThreadSafeHolder();

	public double getLeftEncoder() {
		return leftEncoderHolder.getValue();
	}

	public void setLeftEncoder(double newValue) {
		leftEncoderHolder.setValue(newValue);
	}

	private ThreadSafeHolder leftLightSensorHolder = new ThreadSafeHolder();

	public double getLeftLightSensorHolder() {
		return leftLightSensorHolder.getValue();
	}

	public void setLeftLightSensorHolder(double newValue) {
		leftLightSensorHolder.setValue(newValue);
	}

	private ThreadSafeHolder rightLightSensorHolder = new ThreadSafeHolder();

	public double getRightLightSensorHolder() {
		return rightLightSensorHolder.getValue();
	}

	public void setRightLightSensorHolder(double newValue) {
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
