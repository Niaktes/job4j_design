package ru.job4j.question;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class AnalizeTest {

	@Test
	void whenNotChanged() {
		User u1 = new User(1, "A");
		User u2 = new User(2, "B");
		User u3 = new User(3, "C");
		Set<User> previous = Set.of(u1, u2, u3);
		Set<User> current = Set.of(u1, u2, u3);
		assertEquals(new Info(0, 0, 0), Analize.diff(previous, current));
	}
	
	@Test
	void whenOneChanged() {
		User u1 = new User(1, "A");
		User u2 = new User(2, "B");
		User u3 = new User(3, "C");
		Set<User> previous = Set.of(u1, u2, u3);
		Set<User> current = Set.of(u1, new User(2, "BB"), u3);
		assertEquals(new Info(0, 1, 0), Analize.diff(previous, current));
	}
	
	@Test
	void whenOneDeleted() {
		User u1 = new User(1, "A");
		User u2 = new User(2, "B");
		User u3 = new User(3, "C");
		Set<User> previous = Set.of(u1, u2, u3);
		Set<User> current = Set.of(u1, u3);
		assertEquals(new Info(0, 0, 1), Analize.diff(previous, current));
	}
	
	@Test
	void whenOneAdded() {
		User u1 = new User(1, "A");
		User u2 = new User(2, "B");
		User u3 = new User(3, "C");
		Set<User> previous = Set.of(u1, u2, u3);
		Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
		assertEquals(new Info(1, 0, 0), Analize.diff(previous, current));
	}
	
	@Test
	void whenAllChanged() {
		User u1 = new User(1, "A");
		User u2 = new User(2, "B");
		User u3 = new User(3, "C");
		Set<User> previous = Set.of(u1, u2, u3);
		Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
		assertEquals(new Info(1, 1, 1), Analize.diff(previous, current));
	}
	
	@Test
	void whenMoreChanged() {
		User u1 = new User(1, "A");
		User u2 = new User(2, "B");
		User u3 = new User(3, "C");
		User u4 = new User(4, "D");
		User u5 = new User(5, "E");
		Set<User> previous = Set.of(u1, u2, u3, u4, u5);
		Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "DD"), new User(7, "A"), new User(15, "B"));
		assertEquals(new Info(2, 2, 2), Analize.diff(previous, current));
	}

}
