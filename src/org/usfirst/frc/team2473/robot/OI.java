package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.team2473.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private Joystick joyOne;
	private Joystick joyTwo;
	
	/*
	public enum Config {
	 
		TANK,
		Z1,
		SINGLE,
		WHEEL
	};
	
	public static Config joyConfig;
	*/
	
	public OI() {
		
	}
	
/*
	public OI(Config c) {
		joyConfig = c;
		
		switch(c) {
		
		case TANK:
			joyOne = new Joystick(0);
			joyTwo = new Joystick(1);
			
		case Z1:
			
			
		case SINGLE:
			
			
		case WHEEL:
			
			
		}
	}
*/
	
	public Joystick getJoystickOne() {
        return joyOne;
    }
	
	public Joystick getJoystickTwo() {
        return joyTwo;
    }
	
	public Joystick getJoystickLeft() {
        return joyOne;
    }
	
	public Joystick getJoystickRight() {
        return joyTwo;
    }
}

