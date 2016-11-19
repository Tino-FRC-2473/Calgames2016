package org.usfirst.frc.team2473.robot;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.usfirst.frc.team2473.robot.Database.Value;

import edu.wpi.first.wpilibj.command.Scheduler;

public class DashboardThread extends Thread {

	@Override
	public void run()
	{

			
			while(true)
			{
				System.out.println("opening socket");
				
				try(
						ServerSocket server = new ServerSocket(5005);
						Socket socket = server.accept();
						OutputStream rawOut = socket.getOutputStream();
								PrintStream out = new PrintStream(rawOut))
				{
					System.out.println("connected");
					while(true)
					{
						//send data
						
						for(Value v : Value.values())
						{
							out.println(v + ":" + Database.getInstance().getValue(v));
						}
						Scheduler.getInstance();
						
						Thread.sleep(1000);
						
					}
				}
				catch(IOException e)
				{
					System.err.println(
							"stuff");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
			
			
			
		}
	}
	
}
