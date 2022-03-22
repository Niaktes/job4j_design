package ru.job4j.tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleTreeTest {
	
	SimpleTree<Integer> tree;

	@BeforeEach
	void init() {
		tree = new SimpleTree<>(1);
	}

	@Test
	void when6ElementFindLastThen6() {
		tree.add(1, 2);
		tree.add(1, 3);
		tree.add(1, 4);
		tree.add(4, 5);
		tree.add(5, 6);
		assertTrue(tree.findBy(6).isPresent());
	}
	
	@Test
	void when6ElementFindNotExistsThenOptionalEmpty() {
		tree.add(1, 2);
		assertFalse(tree.findBy(7).isPresent());
	}
	
	@Test
	void whenChildExistOnLeafThenNotAdd() {
		tree.add(1, 2);
		tree.add(1, 3);
		tree.add(1, 4);
		tree.add(4, 5);
		tree.add(5, 6);
		assertFalse(tree.add(2, 6));
	}
	
	@Test
	void whenParentDontExistsThenNotFalse() {
		tree.add(1, 2);
		tree.add(2, 3);
		assertFalse(tree.add(4, 5));
	}
	
	@Test
	void whenLessThan2ChildrenThenIsBinaryTrue() {
		tree.add(1, 2);
		tree.add(1, 3);
		tree.add(2, 4);
		tree.add(2, 5);
		tree.add(3, 6);
		tree.add(3, 7);
		tree.add(5, 8);
		tree.add(5, 9);
		tree.add(9, 10);
		assertTrue(tree.isBinary());
	}
	
	@Test
	void whenMoreThan2ChildrenThenIsBinaryFalse() {
		tree.add(1, 2);
		tree.add(1, 3);
		tree.add(2, 4);
		tree.add(2, 5);
		tree.add(2, 6);
		tree.add(3, 7);
		tree.add(3, 8);
		assertFalse(tree.isBinary());
	}
	
	@Test
	void whenEmptyTreeThenIsBinaryTrue() {
		assertTrue(tree.isBinary());
	}

}
