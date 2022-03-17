package ru.job4j.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleStackTest {

	SimpleStack<Integer> stack;
	
	@BeforeEach
	void init() {
		stack = new SimpleStack<>();
	}

	@Test
	void whenPushThenPop() {
		stack.push(1);
		assertEquals(1, stack.pop());
	}
	
	@Test
	void whenPushPopThenPushPop() {
		stack.push(1);
		stack.pop();
		stack.push(2);
		assertEquals(2, stack.pop());
	}
	
	@Test
	void whenPushPushThenPopPop() {
		stack.push(1);
		stack.push(2);
		stack.pop();
		assertEquals(1, stack.pop());
	}

}
