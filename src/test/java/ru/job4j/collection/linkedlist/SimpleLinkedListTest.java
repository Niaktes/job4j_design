package ru.job4j.collection.linkedlist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleLinkedListTest {

	LinkedList<Integer> list;
	
	@BeforeEach
	void init() {
		list = new SimpleLinkedList<>();
	}
	
	@Test
	void whenAddAndGet() {
		list.add(1);
		list.add(2);
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
	}

	@Test
	void whenGetFromOutOfBoundThenExceptionThrown() {
		list.add(1);		
		list.add(2);
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
	}
	
	@Test
	void whenAddIterHasNextTrue() {
		list.add(1);		
		Iterator<Integer> it = list.iterator();
		assertTrue(it.hasNext());
	}
	
	@Test
	void whenAddIterNextOne() {
		list.add(1);		
		Iterator<Integer> it = list.iterator();
		assertEquals(1, it.next());
	}
	
	@Test
	void whenEmptyIterHasNextFalse() {
		Iterator<Integer> it = list.iterator();
		assertFalse(it.hasNext());
	}
	
	@Test
	void whenAddIterMultiHasNextTrue() {
		list.add(1);
		Iterator<Integer> it = list.iterator();
		assertTrue(it.hasNext());
		assertTrue(it.hasNext());
	}
	
	@Test
	void whenAddIterNextOneNextTwo() {
		list.add(1);
		list.add(2);
		Iterator<Integer> it = list.iterator();
		assertEquals(1, it.next());
		assertEquals(2, it.next());
	}
	
	@Test
	void whenGetIteratorTwiceThenEveryFromBegin() {
		list.add(1);
		list.add(2);
		Iterator<Integer> first = list.iterator();
		assertTrue(first.hasNext());
		assertEquals(1, first.next());
		assertTrue(first.hasNext());
		assertEquals(2, first.next());
		assertFalse(first.hasNext());
		Iterator<Integer> second = list.iterator();
		assertTrue(second.hasNext());
		assertEquals(1, second.next());
		assertTrue(second.hasNext());
		assertEquals(2, second.next());
		assertFalse(second.hasNext());
	}
	
}
