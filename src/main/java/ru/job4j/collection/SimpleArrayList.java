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
			container = Arrays.copyOf(container, container.length * 2);
		}
		container[size] = value;
		size++;
		modCount++;
	}

	@Override
	public T set(int index, T newValue) {
		Objects.checkIndex(index, size);
		T value = container[index];
		container[index] = newValue;
		modCount++;
		return value;
	}

	@Override
	public T remove(int index) {
		Objects.checkIndex(index, size);
		T value = container[index];
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
			
			private T[] iterableContainer = container;
			private final int expectedModCount = modCount;
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				if (expectedModCount != modCount) {
					throw new ConcurrentModificationException();
				}
				return index < iterableContainer.length && iterableContainer[index] != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return iterableContainer[index++];
			}
		};
		
	}
}
