package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

	private final String path;
	private final Map<String, String> values = new HashMap<>();
		
	public Config(final String path) {
		this.path = path;
	}
	
	public void load() {
		try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
			read.lines()
				.filter(s -> !s.startsWith("#") && !s.isBlank())
				.map(s -> s.split("=", 2))
				.forEach(s -> {
					if (s.length != 2 || s[0].isBlank() || s[1].isBlank()) {
						throw new IllegalArgumentException("Pattern of properties are incorrect. Please, check your properties file.");
					}
					values.put(s[0], s[1]);
				});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String value(String key) {
		return values.get(key);
	}
	
	@Override
	public String toString() {
		StringJoiner out = new StringJoiner(System.lineSeparator());
		try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
			read.lines().forEach(out::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Config conf = new Config("app.properties");
		conf.load();
		Class.forName("org.postgresql.Driver");
		String url = conf.value("url");
		String login = conf.value("login");
		String password = conf.value("password");
		try (Connection connection = DriverManager.getConnection(url, login, password)) {
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.getUserName());
			System.out.println(metaData.getURL());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
