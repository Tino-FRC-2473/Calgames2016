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
	
	private SpeedController intakeCAN;
	
	private Solenoid pistonLeft;
	private Solenoid pistonRight;
	
	public boolean up = true;
	
	public Pickup(){
		pistonLeft = new Solenoid(RobotMap.pistonLeft);
		pistonRight = new Solenoid(RobotMap.pistonRight);
		intakeCAN = new CANTalon(RobotMap.intakeMotor);
	}
	
    // The methods for controlling the subsystem go here.
	//Call these methods from the commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new Intake());
    }
    
    public void startIntake() {
    	moveIntake(0.5);
    }
    
    public void stopIntake() {
    	moveIntake(0);
    }
    
    private void moveIntake(double speed) {
    	intakeCAN.set(speed);
    }
    
    public void togglePiston(boolean forward) {
    	if (forward) {
    		pistonLeft.set(true);
    		pistonRight.set(false);
    	}
    	else {
    		pistonRight.set(true);
    		pistonLeft.set(false);
    	}
    }
}

