package ru.job4j.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleMapTest {

	SimpleMap<Integer, String> map;
	
	@BeforeEach
	void init() {
		map = new SimpleMap<>();
	}

	@Test
	void whenPutTenEntriesThenCapacityExpand() {
		for (int i = 1; i < 9; i++) {
			String value = String.format("%d", i);
			assertTrue(map.put(i, value));
		}
	}
	
	@Test
	void whenPutWithSameKeyThenFalse() {
		assertTrue(map.put(1, "One"));		
		assertFalse(map.put(1, "One"));		
	}
	
	@Test
	void whenPutWithSameKeyHashThenFalse() {
		assertTrue(map.put(1, "One"));		
		assertFalse(map.put(9, "One"));		
	}
	
	@Test
	void whenPutAndGet() {
		map.put(1, "One");
		assertEquals("One", map.get(1));
	}
	
	@Test
	void whenGetWrongKeyThenNull() {
		map.put(1, "One");
		assertNull(map.get(2));
	}
	
	@Test
	void whenGetFromEmptyMapThenNull() {
		assertNull(map.get(1));
	}
	
	@Test
	void whenPutGetRemoveAndGetThenNull() {
		assertTrue(map.put(1, "One"));
		assertEquals("One", map.get(1));
		assertTrue(map.remove(1));
		assertNull(map.get(1));
	}
	
	@Test
	void whenRemoveWrongKeyThenFalse() {
		map.put(1, "One");
		assertFalse(map.remove(2));
		assertEquals("One", map.get(1));
	}
	
	@Test
	void whenMapWasChangedThenIteratorThrowsException() {
		map.put(1, "One");
		map.put(2, "Two");
		Iterator<Integer> it = map.iterator();
		map.put(3, "Three");
		assertThrows(ConcurrentModificationException.class, () -> it.hasNext());
	}
	
	@Test
	void whenEmptyMapThenIteratorHasNextFalse() {
		Iterator<Integer> it = map.iterator();
		assertFalse(it.hasNext());
	}
	
	@Test
	void whenEmptyMapThenIteratorNextThrowsException() {
		Iterator<Integer> it = map.iterator();
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	@Test
	void whenElementsRanOutThenIteratorNextThrowsException() {
		map.put(1, "One");
		Iterator<Integer> it = map.iterator();
		assertEquals(1, it.next());
		assertThrows(NoSuchElementException.class, () -> it.next());
	}
	
	@Test
	void whenIteratorHasNextAndNext() {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		Iterator<Integer> it = map.iterator();
		assertTrue(it.hasNext());
		assertEquals(1, it.next());
		assertTrue(it.hasNext());
		assertEquals(2, it.next());
		assertTrue(it.hasNext());
		assertEquals(3, it.next());
		assertFalse(it.hasNext());
	}
	
	

}
