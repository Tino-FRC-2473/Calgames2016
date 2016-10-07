package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.robot.commands.SpinPickup;
import org.usfirst.frc.team2473.robot.commands.ToggleIntake;
import org.usfirst.frc.team2473.robot.subsystems.BallShooter;
import org.usfirst.frc.team2473.robot.subsystems.Pickup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostic {
	public Diagnostic(){
		
	}
	public boolean testEverything() throws Exception {
		return /*shooterTest() & controllerTest() &*/ driveTest();
	}
	private boolean driveTest() {
		SmartDashboard.putString("DB/String 0","BEGINNING DRIVE TEST NOW");
		SpeedController leftFrontCAN = new CANTalon(RobotMap.leftFrontMotor);
		SpeedController rightFrontCAN = new CANTalon(RobotMap.rightFrontMotor);
		SpeedController leftBackCAN = new CANTalon(RobotMap.leftBackMotor);
		SpeedController rightBackCAN = new CANTalon(RobotMap.rightBackMotor);
		SmartDashboard.putString("DB/String 1","RUNNING LEFT FRONT MOTOR NOW");
		double enc1 = ((CANTalon) leftFrontCAN).getEncPosition();
		leftFrontCAN.set(0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		double enc2 = ((CANTalon) leftFrontCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","REVERSING LEFT FRONT MOTOR NOW");
		leftFrontCAN.set(0);
		leftFrontCAN.set(-0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc1 = enc2;
		enc2 = ((CANTalon) leftFrontCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","RUNNING RIGHT FRONT MOTOR NOW");
		enc1 = ((CANTalon) rightFrontCAN).getEncPosition();
		rightFrontCAN.set(0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc2 = ((CANTalon) rightFrontCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","REVERSING RIGHT FRONT MOTOR NOW");
		rightFrontCAN.set(0);
		rightFrontCAN.set(-0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc1 = enc2;
		enc2 = ((CANTalon) rightFrontCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","RUNNING LEFT BACK MOTOR NOW");
		enc1 = ((CANTalon) leftBackCAN).getEncPosition();
		leftFrontCAN.set(0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc2 = ((CANTalon) leftBackCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","REVERSING LEFT BACK MOTOR NOW");
		leftFrontCAN.set(0);
		leftFrontCAN.set(-0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc1 = enc2;
		enc2 = ((CANTalon) leftFrontCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","RUNNING RIGHT BACK MOTOR NOW");
		enc1 = ((CANTalon) rightBackCAN).getEncPosition();
		rightFrontCAN.set(0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc2 = ((CANTalon) rightBackCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","REVERSING RIGHT BACK MOTOR NOW");
		enc1 = enc2;
		rightFrontCAN.set(-0.7);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.exit(1);
		}
		enc2 = ((CANTalon) rightBackCAN).getEncPosition();
		SmartDashboard.putString("DB/String 1", "INIT. ENC. VAL: ");
		SmartDashboard.putString("DB/String 2", ""+ enc1);
		SmartDashboard.putString("DB/String 3", "FINAL ENC. VALUE.: ");
		SmartDashboard.putString("DB/String 4", "" + enc2);
		SmartDashboard.putString("DB/String 5", "DIFF.:");
		SmartDashboard.putString("DB/String 6", "" + (enc2-enc1));
		SmartDashboard.putString("DB/String 0","DRIVE TEST CONCLUDED SUCCESSFULLY");
		return true;
	}
	private boolean controllerTest() {
		SmartDashboard.putString("DB/String 0","BEGINNING CONTROLLER TEST NOW");
		OI o = new OI();
		SmartDashboard.putString("DB/String 1","FIRST, LET'S WORK THROUGH THE BUTTONS.");
		o.diagnostic = true;
		o.testButtons();
		o.diagnostic = false;
		o.testJoysticks();
		SmartDashboard.putString("DB/String 0","ENTIRE CONTROLLER TEST HAS ENDED SUCCESSFULLY");
		return false;
	}
	private boolean shooterTest() throws InterruptedException {
		SmartDashboard.putString("DB/String 0","BEGINNING SHOOTER TEST NOW");
		Pickup p = new Pickup();
		SmartDashboard.putString("DB/String 1","THE PICKUP SHOULD EXTEND NOW");
		p.togglePiston(true);
		p.extended=true;
		Thread.sleep(5000);
		SmartDashboard.putString("DB/String 1","THE PICKUP SHOULD RETRACT NOW");
		p.togglePiston(false);
		p.extended=false;
		SmartDashboard.putString("DB/String 1","BEGINNING TOGGLE INTAKE TEST");
		ToggleIntake t = new ToggleIntake();
		t.start();
		while(t.timeSinceInitialized()<5000);
		if(!t.isCanceled()){
			t.cancel();
			p.togglePiston(false);
			p.extended=false;
			SmartDashboard.putString("DB/String 1","TOGGLE INTAKE TOOK LONGER THAN 5 SECONDS.");
			return false;
		}
		SmartDashboard.putString("DB/String 1", "TOGGLE INTAKE COMPLETED SUCCESSFULLY");
		SmartDashboard.putString("DB/String 1", "BEGINNING SPIN PICKUP TEST");
		SpinPickup s = new SpinPickup();
		s.start();
		while(s.timeSinceInitialized()<5000);
		if(!s.isCanceled()){
			s.cancel();
			p.togglePiston(false);
			p.extended=false;
			SmartDashboard.putString("DB/String 1","SPIN PICKUP TOOK LONGER THAN 5 SECONDS.");
			return false;
		}
		BallShooter b = new BallShooter();
		b.spinMotor(-1.0);
		b.spinMotor(0);
		SmartDashboard.putString("DB/String 0","ENTIRE SHOOTER TEST HAS CONCLUDED SUCCESSFULLY.");
		return true;
	}
}
