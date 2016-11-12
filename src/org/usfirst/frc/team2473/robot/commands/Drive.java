package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Database;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.Database.Value;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {

	public static final double SPEED_TURNING_MULTIPLICATION_CONSTANT = 0.30;
	public static final double SPEED_TURNING_ADDING_CONSTANT = 0.70;
	public static final double DEADZONE_AREA = 0.04;
	public static final double MAX_TURN = 0.8;
	public static final double KP = 0;//.075;
	public static final double KI = 0;//.003;
	public static final double KD = 0;//.00;
	
	private boolean drivingStraight;//is the robot driving straight
	private double startingGyroValue;//the gyro value when starting to drive straight
	private double integral;
	private double lastProportion;
	
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
    	double thrust = -sqrtWithSign(throttleZ*.75);
    	
    	if(Math.abs(wheelX) < DEADZONE_AREA && Math.abs(thrust) > .05)
    	{
    		if(!drivingStraight)
    		{
    			drivingStraight = true;
    			startingGyroValue = Database.getInstance().getValue(Value.GYRO);
    			integral = 0;
    		}
    		driveStraight(thrust);
    	}
    	else
    	{
    		drivingStraight = false;
    		Robot.driveTrain.driveArcade(thrust, shapeWheel(-wheelX,throttleZ));
    	}
    	
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
    
    
    private void driveStraight(double speed)
    {
    	
    	double proportion = Database.getInstance().getValue(Value.GYRO) - startingGyroValue;
    	integral += proportion;
    	double derivative = proportion - lastProportion;
    	double rotate = KP * proportion + KI*integral + KD*derivative;
    	
    	if(Math.abs(rotate) > .70){
    		rotate = Math.signum(rotate) * .7;
    	}
    	
    	Robot.driveTrain.driveArcade(speed, rotate);
    	lastProportion = proportion;
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
    	rawIn *= .8;
    	
    	if(Math.abs(rawIn) < DEADZONE_AREA) return 0;
    	
    	result =  (SPEED_TURNING_ADDING_CONSTANT + SPEED_TURNING_MULTIPLICATION_CONSTANT * Math.abs(speed)/2) * Math.abs(sqrtWithSign(rawIn)) + (1 - Math.abs(speed)/2) * SPEED_TURNING_MULTIPLICATION_CONSTANT;
    	
    	
    	if(result > MAX_TURN) result = MAX_TURN;
    	
    	//System.out.println(result);
    	
    	return result * sign;
    }
}
