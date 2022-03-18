package ru.job4j.set;

import java.util.Iterator;
import java.util.Objects;

import ru.job4j.collection.SimpleArrayList;

public class SimpleSet<T> implements Set<T> {

	private SimpleArrayList<T> set = new SimpleArrayList<>(4);
	private int modCount;
	private int size;

	@Override
	public boolean add(T value) {
		boolean success = !contains(value);
		if (success) {
			set.add(value);
			size++;
			modCount++;
		}
		return success;
	}

	@Override
	public boolean contains(T value) {
		boolean contain = false;
		for (T element : set) {
			if (Objects.equals(value, element)) {
				contain = true;
			}
		}
		return contain;
	}
	
	@Override
	public Iterator<T> iterator() {
		return set.iterator(); 
	}

}
