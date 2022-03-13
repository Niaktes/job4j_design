package ru.job4j.generics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

	RoleStore store;
	
	@BeforeEach
	void init() {
		store = new RoleStore();
	}
	
	@Test
	void whenaddAndFindThenTitleFoundByIdIsVillain() {
		store.add(new Role("1", "the villain"));
		Role role = store.findById("1");
		assertEquals("the villain", role.getTitle());
	}
	
	@Test
	void whenAddAndFindWrongThenFoundIsNull() {
		store.add(new Role("1", "the villain"));
		Role role = store.findById("10");
		assertNull(role);
	}
	
	@Test
	void whenAddDuplicateAndFindThenTitleFoundByIdIsVillain() {
		store.add(new Role("1", "the villain"));
		store.add(new Role("1", "the supervillain"));
		Role role = store.findById("1");
		assertEquals("the villain", role.getTitle());
	}
	
	@Test
	void whenReplaceAndFindThenTitleFoundByIdIsSupervillain() {
		store.add(new Role("1", "the villain"));
		store.replace("1", new Role("1", "the supervillain"));
		Role role = store.findById("1");
		assertEquals("the supervillain", role.getTitle());
	}
	
	@Test
	void whenNoReplaceRoleThenTitleFoundByIdIsVillain() {
		store.add(new Role("1", "the villain"));
		store.replace("10", new Role("1", "the supervillain"));
		Role role = store.findById("1");
		assertEquals("the villain", role.getTitle());
	}
	
	@Test
	void whenDeleteThenFoundIsNull() {
		store.add(new Role("1", "the villain"));
		store.delete("1");
		Role role = store.findById("1");
		assertNull(role);
	}
	
	@Test
	void whenNoDeleteRoleThenTitleFoundByIdIsVillain() {
		store.add(new Role("1", "the villain"));
		store.delete("10");
		Role role = store.findById("1");
		assertEquals("the villain", role.getTitle());
	}
	
	

}
