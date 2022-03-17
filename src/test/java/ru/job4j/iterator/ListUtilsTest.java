package ru.job4j.iterator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class ListUtilsTest {

	@Test
	void whenAddBefore() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
		ListUtils.addBefore(input, 1, 2);
		assertEquals(Arrays.asList(1, 2, 3), input);
	}
	
	@Test
	void whenAddBeforeWithInvalidIndexThenExceptionThrown() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
		assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addBefore(input, 3, 2));
	}
	
	@Test
	void whenAddAfterLast() {
		List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
		ListUtils.addAfter(input, 2, 3);
		assertEquals(Arrays.asList(0, 1, 2, 3), input);
	}	
	
	@Test
	void whenAddAfterInvalidIndexThenExceptionThrown() {
		List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
		assertThrows(IndexOutOfBoundsException.class, () -> ListUtils.addAfter(input, 5, 8));
	}	

	@Test
	void whenRemoveIfDivideByTwoThenOnlyOddNumbers() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		ListUtils.removeIf(input, (v) -> v % 2 == 0);
		assertEquals(Arrays.asList(1, 3, 5), input);
	}	
	
	@Test
	void whenRemoveIfLargerThenThreeThenOnlyLesserNumbers() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		ListUtils.removeIf(input, (v) -> v > 3);
		assertEquals(Arrays.asList(1, 2, 3), input);
	}	
	
	@Test
	void whenReplaceIfEqualsTwoThenValueChanged() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		ListUtils.replaceIf(input, (v) -> v == 2, 99);
		assertEquals(Arrays.asList(1, 99, 3, 4, 5), input);
	}	
	
	@Test
	void whenReplaceIfStringLongerOneSymbolThenValueChanged() {
		List<String> input = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "Jeronimo"));
		ListUtils.replaceIf(input, (v) -> v.length() > 1, "e");
		assertEquals(Arrays.asList("a", "b", "c", "d", "e"), input);
	}	
	
	@Test
	void whenRemoveAllOtherListThenValuesDeleted() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		List<Integer> delete = new ArrayList<>(Arrays.asList(1, 3, 4, 7));
		ListUtils.removeAll(input, delete);
		assertEquals(Arrays.asList(2, 5, 6), input);
	}	
	
	@Test
	void whenRemoveAllSameListThenEmptyList() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		ListUtils.removeAll(input, input);
		assertEquals(Arrays.asList(), input);
	}	
	
	@Test
	void whenRemoveAllAndNoSameValuesThenListDontChange() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
		List<Integer> delete = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
		ListUtils.removeAll(input, delete);
		assertEquals(Arrays.asList(1, 2, 3, 4), input);
	}	

}
