package ru.job4j.set;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
		return new Iterator<T>() {
			
			private final int expectedModCount = modCount;
			private int index = 0;

			@Override
			public boolean hasNext() {
				if (expectedModCount != modCount) {
					throw new ConcurrentModificationException();
				}
				return index < size;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return set.get(index++);
			}
		};
	}

}
