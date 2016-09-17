package org.usfirst.frc.team2473.robot.errorhandling;

import java.util.EventListener;
/*
 * Treat this like an ActionListener --> Check with Rehan on how to
 * use ActionListeners
 */
public interface RobotExceptionListener extends EventListener{
	public void ExceptionThrown(RobotException e);
}
