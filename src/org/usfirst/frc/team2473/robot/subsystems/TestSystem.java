package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TestSystem extends Subsystem {

	private Servo servo;
	
	public TestSystem (){
		super();		
		servo = new Servo(RobotMap.servo);
	}

    public void initDefaultCommand() {

    }
    
    public void turnServo(double deg) {
    	servo.setAngle(deg);
    }
}

