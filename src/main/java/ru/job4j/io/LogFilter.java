package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foundStrings;
	}
	
	public static void save(List<String> log, String file) {
		try (PrintWriter out = new PrintWriter(
				new BufferedOutputStream(
						new FileOutputStream(file)))) {
			log.forEach(out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LogFilter logFilter = new LogFilter();
		List<String> log = logFilter.filter("log.txt");
		log.forEach(System.out::println);
		save(log, "404.txt");
	}

}
