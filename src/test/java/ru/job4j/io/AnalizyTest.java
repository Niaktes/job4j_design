package ru.job4j.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AnalizyTest {
	
	Analizy analizy;
	
	@BeforeEach
	void init() {
		analizy = new Analizy();
	}

	@TempDir
	File tempDir;
	
	@Test
	void whenServerWasUnavailableThenTimeIsLogged() throws IOException {
		File source = new File(tempDir, "source.txt");
		File target = new File(tempDir, "target.txt");
		try (PrintWriter out = new PrintWriter(source)) {
			out.println("500 10:02:51");
			out.println("200 11:01:01");
			out.println("400 15:11:11");
			out.println("300 15:12:30");
		}
		analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
		StringBuilder rsl = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new FileReader(target))) {
			in.lines().forEach(rsl::append);
		}
		assertEquals("10:02:51;11:01:01;15:11:11;15:12:30;", rsl.toString());
	}

	@Test
	void whenServerWasNotUnavailableThenLogIsEmpty() throws IOException {
		File source = new File(tempDir, "source.txt");
		File target = new File(tempDir, "target.txt");
		try (PrintWriter out = new PrintWriter(source)) {
			out.println("200 10:02:51");
			out.println("300 11:01:01");
			out.println("200 15:11:11");
		}
		analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
		StringBuilder rsl = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new FileReader(target))) {
			in.lines().forEach(rsl::append);
		}
		assertEquals("", rsl.toString());
	}
	
	@Test
	void whenServerWasNotAvailableThenLogIsEmpty() throws IOException {
		File source = new File(tempDir, "source.txt");
		File target = new File(tempDir, "target.txt");
		try (PrintWriter out = new PrintWriter(source)) {
			out.println("500 10:02:51");
			out.println("400 11:01:01");
			out.println("500 15:11:11");
		}
		analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
		StringBuilder rsl = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new FileReader(target))) {
			in.lines().forEach(rsl::append);
		}
		assertEquals("", rsl.toString());
	}

}
