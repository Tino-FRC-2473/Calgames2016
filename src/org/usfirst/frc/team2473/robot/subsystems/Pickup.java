package org.usfirst.frc.team2473.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2473.robot.RobotMap;


/**
 *
 */
public class Pickup extends Subsystem {
    
	private SpeedController intake;
	private Solenoid pistonOne;
	private Solenoid pistonTwo;
	
	public boolean up = true;
	
	public Pickup(){
		intake = new CANTalon(RobotMap.leftBackMotor);
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
    	intake.set(0.5);
    }
    
    public void stopIntake() {
    	intake.set(0);
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

