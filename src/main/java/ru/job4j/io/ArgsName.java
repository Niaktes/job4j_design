package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
	
	private final Map<String, String> values = new HashMap<>();
	
	public String get(String key) {
		if (!values.containsKey(key)) {
			throw new IllegalArgumentException("Requested argument missing.");
		}
		return values.get(key);
	}
	
	private void parse(String[] args) {
		for (String argument : args) {
			String[] pair = argument.split("=", 2);
			argumentsValidation(pair);
			values.put(pair[0].substring(1), pair[1]);			
		}
	}
	
	private static void argumentsValidation(String[] pair) {
		if (pair.length != 2 || pair[0].isBlank() || pair[1].isBlank()) {
			throw new IllegalArgumentException("Invalid argument! Key or value of argument is missing.");
		}
		if (!pair[0].startsWith("-")) {
			throw new IllegalArgumentException("Invalid argument! Please use next pattern: \"-key=value\"");
		}
	}
	
	public static ArgsName of(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("No arguments has been added.");
		}
		ArgsName names = new ArgsName();
		names.parse(args);
		return names;
	}

	public static void main(String[] args) {
		ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
		System.out.println(jvm.get("Xmx"));
		
		ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
		System.out.println(zip.get("out"));
	}

}
