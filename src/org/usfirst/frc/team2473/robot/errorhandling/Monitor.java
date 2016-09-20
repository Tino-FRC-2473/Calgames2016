package org.usfirst.frc.team2473.robot.errorhandling;

public abstract class Monitor {
	private RobotExceptionListener listener;
	public Monitor(RobotExceptionListener l){
		listener = l;
	}
	
	public abstract void monitor(); //Must implement method l.exceptionThrown(RobotException e);
	
}
