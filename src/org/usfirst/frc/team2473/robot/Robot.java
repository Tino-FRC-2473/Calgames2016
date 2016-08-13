
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2473.robot.commands.TeleOpCommand;
//import org.usfirst.frc.team2473.robot.commands.*;
import org.usfirst.frc.team2473.robot.subsystems.*;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static OI oi;

    public Command autonomousCommand;
    public Command teleOpCommand;
    
    public static Command t;

    public void robotInit() {
    	driveTrain = new DriveTrain();
    	oi = new OI();
    	
    	//(1: TANK), (2: ARCADE), (3: Z), (4: WHEEL)
    	teleOpCommand = new TeleOpCommand(4);
    	
    	//autonomousCommand = new AutonomousCommand();
    }

    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        driveTrain.log();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
