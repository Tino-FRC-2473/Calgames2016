package org.usfirst.frc.team2473.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PIThread extends Thread {

	public volatile boolean working = false;
	public String PIAdress = "http://2473-pi.local";
	private Pattern p = Pattern.compile("[0-9]+\\.[0-9]+");
	private double lastTime = 0;

	@Override
	public void run() {
		int portNumber = 8080;
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(portNumber);
					Socket clientSocket = serverSocket.accept();
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

				String inputLine, outputLine;

				while ((inputLine = in.readLine()) != null) {
					out.println(inputLine);
					 Matcher m = p.matcher(inputLine);
					 m.find();
					 
					 double[] vals ={0,0,0};
					 
					 vals[0] = Double.parseDouble(inputLine.substring(m.start()+1, m.end()-1));
					 m.find();
					 vals[1] = Double.parseDouble(inputLine.substring(m.start()+1, m.end()-1));
					 m.find();
					 
					vals[2] = Double.parseDouble(inputLine.substring(m.start()+1, m.end()-1));
					System.out.println(vals[2] - lastTime);
					lastTime = vals[2];
				}
			} catch (IOException e) {
				System.out.println("Exception caught when trying to listen on port " + portNumber
						+ " or listening for a connection");
				System.out.println(e.getMessage());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}

	}
}
