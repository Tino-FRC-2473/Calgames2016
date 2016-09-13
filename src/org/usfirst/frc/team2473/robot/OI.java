package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team2473.robot.Database.ButtonName;
import org.usfirst.frc.team2473.robot.Database.Value;
import org.usfirst.frc.team2473.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
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

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	private Joystick throttle = new Joystick(0);
	private Joystick wheel = new Joystick(1);
	private Map<ButtonName, Boolean> tempButtonMap;// a map with a snapshot of
													// the values
	private Map<ButtonName, BooleanSupplier> buttonCallMap;// a map that takes
															// the button names
															// and associates
															// functions with
															// them
	private Map<Value, Double> joyMap;// a map with a snapshot of the values
	private Map<Value, DoubleSupplier> joyCallMap;// a map that maps joystick
													// names with the functions
													// to get their values

	public OI() {

		tempButtonMap = new HashMap<>();

		joyMap = new HashMap<>();

		buttonCallMap = new HashMap<>();

		// add the button calls here

		buttonCallMap = Collections.unmodifiableMap(buttonCallMap);


		joyCallMap = new HashMap<>();

		// add joystick calls here
		joyCallMap.put(Value.WHEEL_TWIST, () -> getWheel().getX());
		joyCallMap.put(Value.THROTTLE_VALUE, () -> getThrottle().getZ());

		joyCallMap = Collections.unmodifiableMap(joyCallMap);



		// Database.getInstance().getButton(ButtonName.TRIGGER).whenActive(new
		// ButtonTest());
	}

	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getWheel() {
		return wheel;
	}

	public void updateJoysticks() {
		
		//take a snapshot of the values
		for(Value v : joyCallMap.keySet())
		{
			joyMap.put(v, joyCallMap.get(v).getAsDouble());
		}
		
		//push those values to the Database and clear the map
		for(Value v : joyMap.keySet())
		{
			Database.getInstance().setValue(v, joyMap.get(v));
		}
	}

	public void updateButtons() {
		//snapshot the buttons
		for (ButtonName b : buttonCallMap.keySet()) {
			tempButtonMap.put(b, buttonCallMap.get(b).getAsBoolean());
		}

		//push those values 
		for (ButtonName b : tempButtonMap.keySet()) {
			Database.getInstance().setButtonValue(b, tempButtonMap.get(b));
		}
	}
}

