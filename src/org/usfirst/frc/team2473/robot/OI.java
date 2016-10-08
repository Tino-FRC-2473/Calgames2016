package org.usfirst.frc.team2473.robot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team2473.robot.Database.ButtonName;
import org.usfirst.frc.team2473.robot.Database.Value;
import org.usfirst.frc.team2473.robot.commands.FireBallShooter;
import org.usfirst.frc.team2473.robot.commands.SpinPickup;
import org.usfirst.frc.team2473.robot.commands.ToggleIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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
	public static boolean diagnostic = false;

	public OI() {

		tempButtonMap = new HashMap<>();

		joyMap = new HashMap<>();

		buttonCallMap = new HashMap<>();

		// add the button calls here
		buttonCallMap.put(ButtonName.TRIGGER, () -> getThrottle().getRawButton(1));
		buttonCallMap.put(ButtonName.PISTONS, () -> getThrottle().getRawButton(4));
		buttonCallMap.put(ButtonName.PICKUP, () -> getThrottle().getRawButton(2));

		buttonCallMap = Collections.unmodifiableMap(buttonCallMap);

		joyCallMap = new HashMap<>();

		// add joystick calls here
		joyCallMap.put(Value.WHEEL_TWIST, () -> getWheel().getX());
		joyCallMap.put(Value.THROTTLE_VALUE, () -> getThrottle().getZ());

		joyCallMap = Collections.unmodifiableMap(joyCallMap);

		// Database.getInstance().getButton(ButtonName.TRIGGER).whenActive(new
		// ButtonTest());
		if (!diagnostic) {
			Database.getInstance().getButton(ButtonName.TRIGGER).whenPressed(new FireBallShooter(.75));
			Database.getInstance().getButton(ButtonName.PISTONS).whenPressed(new ToggleIntake());
			Database.getInstance().getButton(ButtonName.PICKUP).whileHeld(new SpinPickup());
			// new JoystickButton(getThrottle(), 1).whileActive(new
			// FireBallShooter());
		}
	}

	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getWheel() {
		return wheel;
	}

	public void updateJoysticks() {

		// snapshots the current joystick
		for (Value v : joyCallMap.keySet()) {
			joyMap.put(v, joyCallMap.get(v).getAsDouble());
		}
		// pushes to the Database
		for (Value v : joyMap.keySet()) {
			Database.getInstance().setValue(v, joyMap.get(v));
		}
	}

	public void updateButtons() {
		// snapshots the current joystick
		for (ButtonName b : buttonCallMap.keySet()) {
			tempButtonMap.put(b, buttonCallMap.get(b).getAsBoolean());
		}
		// pushes to the Database
		for (ButtonName b : tempButtonMap.keySet()) {
			Database.getInstance().setButtonValue(b, tempButtonMap.get(b));
		}
	}

	public void testButtons(){
		SmartDashboard.putString("DB/String 1","PLEASE PRESS AND HOLD THE TRIGGER UNTIL IT IS REGISTERED");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		SmartDashboard.putString("DB/String 2",(buttonCallMap.get(ButtonName.TRIGGER).getAsBoolean()? "TRIGGER IS REGISTERED":"TRIGGER IS NOT REGISTERED"));
		SmartDashboard.putString("DB/String 1","PLEASE PRESS AND HOLD THE PISTON UNTIL IT IS REGISTERED");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		SmartDashboard.putString("DB/String 2",(buttonCallMap.get(ButtonName.PISTONS).getAsBoolean()? "PISTON IS REGISTERED":"PISTON IS NOT REGISTERED"));
		SmartDashboard.putString("DB/String 1","PLEASE PRESS AND HOLD THE PICKUP UNTIL IT IS REGISTERED");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		SmartDashboard.putString("DB/String 2",(buttonCallMap.get(ButtonName.PICKUP).getAsBoolean()? "PICKUP IS REGISTERED":"PICKUP IS NOT REGISTERED"));	
	}
	
	public void testJoysticks() {
		SmartDashboard.putString("DB/String 1","RECIEVING INITIAL VALUE FOR THROTTLE");
		double ti = joyCallMap.get(Value.THROTTLE_VALUE).getAsDouble();
		SmartDashboard.putString("DB/String 1","PLEASE MOVE THE THROTTLE. THE SYSTEM WILL WAIT 2 SECONDS.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		double tf = joyCallMap.get(Value.THROTTLE_VALUE).getAsDouble();
		SmartDashboard.putString("DB/String 1","INITIAL THROTTLE VALUE: " + ti + "\nFINAL(Current) THROTTLE VALUE: " + tf + "\nDIFFERENCE: "
				+ (tf - ti));
		SmartDashboard.putString("DB/String 1","RECIEVING INITIAL VALUE FOR WHEEL");
		ti = joyCallMap.get(Value.WHEEL_TWIST).getAsDouble();
		SmartDashboard.putString("DB/String 1","PLEASE MOVE THE WHEEL. THE SYSTEM WILL WAIT 2 SECONDS.");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		tf = joyCallMap.get(Value.WHEEL_TWIST).getAsDouble();
		SmartDashboard.putString("DB/String 1",
				"INITIAL WHEEL VALUE: " + ti + "\nFINAL(Current) WHEEL VALUE: " + tf + "\nDIFFERENCE: " + (tf - ti));
	}
}
