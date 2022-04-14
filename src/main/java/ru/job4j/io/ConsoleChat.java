package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
	
	private static final String OUT = "закончить";	
	private static final String STOP = "стоп";	
	private static final String CONTINUE = "продолжить";	
	private final String path;	
	private final String botAnswers;
	private Random random = new Random();
	
	public ConsoleChat(String path, String botAnswers) {
		this.path = path;
		this.botAnswers = botAnswers;
	}
	
	public void run() {
		boolean stopped = false;
		List<String> answers = readPhrases();
		List<String> log = new ArrayList<>();
		System.out.println("Добро пожаловать в приложение \"Консольный чат\"");
		System.out.println("Если хотите приостановить работу чата, введите: " + STOP);
		System.out.println("Если хотите продолжить работу чата, введите: " + CONTINUE);
		System.out.println("Если хотите закончить работу чата, введите: " + OUT);
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		while (!OUT.equals(userInput)) {
			userInput = scanner.nextLine();
			log.add(userInput);
			if (STOP.equals(userInput)) {
				stopped = true;
			}
			if (CONTINUE.equals(userInput)) {
				stopped = false;
			}
			if (!stopped && !OUT.equals(userInput)) {
				String answer = answers.get(random.nextInt(answers.size()));
				System.out.println(answer);
				log.add(answer);
			}
		}
		scanner.close();
		saveLog(log);		
	}
	
	
	private List<String> readPhrases() {
		List<String> phrases = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
			input.lines().forEach(phrases::add);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return phrases;
	}
	
	private void saveLog(List<String> log) {
		try (PrintWriter out = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
			out.println();
			out.println(new Timestamp(System.currentTimeMillis()));
			log.forEach(out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		ConsoleChat cc = new ConsoleChat("./data/consoleChat/chat.log", "./data/consoleChat/botAnswers.txt");
		cc.run();
	}

}
