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

	public int getGyro() {
		return gyroHolder.getValue();
	}

	public void setGyro(int newValue) {
		gyroHolder.setValue(newValue);
	}


	private ThreadSafeHolder rightEncoderHolder = new ThreadSafeHolder();

	public int getRightEncoder() {
		return rightEncoderHolder.getValue();
	}

	public void setRightEncoder(int newValue) {
		rightEncoderHolder.setValue(newValue);
	}

	private ThreadSafeHolder leftEncoderHolder = new ThreadSafeHolder();

	public int getLeftEncoder() {
		return leftEncoderHolder.getValue();
	}

	public void setLeftEncoder(int newValue) {
		leftEncoderHolder.setValue(newValue);
	}

	private ThreadSafeHolder leftLightSensorHolder = new ThreadSafeHolder();

	public int getLeftLightSensorHolder() {
		return leftLightSensorHolder.getValue();
	}

	public void setLeftLightSensorHolder(int newValue) {
		leftLightSensorHolder.setValue(newValue);
	}

	private ThreadSafeHolder rightLightSensorHolder = new ThreadSafeHolder();

	public int getRightLightSensorHolder() {
		return rightLightSensorHolder.getValue();
	}

	public void setRightLightSensorHolder(int newValue) {
		rightLightSensorHolder.setValue(newValue);
	}


}

class ThreadSafeHolder{

	private volatile int value;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

	public int getValue() {
		try {
			lock.readLock().lock();
			return value;
		} finally {
			lock.readLock().unlock();
		}

	}

	public void setValue(int newValue) {
		try {
			lock.writeLock().lock();
			value = newValue;
		} finally {
			lock.writeLock().unlock();
		}
	}
}
