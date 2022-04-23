package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CSVReader {
		
	public static void handle(ArgsName arguments) throws IOException {
		File source = new File(arguments.get("path"));
		String delimiter = arguments.get("delimiter");
		String out = arguments.get("out");
		List<String> filter = Arrays.asList(arguments.get("filter").split(","));
		argumentsValidation(source, delimiter, out, filter);
		List<List<String>> data = readCSV(source, delimiter);
		if (data.isEmpty()) {
			throw new EOFException("File is empty!");
		}
		List<List<String>> filteredData = filterCSVByColumns(data, filter);
		outputCSV(filteredData, out, delimiter);
	}
	
	
	private static void argumentsValidation(File source, String delimiter, String out, List<String> filters) {
		if (!source.exists() || !source.isFile()) {
			throw new IllegalArgumentException("File specified in \"-path=\" does not exist or not a file.");
		}
		if (!source.getName().endsWith(".csv")) {
			throw new IllegalArgumentException("File specified in \"-path=\" must be .csv extension.");
		}
		if (delimiter.length() != 1) {
			throw new IllegalArgumentException("Illegal delimiter. Please, use single character delimiter.");
		}
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(delimiter);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Illegal delimiter. Please, use special symbol as delimiter.");
		}
		if (!out.equals("stdout") && !out.endsWith(".csv")) {
			throw new IllegalArgumentException(
					"Wrong output target. Please, use \"stdout\" as -out= argument to use console, or path to .csv file.");
		}
		if (filters.isEmpty()) {
			throw new IllegalArgumentException("No filters for data selection. Please, use \"-filter=x,y...\" pattern.");
		}
	}
	
	private static List<List<String>> readCSV(File source, String delimiter) throws IOException {
		List<List<String>> data = new ArrayList<>();
		try (Scanner dataScanner = new Scanner(source)) {
			while (dataScanner.hasNextLine()) {
				String nextLine = dataScanner.nextLine();
				List<String> splittedLine = new ArrayList<>();
				try (Scanner lineScanner = new Scanner(nextLine).useDelimiter(delimiter)) {
					while (lineScanner.hasNext()) {
						splittedLine.add(lineScanner.next());
					}
				}
				data.add(splittedLine);				
			}
		}
		return data;
	}
	
	private static List<List<String>> filterCSVByColumns(List<List<String>> data, List<String> filters) {
		List<List<String>> filteredData = new ArrayList<>();
		List<String> columnNames = data.get(0);
		for (List<String> dataLine : data) {
			filteredData.add(dataLine.stream()
					.filter(value -> filters.contains(columnNames.get(dataLine.indexOf(value))))
					.toList());
		}
		return filteredData;
	}
	
	private static void outputCSV(List<List<String>> data, String target, String delimiter) {
		if ("stdout".equals(target)) {
			for (List<String> dataLine : data) {
				System.out.println(dataLine.stream()
						.collect(Collectors.joining(delimiter)));
			}
		} else {
			try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
				for (List<String> dataLine : data) {
					out.println(dataLine.stream()
							.collect(Collectors.joining(delimiter)));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void main(String[] args) {
		if (args.length != 4) {
			throw new IllegalArgumentException("Please use next arguments pattern: \"-path= -delimiter= -out= -filter=\".");
		}
		ArgsName arguments = ArgsName.of(args);
		try {
			handle(arguments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
