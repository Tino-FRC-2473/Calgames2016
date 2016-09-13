package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.Database.Value;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	public static final double SPEED_TURNING_MULTIPLICATION_CONSTANT = 0.4;
	public static final double SPEED_TURNING_ADDING_CONSTANT = 0.6;
	public static final double DEADZONE_AREA = 0.04;
	public static final double MAX_TURN = 0.8;
	
	private boolean drivingStraight;//is the robot driving straight
	private double startingGyroValue;//the gyro value when starting to drive straight
	
    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double throttleZ = Database.getInstance().getValue(Value.THROTTLE_VALUE);
    	double wheelX = Database.getInstance().getValue(Value.WHEEL_TWIST);
    	double thrust = -sqrtWithSign(throttleZ);
    	
    	if(Math.abs(wheelX) < DEADZONE_AREA)
    	{
    		if(!drivingStraight)
    		{
    			drivingStraight = true;
    			startingGyroValue = Database.getInstance().getValue(Value.GYRO);
    		}
    		driveStraight(thrust, 0.3*(Database.getInstance().getValue(Value.GYRO) - startingGyroValue));
    		
    	}
    	else
    	{
    		drivingStraight = false;
    		Robot.driveTrain.driveArcade(thrust, wheelX);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    
    private void driveStraight(double speed, double rotate)
    {
    	Robot.driveTrain.driveArcade(speed, rotate);
    }
    
    private double sqrtWithSign(double in)
    {
    	return (in > 0)?Math.sqrt(in):-Math.sqrt(-in) ;
    }
    
    /**
     * 
     * @param rawIn the raw wheel input
     * @param speed the speed of the bot. Can be simply the thrust
     * @return a scaled value that should be the new turn factor
     */
    private double shapeWheel(double rawIn, double speed)
    {
    	double sign = Math.signum(rawIn);
    	double result = rawIn;
    	
    	if(Math.abs(rawIn) < DEADZONE_AREA) return 0;
    	
    	//result = ;
    	
    	
    	if(result > MAX_TURN) result = MAX_TURN;
    	
    	return result * sign;
    }
}
