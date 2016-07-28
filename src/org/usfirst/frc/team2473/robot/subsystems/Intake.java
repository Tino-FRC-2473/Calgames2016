package org.usfirst.frc.team2473.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2473.robot.RobotMap;


/**
 *
 */
public class Intake extends Subsystem {
    
	private SpeedController lowerIntake;
	private SpeedController upperIntake;
	
	public Intake(){
		lowerIntake = new CANTalon(RobotMap.leftBackMotor);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void start() {
    	lowerIntake.set(0.5);
    }
    
    public void stop() {
    	lowerIntake.set(0);
    }
}

