package org.usfirst.frc.team2473.robot.errorhandling;

import java.util.EventListener;

public interface RobotExceptionListener extends EventListener{
	public void ExceptionThrown(RobotException e);
}
