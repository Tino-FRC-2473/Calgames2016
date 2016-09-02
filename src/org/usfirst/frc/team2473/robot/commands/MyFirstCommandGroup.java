package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MyFirstCommandGroup extends CommandGroup {
    
	/**
	 * This command moves the robot forward, then autonomously extends the piston for intake before moving the robot back.
	 */
    public  MyFirstCommandGroup() {
//    	addSequential(new DriveRobot(.10, .10, 1000));
    	addSequential(new AutoIntakePosition(1));
//    	addSequential(new DriveRobot(.10, .10, 1000));
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require (but does not need the requires() method).
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
