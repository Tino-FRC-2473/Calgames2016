package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleOpCommand extends CommandGroup {

	public TeleOpCommand(Command drive) {
		addSequential(drive);
		//addSequential(new NameOfOtherCommandToBeRunInTeleOp());
	}
}