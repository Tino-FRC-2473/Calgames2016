package org.usfirst.frc.team2473.robot;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PIThread extends Thread {

	public volatile boolean working = false;
	public String PIAdress = "http://2473-pi.local";

	@Override
	public void run() {
		try {
			URL url = new URL(PIAdress);

			HttpURLConnection connection = null;

			while (connection == null) {
				try {
					connection = (HttpURLConnection) url.openConnection();
				} catch (IOException expected) {
					continue;
				}

			}

			connection.setRequestMethod("GET");
			
			
			int code = connection.getResponseCode();
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
