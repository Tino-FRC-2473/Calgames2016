package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	CANTalon leftFrontCAN;
	CANTalon rightFrontCAN;
	CANTalon leftBackCAN;
	CANTalon rightBackCAN;
	
	public DriveTrain (){
		super();
		
		leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

