package ru.job4j.set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleSetTest {

	Set<Integer> set;
	
	@BeforeEach
	void init() {
		set = new SimpleSet<>();
	}

	@Test
	void whenAddNonNull() {
		assertTrue(set.add(1));
		assertTrue(set.contains(1));
		assertFalse(set.add(1));
	}
	
	@Test
	void whenAddNull() {
		assertTrue(set.add(null));
		assertTrue(set.contains(null));
		assertFalse(set.add(null));
	}
	
	@Test
	void whenContainsWrongValueThenFalse() {
		assertTrue(set.add(1));
		assertFalse(set.contains(5));
	}
		
	@Test
	void whenCheckIterator() {
		assertTrue(set.add(1));
		assertTrue(set.add(2));
		Iterator<Integer> iterator = set.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(1), iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(2), iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void whenAddAfterGetIteratorThenMustBeException() {
		assertTrue(set.add(1));
		Iterator<Integer> iterator = set.iterator();
		assertTrue(set.add(2));
		assertThrows(ConcurrentModificationException.class, () -> iterator.next());
	}
	
	@Test
	void whenGetIteratorFromEmptySetThenNextThrowException() {
		Iterator<Integer> iterator = set.iterator();
		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}

}
