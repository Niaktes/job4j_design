package ru.job4j.it;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class MatrixItTest {

	@Test
	void when4El() {
		int[][] in = {
				{1}
		};
		MatrixIt it = new MatrixIt(in);
		assertEquals(1, it.next());
	}
	
	@Test
	void whenFirstEmptyThenNext() {
		int[][] in = {
				{}, {1}
		};
		MatrixIt it = new MatrixIt(in);
		assertEquals(1, it.next());
	}
	
	@Test
	void whenFirstIsEmptyThenHasNext() {
		int[][] in = {
				{}, {1}
		};
		MatrixIt it = new MatrixIt(in);
		assertTrue(it.hasNext());
	}
	
	@Test
	void whenRowHasDiffSize() {
		int[][] in = {
				{1}, {2, 3}
		};
		MatrixIt it = new MatrixIt(in);
		assertEquals(1, it.next());
		assertEquals(2, it.next());
		assertEquals(3, it.next());
	}
	
	@Test
	void whenFewEmpty() {
		int[][] in = {
				{1}, {}, {}, {}, {2}
		};
		MatrixIt it = new MatrixIt(in);
		assertEquals(1, it.next());
		assertEquals(2, it.next());
	}
		
	@Test
	void whenEmpty() {
		int[][] in = {
				{}
		};
		MatrixIt it = new MatrixIt(in);
		assertFalse(it.hasNext());
	}

	@Test
	void whenEmptyThenNext() {
		int[][] in = {
				{}
		};
		MatrixIt it = new MatrixIt(in);
		assertThrows(NoSuchElementException.class, () -> { 
			it.next(); });
	}
	
	@Test
	void whenMultiHasNext() {
		int[][] in = {
				{}, {1}
		};
		MatrixIt it = new MatrixIt(in);
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
	}
	
	@Test
	void whenNoElements() {
		int[][] in = {{}, {}, {}};
		MatrixIt it = new MatrixIt(in);
		assertFalse(it.hasNext());
	}
}
