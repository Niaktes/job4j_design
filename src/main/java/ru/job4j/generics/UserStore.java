package ru.job4j.generics;

public class UserStore implements Store<User> {

	private final Store<User> store = new MemStore<>();
	
	@Override
	public void add(User user) {
		store.add(user);
	}

	@Override
	public boolean replace(String id, User user) {
		return store.replace(id, user);
	}

	@Override
	public boolean delete(String id) {
		return store.delete(id);
	}

	@Override
	public User findById(String id) {
		return store.findById(id);
	}
	
}
