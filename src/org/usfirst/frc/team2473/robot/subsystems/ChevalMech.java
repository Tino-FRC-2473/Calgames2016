package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ChevalMech extends Subsystem {

	private Solenoid chevalM;
	
	public ChevalMech(){
		super();
		
		chevalM = new Solenoid(RobotMap.pcm,RobotMap.chevalMechanism);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	// Sets position of piston ( True / False )
	public void set(Boolean pos){
		chevalM.set(pos);
	}
	
	// Returns Position of piston returns (True / False)
	public boolean get(){
		return chevalM.get();
	}

}