
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team2473.robot.commands.*;
import org.usfirst.frc.team2473.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static TestSystem system;
	public static OI oi;

    Command turnServo;

    public void robotInit() {
    	system = new TestSystem();
		oi = new OI();
		
		turnServo = new TurnServo(80); //insert desired degrees here
    }
    
    public void autonomousInit() {
        if (turnServo!= null) turnServo.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void log(){

    }
}
