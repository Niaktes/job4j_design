package ru.job4j.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ForwardLinkedTest {

	ForwardLinked<Integer> linked;
	
	@BeforeEach
	void init() {
		linked = new ForwardLinked<>();
	}

	@Test
	void whenDeleFirstAndIterateThenExceptionThrown() {
		linked.add(1);
		linked.deleteFirst();
		Iterator<Integer> it = linked.iterator();
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	@Test
	void whenDeleEmptyThenExceptionThrown() {
		assertThrows(NoSuchElementException.class, () -> linked.deleteFirst());
	}
	
	@Test
	void whenMultiDelete() {
		linked.add(1);
		linked.add(2);
		assertEquals(1, linked.deleteFirst());
		Iterator<Integer> it = linked.iterator();
		assertEquals(2, it.next());
	}
	
	@Test
	void whenAddThenIter() {
		linked.add(1);
		linked.add(2);
		Iterator<Integer> it = linked.iterator();
		assertEquals(1, it.next());
		assertEquals(2, it.next());
	}
	
	@Test
	void whenAddAndRevertThenIter() {
		linked.add(1);
		linked.add(2);
		linked.revert();
		Iterator<Integer> it = linked.iterator();
		assertEquals(2, it.next());
		assertEquals(1, it.next());
	}
	
	@Test
	void whenSize0ThenReturnFalse() {
		assertFalse(linked.revert());
	}
	
	@Test
	void whenSize1ThenReturnFalse() {
		linked.add(1);
		assertFalse(linked.revert());
	}
	
}
