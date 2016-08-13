package org.usfirst.frc.team2473.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.commands.IntakePosition;


/**
 *
 */
public class Pickup extends Subsystem {
    
	private SpeedController intake;
	private DoubleSolenoid pistonOne;
	private DoubleSolenoid pistonTwo;
	
	
	public Pickup(){
		intake = new CANTalon(RobotMap.leftBackMotor);
		pistonOne = new DoubleSolenoid(1, 2);
		pistonTwo = new DoubleSolenoid(3, 4);
	}
	
    // The methods for controlling the subsystem go here.
	//Call these methods from the commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakePosition());
    }
    
    public void startIntake() {
    	intake.set(0.5);
    }
    
    public void stopIntake() {
    	intake.set(0);
    }
    
    public void togglePiston(boolean forward) {
    	if (forward) {
    		pistonOne.set(DoubleSolenoid.Value.kForward);
    		pistonTwo.set(DoubleSolenoid.Value.kForward);
    	}
    	else {
    		pistonOne.set(DoubleSolenoid.Value.kReverse);
    		pistonTwo.set(DoubleSolenoid.Value.kReverse);
    	}
    }
}

