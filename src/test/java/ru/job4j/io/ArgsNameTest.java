package ru.job4j.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArgsNameTest {

	@Test
	void whenGetFirst() {
		ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
		assertEquals("512", jvm.get("Xmx"));
	}
	
	@Test
	void whenGetFirstReorder() {
		ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
		assertEquals("512", jvm.get("Xmx"));
	}
	
	@Test
	void whenMultipleEqualsSymbol() {
		ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
		assertEquals("?msg=Exit=", jvm.get("request"));
	}
	
	@Test
	void whenNoHyphenSymbol() {
		assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[] {"Xmx=512"}));
	}
	
	@Test
	void whenGetNotExists() {
		ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
		assertThrows(IllegalArgumentException.class, () -> jvm.get("Xms"));
	}
	
	@Test
	void whenWrongSomeArgument() {
		assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx="}));
	}
	
	@Test
	void whenNoKeyInArgumen() {
		assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[] {"=512"}));
	}
	
	@Test
	void whenNoKeyAndValue() {
		assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[] {"="}));
	}
	
	@Test
	void whenNoEqualsSymbol() {
		assertThrows(IllegalArgumentException.class, () -> ArgsName.of(new String[] {"-encodingUTF-8"}));
	}

}
