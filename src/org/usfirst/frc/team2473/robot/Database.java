package org.usfirst.frc.team2473.robot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.naming.TimeLimitExceededException;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Database{

	public static final double LEFT_ENC_CONSTANT = .01944349; // scales encoders
																// to inches
	public static final double RIGHT_ENC_CONSTANT = .00827586;

	public enum Value {
		GYRO, LEFT_LIGHT_SENSOR, RIGHT_LIGHT_SENSOR, LEFT_ENCODER, RIGHT_ENCODER, JOY1_X, JOY1_Y, JOY1_Z, JOY2_X, JOY2_Y, JOY2_Z, WHEEL_TWIST, THROTTLE_VALUE;// add
	}

	public enum ButtonName {
		//TRIGGER
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

	private Map<ButtonName, ThreadSafeInternalButton> buttonMap;
	
	private Database() {
		HashMap<Value, ThreadSafeHolder> tempMap = (new HashMap<>());//creates the map
		map = Collections.synchronizedMap(tempMap);//makes the map thread-safe
		//initializes the map with all the keys
		for (Value v : Value.values()) {
			map.put(v, new ThreadSafeHolder());
		}
		buttonMap = Collections.synchronizedMap(new HashMap<>());
		for(ButtonName b : ButtonName.values())
		{
			buttonMap.put(b, new ThreadSafeInternalButton());
		}
			
	}
	/**
	 * returns the value of the enum in a thread-safe manner
	 * @param v the value you are querying
	 * @return the value responding to the value enum
	 */

	public double getValue(Value v) {
		return map.get(v).getValue();
	}

	
	public double getValue(Value v, long timeout) throws InterruptedException, TimeoutException
	{
		return map.get(v).getValue(timeout);
	}
	/**
	 * sets the double value of the value in a thread-safe manner
	 * @param v the value you are querying
	 * @param the value responding to the value enum
	 */
	public void setValue(Value v, double newValue) {
		map.get(v).setValue(newValue);
	}

	public void setValue(Value v, double newValue, long timeout) throws InterruptedException, TimeoutException{
		map.get(v).setValue(newValue, timeout);
	}
	
	public synchronized Button getButton(ButtonName name)
	{
		return buttonMap.get(name);
	}

	public synchronized void setButtonValue(ButtonName name, boolean newValue)
	{
		buttonMap.get(name).setPressed(newValue);
	}
	
	public void log() {
		SmartDashboard.putNumber("Left Distance",
				Database.getInstance().getValue(Value.LEFT_ENCODER));
		SmartDashboard.putNumber("Right Distance",
				Database.getInstance().getValue(Value.RIGHT_ENCODER));
		SmartDashboard.putNumber("Gyro Angle",
				Database.getInstance().getValue(Value.GYRO));
	}
}

/**
 * A class capable of storing a single double value in a thread-safe value. It uses the java <a href = "">ReentrantReadWriteLock</a>
 * to make a FIFO acess
 * @author thatSteveFan, Will Fang
 *
 */

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
	
	public double getValue(long timeout) throws TimeoutException, InterruptedException
	{
		try{
		if(lock.readLock().tryLock(timeout, TimeUnit.MILLISECONDS))
			{
				return value;
			}
			else
			{
				throw new TimeoutException();
			}
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

	public void setValue(double newValue, long timeout) throws InterruptedException, TimeoutException{
		try{
			
			if(lock.writeLock().tryLock(timeout, TimeUnit.MILLISECONDS))
			{
				value = newValue;
			}
			else
			{
				throw new TimeoutException();
			}
		}finally{
			lock.writeLock().unlock();
		}
	}
}

/**
 * essentially the InternalButton but synchronized.
 * @author thatSteveFan, Will Fang
 *
 */
class ThreadSafeInternalButton extends InternalButton
{

	@Override
	public synchronized void setInverted(boolean inverted) {
		// TODO Auto-generated method stub
		super.setInverted(inverted);
	}

	@Override
	public synchronized void setPressed(boolean pressed) {
		// TODO Auto-generated method stub
		super.setPressed(pressed);
	}

	@Override
	public synchronized boolean get() {
		// TODO Auto-generated method stub
		return super.get();
	}

	@Override
	public synchronized void whenPressed(Command command) {
		// TODO Auto-generated method stub
		super.whenPressed(command);
	}

	@Override
	public synchronized void whileHeld(Command command) {
		// TODO Auto-generated method stub
		super.whileHeld(command);
		
	}

	@Override
	public synchronized void whenReleased(Command command) {
		// TODO Auto-generated method stub
		super.whenReleased(command);
	}

	@Override
	public synchronized void toggleWhenPressed(Command command) {
		// TODO Auto-generated method stub
		super.toggleWhenPressed(command);
	}

	@Override
	public synchronized void cancelWhenPressed(Command command) {
		// TODO Auto-generated method stub
		super.cancelWhenPressed(command);
	}
	
}
