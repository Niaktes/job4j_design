package ru.job4j.it;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BackwardArrayItTest {

	@Test
	void whenMultiCallHasNextThenTrue() {
		BackwardArrayIt it = new BackwardArrayIt(new int[] {1, 2, 3});
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
	}

	@Test
	void whenReadSequence() {
		BackwardArrayIt it = new BackwardArrayIt(new int[] {1, 2, 3});
		assertEquals(3, it.next());
		assertEquals(2, it.next());
		assertEquals(1, it.next());
	}

	@Test
	void whenNextFromEmpty() {
		BackwardArrayIt it = new BackwardArrayIt(new int[] {});
		assertThrows(NoSuchElementException.class, () -> { 
			it.next(); });
	}	
}
