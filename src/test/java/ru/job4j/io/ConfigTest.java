package ru.job4j.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConfigTest {

	@Test
	void whenPairWithoutComment() {
		String path = "./data/pair_without_comment.properties";
		Config config = new Config(path);
		config.load();
		assertEquals("Petr Arsentev", config.value("name"));
		assertNull(config.value("surname"));
	}
	
	@Test
	void whenPairWithCommentsAndBlankLines() {
		String path = "./data/pairs_with_comments&emptyLines.properties";
		Config config = new Config(path);
		config.load();
		assertEquals("Petr Arsentev", config.value("name"));
		assertEquals("Sergey Zakharenko", config.value("other name"));
		assertNull(config.value(" "));
		assertNull(config.value("comment"));
	}
	
	@Test
	void whenKeyValuePatternIsWrong() {
		String path = "./data/wrong_pattern.properties";
		Config config = new Config(path);
		assertThrows(IllegalArgumentException.class, () -> config.load());
	}
	
}
