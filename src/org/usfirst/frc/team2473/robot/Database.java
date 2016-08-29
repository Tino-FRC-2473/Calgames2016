package org.usfirst.frc.team2473.robot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Database{

	public static final double LEFT_ENC_CONSTANT = .01944349; // scales encoders
																// to inches
	public static final double RIGHT_ENC_CONSTANT = .00827586;




	public enum Value {
		GYRO, LEFT_LIGHT_SENSOR, RIGHT_LIGHT_SENSOR, LEFT_ENCODER, RIGHT_ENCODER, JOY1_X, JOY1_Y, JOY1_Z, JOY2_X, JOY2_Y, JOY2_Z;// add
																																	// buttons

	}

	public enum ButtonName {

	}


	static Database theInstance;
	static {
		theInstance = new Database();
	}

	public static Database getInstance() {
		return theInstance;
	}

	private Map<Value, ThreadSafeHolder> map;
	private Map<ButtonName, Button> buttonMap;
	
	private Database() {
		HashMap<Value, ThreadSafeHolder> tempMap = (new HashMap<>());
		map = Collections.synchronizedMap(tempMap);
		for (Value v : Value.values()) {
			map.put(v, new ThreadSafeHolder());
		}
		buttonMap = Collections.synchronizedMap(new HashMap<>());
		for(ButtonName b : ButtonName.values())
		{
			buttonMap.put(b, new InternalButton());
		}
			
	}

	public double getValue(Value v) {
		return map.get(v).getValue();
	}


	public void setValue(Value v, double newValue) {


		map.get(v).setValue(newValue);
	}

	public Button getButton(ButtonName name)
	{
		return buttonMap.get(name);
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
