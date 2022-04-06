package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
	
	public List<String> filter(String file) {
		List<String> foundStrings = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				if (line.contains("\s404\s")) {
					foundStrings.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foundStrings;
	}

	public static void main(String[] args) {
		LogFilter logFilter = new LogFilter();
		List<String> log = logFilter.filter("log.txt");
		log.stream().forEach(s -> System.out.println(s));
	}

}
