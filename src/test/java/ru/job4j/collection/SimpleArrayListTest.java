package ru.job4j.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {
	
	List<Integer> list;

	@BeforeEach
	void init() {
		list = new SimpleArrayList<>(3);
		list.add(1);
		list.add(2);
		list.add(3);
	}

	@Test
	void whenAddThenSizeIncrease() {
		assertEquals(3, list.size());
	}
	
	@Test
	void whenAddAndGetByCorrectIndex() {
		assertEquals(Integer.valueOf(1), list.get(0));
	}
	
	@Test
	void whenAddAndGetByIncorrectIndexThenGetException() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
	}
	
	@Test
	void whenRemoveThenGetValueAndSizeDecrease() {
		assertEquals(3, list.size());
		assertEquals(Integer.valueOf(2), list.remove(1));
		assertEquals(2, list.size());
	}
	
	@Test
	void whenRemoveByIncorrectIndexThenGetException() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
	}
	
	@Test
	void whenRemoveThenMustNotBeEmpty() {
		list.remove(1);
		assertEquals(Integer.valueOf(1), list.get(0));
		assertEquals(Integer.valueOf(3), list.get(1));
	}
	
	@Test
	void whenAddNullThenMustBeSameBehavior() {
		list = new SimpleArrayList<>(3);
		list.add(null);
		list.add(null);
		assertEquals(2, list.size());
		assertNull(list.get(0));
		assertNull(list.get(1));
	}
	
	@Test
	void whenSetThenGetOldValueAndSizeNotChanged() {
		assertEquals(Integer.valueOf(2), list.set(1, 22));
		assertEquals(3, list.size());
	}
	
	@Test
	void whenSetByIncorrectIndexThenGetException() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, 22));
	}
	
	@Test
	void whenGetIteratorFromEmptyListThenHasNextResultFalse() {
		list = new SimpleArrayList<>(5);
		assertFalse(list.iterator().hasNext());
	}
	
	@Test
	void whenGetIteratorFromEmptyListThenNextThrowException() {
		list = new SimpleArrayList<>(5);
		Iterator<Integer> iterator = list.iterator();
		assertThrows(NoSuchElementException.class, () -> iterator.next());
	}
	
	@Test
	void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
		assertEquals(Integer.valueOf(1), list.iterator().next());
		assertEquals(Integer.valueOf(1), list.iterator().next());
	}
	
	@Test
	void whenCheckIterator() {
		Iterator<Integer> iterator = list.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(1), iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(2), iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(3), iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void whenNoPlaceThenMustIncreaseCapacity() {
		assertEquals(3, list.size());
		IntStream.range(3, 10).forEach(v -> list.add(v));
		assertEquals(10, list.size());
	}
	
	@Test
	void whenAddAfterGetIteratorThenMustBeException() {
		Iterator<Integer> iterator = list.iterator();
		list.add(4);
		assertThrows(ConcurrentModificationException.class, () -> iterator.next());
	}
	
	@Test
	void whenRemoveAfterGetIteratorThenMustBeException() {
		Iterator<Integer> iterator = list.iterator();
		list.add(0);
		assertThrows(ConcurrentModificationException.class, () -> iterator.next());
	}
	
	@Test
	void whenArryIsZeroLengthThenIncreaseCapacity() {
		list = new SimpleArrayList<>(0);
		assertEquals(0, list.size());
		IntStream.range(1, 4).forEach(v -> list.add(v));
		assertEquals(3, list.size());
	}

}
