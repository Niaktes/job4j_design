package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class SimpleMap<K, V> implements Map<K, V> {
	
	private static final float LOAD_FACTOR = 0.75f;
	private int capacity = 8;
	private int count = 0;
	private int modCount = 0;
	private MapEntry<K, V>[] table = new MapEntry[capacity];
	
	@Override
	public boolean put(K key, V value) {
		int index = indexFor(hash(key.hashCode()));
		boolean slotIsEmpty = table[index] == null;
		if (slotIsEmpty) {
			if (count / capacity >= LOAD_FACTOR) {
				expand();
			}
			table[index] = new MapEntry<>(key, value);
			count++;
			modCount++;
		}
		return slotIsEmpty;
	}
	
	@Override
	public V get(K key) {
		int index = indexFor(hash(key.hashCode()));
		return (table[index] != null) ? table[index].value : null;
	}
	
	@Override
	public boolean remove(K key) {
		int index = indexFor(hash(key.hashCode()));
		boolean entryExists = table[index] != null;
		if (entryExists) {
			table[index] = null;
			count--;
			modCount++;
		}
		return entryExists;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new Iterator<>() {

			private final int expectedModCount = modCount;
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				if (expectedModCount != modCount) {
					throw new ConcurrentModificationException();
				}
				while (index < capacity - 1 && table[index] == null) {
					index++;
				}
				return table[index] != null;
			}

			@Override
			public K next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return table[index++].key;
			}
		};
	}

	
	private int hash(int hashCode) {
		int hash = 0;
		while (hashCode > 0) {
			hash += hashCode % 10;
			hashCode = hashCode / 10;
		}
		return hash;
	}
	
	private int indexFor(int hash) {
		return hash & (capacity - 1);
	}
	
	private void expand() {
		capacity = capacity * 2;
		MapEntry<K, V>[] newTable = new MapEntry[capacity];
		for (MapEntry<K, V> entrySet : table) {
			int index = indexFor(hash(entrySet.key.hashCode()));
			newTable[index] = entrySet;
		}
		table = newTable;
	}

	
	
	private static class MapEntry<K, V> {
		
		K key;
		V value;
		
		public MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
