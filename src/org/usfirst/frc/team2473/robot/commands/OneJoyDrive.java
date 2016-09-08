package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Database.Value;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OneJoyDrive extends Command {

    public OneJoyDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveTrain.driveArcade(-Robot.oi.getJoystickLeft().getY(), -(1 - Robot.oi.getJoystickLeft().getY()*.4) * Robot.oi.getJoystickLeft().getTwist());
    	
    	double throttleZ = Database.getInstance().getValue(Value.THROTTLE_VALUE);
    	double wheelX = Database.getInstance().getValue(Value.WHEEL_TWIST);
    	double thrust = -sqrtWithSign(throttleZ);
    	//double turn = /*((joyRightY > 0)?1:-1)*/-1 * (1 - joyRightY*.3) *sqrtWithSign(joyLeftX) * 1.0;
    	double turn = (Math.abs(wheelX) < .04)?0:-1 *  ((wheelX > 0)?1:-1) *((Math.abs(throttleZ) * .40 + .60)*Math.abs(wheelX) + ((1.0 - Math.abs(throttleZ)) * .40));
    	Robot.driveTrain.driveArcade(thrust,turn );
    	 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    private double sqrtWithSign(double in)
    {
    	return (in > 0)?Math.sqrt(in):-Math.sqrt(-in) ;
    }
}
