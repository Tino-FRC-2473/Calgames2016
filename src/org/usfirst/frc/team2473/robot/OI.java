package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.team2473.robot.commands.*;

public class OI {
	
	private Joystick joyOne; //the controller plugged into port ZEROOO (0)
	private Joystick joyTwo; //the controller plugged into port ONEEEE (1)
	
	public OI() {
		//sjin is short
	}
	
	public Joystick getJoystickOne() {
        return joyOne;
    }
	
	public Joystick getJoystickTwo() {
        return joyTwo;
    }
}

