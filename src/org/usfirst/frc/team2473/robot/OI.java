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
public class OI {
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
		// pushes to the DatabaseÏ
		for (ButtonName b : tempButtonMap.keySet()) {
			Database.getInstance().setButtonValue(b, tempButtonMap.get(b));
		}
	}
}
