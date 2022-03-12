package ru.job4j.it;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class FlatMapTest {

	@Test
	void whenDiffNext() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1).iterator(),
				List.of(2, 3).iterator()
				).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertEquals(1, flat.next());
		assertEquals(2, flat.next());
		assertEquals(3, flat.next());
	}
	
	@Test
	void whenSeqNext() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1, 2, 3).iterator()
				).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertEquals(1, flat.next());
		assertEquals(2, flat.next());
		assertEquals(3, flat.next());
	}
	
	@Test
	void whenMultiHasNext() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1).iterator()
				).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertTrue(flat.hasNext());
		assertTrue(flat.hasNext());
	}
	
	@Test
	void whenHasNextFalse() {
		Iterator<Iterator<Integer>> data = List.of(
				List.of(1).iterator()
				).iterator();
		FlatMap<Integer> flat = new FlatMap<>(data);
		assertEquals(1, flat.next());
		assertFalse(flat.hasNext());
	}
	
	@Test
	void whenEmpty() {
		Iterator<Iterator<Object>> data = List.of(
				List.of().iterator()
				).iterator();
		FlatMap<Object> flat = new FlatMap<>(data);
		assertThrows(NoSuchElementException.class, () -> { 
			flat.next(); });
	}
	
	@Test
	void whenSeveralEmptyAndNotEmpty() {
		Iterator<Iterator<?>> it = List.of(
				List.of().iterator(),
				List.of().iterator(),
				List.of().iterator(),
				List.of(1).iterator()
				).iterator();
		FlatMap flat = new FlatMap(it);
		assertTrue(flat.hasNext());
		assertEquals(1, flat.next());
	}
	
	@Test
	void whenSeveralEmptyThenReturnFalse() {
		Iterator<Iterator<Object>> it = List.of(
				List.of().iterator(),
				List.of().iterator(),
				List.of().iterator(),
				List.of().iterator()
				).iterator();
		FlatMap<Object> flat = new FlatMap<>(it);
		assertFalse(flat.hasNext());
	}
	
}
