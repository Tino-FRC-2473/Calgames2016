
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team2473.robot.commands.ArcadeDrive;
import org.usfirst.frc.team2473.robot.commands.TankDrive;
import org.usfirst.frc.team2473.robot.commands.TeleOpCommand;
import org.usfirst.frc.team2473.robot.commands.WheelDrive;
import org.usfirst.frc.team2473.robot.commands.ZDrive;
//import org.usfirst.frc.team2473.robot.commands.*;
import org.usfirst.frc.team2473.robot.subsystems.*;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static OI oi;

    private Command autonomousCommand;
    private Command teleOpCommand;
    
    private Command driveCmd;

    public void robotInit() {
    	//(1: TANK), (2: ARCADE), (3: Z), (4: WHEEL)
    	int drive = 1;
    	driveCmd = getDriveCmdFromInt(drive);
    	
    	teleOpCommand = new TeleOpCommand(driveCmd);
    	//autonomousCommand = new AutonomousCommand();
    	
    	driveTrain = new DriveTrain(teleOpCommand);
    	oi = new OI();
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
