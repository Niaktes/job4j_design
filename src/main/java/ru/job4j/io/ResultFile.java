package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {

	public static void main(String[] args) {
/*
 * Рассмотрим два варианта: побайтовую запись и черезBuffered OutputStream: 
 */
		MultiplicationExample ex = new MultiplicationExample();
		try (FileOutputStream out = new FileOutputStream("result.txt")) {
			out.write(ex.printMultiplicationTable().getBytes());
			out.write(System.lineSeparator().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (PrintWriter out = new PrintWriter(
				new BufferedOutputStream(
						new FileOutputStream("result.txt")))) {
			out.println(ex.printMultiplicationTable());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
