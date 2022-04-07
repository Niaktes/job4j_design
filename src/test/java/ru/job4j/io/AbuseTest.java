package ru.job4j.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AbuseTest {

	@TempDir
	File tempDir; 
		
	@Test
	void drop() throws IOException {
		File source = new File(tempDir, "source.txt");
		File target = new File(tempDir, "target.txt");	
		try (PrintWriter out = new PrintWriter(source)) {
			out.println("hello foolish dude");
			out.println("java job4j php");
		}
		Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));
		StringBuilder rsl = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new FileReader(target))) {
			in.lines().forEach(rsl::append);
		}
		assertEquals("hello dude java job4j ", rsl.toString());
	}

}
