package org.usfirst.frc.team2473.robot;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import org.usfirst.frc.team2473.robot.Database.Value;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DashboardThread extends Thread {

	
	PowerDistributionPanel pdp;
	Map<String, DoubleSupplier> motorMaker; 
	Map<String, Supplier<Command>> commandMap;
	
	private int port = 2005;
	 
	public DashboardThread(PowerDistributionPanel pdp, Map<String, DoubleSupplier> motorMaker, Map<String, Supplier<Command>> systems) {
		this.pdp = pdp;
		this.motorMaker = motorMaker;
		commandMap = systems;
	}
	

	@Override
	public void run()
	{

			
			while(true)
			{
				System.out.printf("opening socket on port %d\n", port);
				
				try(
						ServerSocket server = new ServerSocket(port);
						Socket socket = server.accept();
						OutputStream rawOut = socket.getOutputStream();
								PrintStream out = new PrintStream(rawOut))
				{
					System.out.println("connected");
					while(true)
					{
						out.println("START: SENSORS AND JOYSTICKS:");
						for(Value v : Value.values())
						{
							out.println(v + ":" + Database.getInstance().getValue(v));
						}
						out.println("END: SENSORS AND JOYSTICKS:");
						out.println("START: BATTERY INFORMATION");
						out.printf("VOLTAGE: %f", pdp.getVoltage());
						out.println("END: BATTERY INFORMATION");
						out.println("START: MOTORS");
						for(String s: motorMaker.keySet())
						{
							out.printf("%s: %f", s, motorMaker.get(s).getAsDouble());
						}
						out.println("END: MOTORS");
						out.println("START: COMMANDS");
						for(String s : commandMap.keySet())
						{
							out.printf("%s: %s", s, commandMap.get(s).get() == null ? "null" : commandMap.get(s).get().toString());
						}
						out.println("END: COMMANDS");
						
						Thread.sleep(1000);
						
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("disconnected");
			
			
			
			
			
			
		}
	}
	
}
