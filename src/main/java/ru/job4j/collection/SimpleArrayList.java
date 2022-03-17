package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {
	
	private T[] container;
	private int size;
	private int modCount;
	
	@SuppressWarnings("unchecked")
	public SimpleArrayList(int capacity) {
		this.container = (T[]) new Object[capacity];
	}

	@Override
	public void add(T value) {
		if (size == container.length) {
			increaseCapacity();
		}
		container[size] = value;
		size++;
		modCount++;
	}

	@Override
	public T set(int index, T newValue) {
		T value = get(index);
		container[index] = newValue;
		modCount++;
		return value;
	}

	@Override
	public T remove(int index) {
		T value = get(index);
		size--;
		if (index < size) {
			System.arraycopy(container, index + 1, container, index, size - index);
		}
		container[size] = null;
		modCount++;
		return value;
	}

	@Override
	public T get(int index) {
		Objects.checkIndex(index, size);
		return container[index];
	}

	@Override
	public int size() {
		return size;
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
				return container[index++];
			}
		};		
	}
	
	private void increaseCapacity() {
		if (container.length == 0) {
			container = Arrays.copyOf(container, 2);
		} else {
			container = Arrays.copyOf(container, container.length * 2);
		}
	}
}
