package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

	public static void main(String[] args) {
		MultiplicationExample ex = new MultiplicationExample();
		try (FileOutputStream out = new FileOutputStream("result.txt")) {
			out.write(ex.printMultiplicationTable().getBytes());
			out.write(System.lineSeparator().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
