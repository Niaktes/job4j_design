package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class Analizy {

	public void unavailable(String source, String target) {
		try (BufferedReader in = new BufferedReader(new FileReader(source));
				PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
			boolean serverAvailable = true;
			StringJoiner unavailabilityPeriod = new StringJoiner(";");
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				String[] dataLine = line.split(" ", 2);
				if (serverAvailable && ("500".equals(dataLine[0]) || "400".equals(dataLine[0]))) {
					serverAvailable = false;
					unavailabilityPeriod.add(dataLine[1]);
				}
				if (!serverAvailable && ("200".equals(dataLine[0]) || "300".equals(dataLine[0]))) {
					serverAvailable = true;
					unavailabilityPeriod.add(dataLine[1] + ";");
					out.println(unavailabilityPeriod.toString());
					unavailabilityPeriod = new StringJoiner(";");	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Analizy analizy = new Analizy();
		analizy.unavailable("./data/server.log", "./data/unavailable.csv");
	}

}
