package ru.job4j.generics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserStoreTest {

	UserStore store;
	
	@BeforeEach
	void init() {
		store = new UserStore();
	}
	
	@Test
	void whenAddAndFindThenUsernameIsPetr() {
		store.add(new User("1", "Petr"));
		User result = store.findById("1");
		assertEquals("Petr", result.getUsername());
	}
	
	@Test
	void whenAddAndFindThenUserIsNull() {
		store.add(new User("1", "Petr"));
		User result = store.findById("10");
		assertNull(result);
	}
	
	@Test
	void whenAddDuplicateAndFindUsernameIsPetr() {
		store.add(new User("1", "Petr"));
		store.add(new User("1", "Maximilian"));
		User result = store.findById("1");
		assertEquals("Petr", result.getUsername());
	}
	
	@Test
	void whenReplaceThenUsernameIsMaximilian() {
		store.add(new User("1", "Petr"));
		store.replace("1", new User("1", "Maximilian"));
		User result = store.findById("1");
		assertEquals("Maximilian", result.getUsername());
	}
	
	@Test
	void whenNoReplaceUserThenNoChangeUsername() {
		store.add(new User("1", "Petr"));
		store.replace("10", new User("10", "Maximilian"));
		User result = store.findById("1");
		assertEquals("Petr", result.getUsername());
	}
	
	@Test
	void whenDeleteUserThenUserIsNull() {
		store.add(new User("1", "Petr"));
		store.delete("1");
		User result = store.findById("1");
		assertNull(result);
	}
	
	@Test
	void whenNoDeleteUserThenUsernameIsPeter() {
		store.add(new User("1", "Petr"));
		store.delete("10");
		User result = store.findById("1");
		assertEquals("Petr", result.getUsername());
	}

}
