package ru.job4j.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleQueueTest {

	SimpleQueue<Integer> queue;
	
	@BeforeEach
	void init() {
		queue = new SimpleQueue<>();
	}

	@Test
	void whenPushPoll() {
		queue.push(1);
		int actual = queue.poll();
		assertEquals(1, actual);
	}
	
	@Test
	void when2PushPoll() {
		queue.push(1);
		queue.push(2);
		int actual = queue.poll();
		assertEquals(1, actual);
	}
	
	@Test
	void when2PushPollPushPoll() {
		queue.push(1);
		queue.poll();
		queue.push(2);
		int actual = queue.poll();
		assertEquals(2, actual);
	}
	
	@Test
	void whenEmptyPoll() {
		assertThrows(NoSuchElementException.class, () -> queue.poll());
	}
	
	@Test
	void whenPushPushPollAndPush() {
		queue.push(1);
		queue.push(2);
		queue.poll();
		queue.push(3);
		int actual = queue.poll();
		assertEquals(2, actual);
	}

}
