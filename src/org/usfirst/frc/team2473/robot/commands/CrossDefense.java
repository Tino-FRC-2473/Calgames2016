package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrossDefense extends Command{
	
	final String DEFENSE;
	final double DEFENSE_POWER;
	
	public CrossDefense(String typeOfDefense) {
		requires(Robot.driveTrain);
		
		/*INSERT CHEVAL SUBSYSTEM REQUIREMENT*/
		
		DEFENSE = typeOfDefense;
		DEFENSE_POWER = 0.4;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		switch(DEFENSE) {
			case "low_bar":
				Robot.driveTrain.drive(DEFENSE_POWER, DEFENSE_POWER);
				break;
			case "cheval":
				Robot.driveTrain.drive(DEFENSE_POWER, DEFENSE_POWER);
				break;
			case "category_b":
				Robot.driveTrain.drive(0.5, 0.5);
				break;
			case "rough_terrain":
				Robot.driveTrain.drive(DEFENSE_POWER, DEFENSE_POWER);				
				break;
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.detectedFloor();
	}

	@Override
	protected void end() {
		Robot.driveTrain.halt();
	}

	@Override
	protected void interrupted() {
		
	}
	
}
