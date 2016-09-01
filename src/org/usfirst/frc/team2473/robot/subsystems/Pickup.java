package org.usfirst.frc.team2473.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;


/**
 *	@author Kashyap & Wesley
 */
public class Pickup extends Subsystem {
	
	private Solenoid pistonOne;
	private Solenoid pistonTwo;
	
	public boolean up = true;
	
	public Pickup(){
		pistonOne = new Solenoid(0);
		pistonTwo = new Solenoid(1);
	}
	
    // The methods for controlling the subsystem go here.
	//Call these methods from the commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new IntakePosition());
    }
    
    public void startIntake() {
    	Robot.driveTrain.moveIntake(0.5);
    }
    
    public void stopIntake() {
    	Robot.driveTrain.moveIntake(0);
    }
    
    public void togglePiston(boolean forward) {
    	if (forward) {
    		pistonOne.set(true);
    		pistonTwo.set(true);
    	}
    	else {
    		pistonOne.set(false);
    		pistonTwo.set(false);
    	}
    }
}

