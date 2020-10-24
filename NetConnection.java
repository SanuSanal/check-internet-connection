package com.samples.netconnection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class NetConnection {
	public static void main(String[] args) throws IOException {
		Date now = new Date();

		Date refDate = new Date();
		refDate.setHours(refDate.getHours() + 1);
		
		File logFile = new File("log.txt");
		try {
			logFile.createNewFile();
			System.out.println("Log file created at : " + logFile.getAbsolutePath());
		} catch (IOException e) {
			System.out.println("couldn't create file");
		}

		FileWriter fw = null;

		fw = new FileWriter(logFile);
		
		while(now.before(refDate)) {
			URL url = null;
			URLConnection connection = null;
			now = new Date();
			try {
				url = new URL("http://www.google.com");
				connection = url.openConnection();
				connection.connect();
				fw.write("Internet is connected " + now + "\n");
				System.out.println("Internet is connected " + now);
			} catch (MalformedURLException e) {
				fw.write("Internet is not connected " + now + "\n");
				System.out.println("Internet is not connected " + now);
			} catch (IOException e) {
				System.out.println("Internet is not connected " + now);
				fw.write("Internet is not connected " + now + "\n");
			} finally {
				url = null;
				connection = null;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("couldnt pause");
				}
			}
		}

		fw.close();

	}
}
