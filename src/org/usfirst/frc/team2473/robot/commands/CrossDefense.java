package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrossDefense extends Command{
	
	final String DEFENSE;
	
	public CrossDefense(String typeOfDefense) {
		requires(Robot.driveTrain);
		
		/*INSERT CHEVAL SUBSYSTEM REQUIREMENT*/
		
		DEFENSE = typeOfDefense;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		switch(DEFENSE) {
			case "low_bar":
				Robot.driveTrain.drive(0.4, 0.4);
				break;
			case "cheval":
				/*INSERT CHEVAL CODE HERE*/
				break;
			case "category_b":
				Robot.driveTrain.drive(0.5, 0.5);
				break;
			case "rough_terrain":
				Robot.driveTrain.drive(0.4, 0.4);				
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
