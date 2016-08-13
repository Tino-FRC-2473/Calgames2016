package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleOpCommand extends CommandGroup {
	int driveType;
	
	public TeleOpCommand(int drive) {
		driveType = drive;
		
		addSequential(getDriveCmdFromInt(driveType));
		//addSequential(new NameOfOtherCommandToBeRunInTeleOp());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public Command getDriveCommand() {
		return getDriveCmdFromInt(driveType);
	}
	
	private Command getDriveCmdFromInt(int d) {
    	switch(d) {
    	case 1:
    		return new TankDrive();
    		
    	case 2:
    		return new ArcadeDrive();
    		
    	case 3:
    		return new ZDrive();
    		
    	case 4:
    		return new WheelDrive();
    		
    	default:
    		return new TankDrive();
    		
    	}
    }
}