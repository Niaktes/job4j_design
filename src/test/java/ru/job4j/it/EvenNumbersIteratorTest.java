package ru.job4j.it;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EvenNumbersIteratorTest {
	
	private Iterator<Integer> it;

	@BeforeEach
	void setUp() {
		it = new EvenNumbersIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
	}

	@Test
	void shouldReturnEvenNumbersSequentially() {
		assertTrue(it.hasNext());
		assertEquals(2, it.next());
		assertTrue(it.hasNext());
		assertEquals(4, it.next());
		assertTrue(it.hasNext());
		assertEquals(6, it.next());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> { 
			it.next(); });
	}
	
	@Test
	void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
		assertEquals(2, it.next());
		assertEquals(4, it.next());
		assertEquals(6, it.next());
	}
	
	@Test
	void shouldReturnFalseIfNoAnyEvenNumbers() {
		it = new EvenNumbersIterator(new int[]{1});
		assertFalse(it.hasNext());
	}

	@Test
	void allNumbersAreEven() {
		it = new EvenNumbersIterator(new int[] {2, 4, 6});
		assertTrue(it.hasNext());
		assertEquals(2, it.next());
		assertTrue(it.hasNext());
		assertEquals(4, it.next());
		assertTrue(it.hasNext());
		assertEquals(6, it.next());
		assertFalse(it.hasNext());
	}
}
