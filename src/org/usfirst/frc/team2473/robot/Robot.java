
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team2473.robot.commands.*;
import org.usfirst.frc.team2473.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static OI oi;

    Command autonomousCommand;
    
	private int drive = 0;

    public void robotInit() {
    	oi = new OI();
    	
    	SmartDashboard.putString("DB/String 1", "Select the drive control you wish to use by pressing a button.");
    	SmartDashboard.putString("DB/String 2", "(2: TANK), (3: ARCADE), (4: Z), (5: WHEEL), (1: DEFAULT [TANK])");
    	
    	/*while(drive == 0) {
    		for(int i = 1; i <= 5; i++) {
    			if(oi.getJoystickOne().getRawButton(i)) {
    				drive = i;
    				break;
    			}
    		}
    	}*/
    	
    	driveTrain = new DriveTrain(2/*drive*/);
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
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
