package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Align extends Command{
	
	final int COLOR_VAL;
	String direction_on = "";
		
	

	public Align(int lineType){
		COLOR_VAL=lineType;
		requires(Robot.driveTrain);
		
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		String state = "";
		if(Robot.driveTrain.getSensorOne() == COLOR_VAL && Robot.driveTrain.getSensorTwo() != COLOR_VAL) {
			state = "left";
		} else if(Robot.driveTrain.getSensorOne() != COLOR_VAL && Robot.driveTrain.getSensorTwo() == COLOR_VAL) {
			state = "right";
		} else if(Robot.driveTrain.getSensorOne() != COLOR_VAL && Robot.driveTrain.getSensorTwo() != COLOR_VAL) {
			state = direction_on;
		}

		alignTo(state);

	}

	@Override
	protected boolean isFinished() {
		return (Robot.driveTrain.getSensorOne() == COLOR_VAL) && (Robot.driveTrain.getSensorTwo() == COLOR_VAL);
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveTrain.drive(0,0);		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	void alignTo(String direction) {
		switch(direction) {
			case "right": 
				direction_on = "right";
				if(Robot.driveTrain.getSensorOne() != COLOR_VAL) {
					Robot.driveTrain.pivotRight(0.6);
				}
			break;
			case "left":
				direction_on = "left";
				if(Robot.driveTrain.getSensorTwo() != COLOR_VAL) {
					Robot.driveTrain.pivotLeft(0.6);
				}
			break;
			default:
				Robot.driveTrain.drive(0,0);
			break;
		}
	}
	
}
