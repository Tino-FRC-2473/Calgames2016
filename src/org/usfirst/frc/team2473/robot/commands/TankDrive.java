package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {

    public TankDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {
    }
    
    protected void execute() {
		Robot.driveTrain.drive(
			Math.pow(Robot.oi.getJoystickOne().getY(), 2),
			Math.pow(Robot.oi.getJoystickTwo().getY(), 2)
		);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
