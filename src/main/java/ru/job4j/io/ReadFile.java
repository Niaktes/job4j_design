package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class ReadFile {

	public static void main(String[] args) {
/*
 *	прочитаем файл побайтово через InputStream и посимвольно через BufferedReader.	
 */		
		try (FileInputStream in = new FileInputStream("input.txt")) {
			StringBuilder text = new StringBuilder();
			int read;
			while ((read = in.read()) != -1) {
				text.append((char) read);
			}
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
/*
 *	напишем через цикл и через stream api: 
 */
		try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				System.out.println(line);
			}
			in.lines().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
