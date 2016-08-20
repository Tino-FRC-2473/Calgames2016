package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallShooter extends Subsystem{

	private CANTalon shooterCAN;

	public BallShooter(){
		super();
		shooterCAN = new CANTalon(RobotMap.ballShooterMotor);
	}
	
	@Override
	protected void initDefaultCommand() {
		//DONT PUT ANYTHING IN HERE.
		
	}
	
	public void spinMotor(double speed) {
		shooterCAN.set(speed);
	} 

}