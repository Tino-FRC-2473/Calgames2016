package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.robot.commands.SpinPickup;
import org.usfirst.frc.team2473.robot.commands.ToggleIntake;
import org.usfirst.frc.team2473.robot.subsystems.BallShooter;
import org.usfirst.frc.team2473.robot.subsystems.Pickup;

public class Diagnostic {
	public Diagnostic(){
		
	}
	public boolean testEverything() throws InterruptedException {
		return shooterTest() & controllerTest() & driveTest();
	}
	private boolean driveTest() {
		return false;
	}
	private boolean controllerTest() {
		return false;
	}
	private boolean shooterTest() throws InterruptedException {
		System.out.println("BEGINNING SHOOTER TEST NOW");
		Pickup p = new Pickup();
		System.out.println("THE PICKUP SHOULD EXTEND NOW");
		p.togglePiston(true);
		p.extended=true;
		Thread.sleep(5000);
		System.out.println("THE PICKUP SHOULD RETRACT NOW");
		p.togglePiston(false);
		p.extended=false;
		System.out.println("BEGINNING TOGGLE INTAKE TEST");
		ToggleIntake t = new ToggleIntake();
		t.start();
		while(t.timeSinceInitialized()<5000);
		if(!t.isCanceled()){
			t.cancel();
			p.togglePiston(false);
			p.extended=false;
			System.out.println("TOGGLE INTAKE TOOK LONGER THAN 5 SECONDS.");
			return false;
		}
		System.out.println("TOGGLE INTAKE COMPLETED SUCCESSFULLY\nBEGINNING SPIN PICKUP TEST");
		SpinPickup s = new SpinPickup();
		s.start();
		while(s.timeSinceInitialized()<5000);
		if(!s.isCanceled()){
			s.cancel();
			p.togglePiston(false);
			p.extended=false;
			System.out.println("SPIN PICKUP TOOK LONGER THAN 5 SECONDS.");
			return false;
		}
		BallShooter b = new BallShooter();
		b.spinMotor(-1.0);
		b.spinMotor(0);
		System.out.println("ENTIRE SHOOTER TEST HAS CONCLUDED SUCCESSFULLY.");
		return true;
	}
}
