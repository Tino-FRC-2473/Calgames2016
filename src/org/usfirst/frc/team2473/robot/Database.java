package org.usfirst.frc.team2473.robot;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database{


	static Database theInstance;
	static {
		theInstance = new Database();
	}



	private Database() {

	}


	private volatile int gyroValue;
	private ReentrantReadWriteLock gyroLock = new ReentrantReadWriteLock(true);

	public int getGyro() {
		try {
			gyroLock.readLock().lock();
			return gyroValue;
		} finally {
			gyroLock.readLock().unlock();
		}

	}

	public void setGyro(int newValue){
		try{
			gyroLock.writeLock().lock();
			gyroValue = newValue;
		}
		finally {
			gyroLock.writeLock().unlock();
		}
	}
	
	
	
	
	
	
	
	

}
