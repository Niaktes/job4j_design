package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

	private Node<T> head;
	
	public void add(T value) {
		Node<T> node = new Node<>(value, null);
		if (head == null) {
			head = node;
			return;
		}
		Node<T> tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
		tail.next = node;
	}
	
	public void addFirst(T value) {
		head = new Node<>(value, head);
	}
		
	public T deleteFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		Node<T> deleted = head;
		T value = head.value;
		head = head.next;
		deleted.next = null;
		deleted.value = null;
		return value;
	}
	
	public boolean revert() {
		boolean reverted = head != null && head.next != null;
		if (reverted) {
			Node<T> prev = null;
			Node<T> point = head;
			while (point != null) {
				Node<T> nextPoint = point.next;
				point.next = prev;
				prev = point;
				point = nextPoint;
			}
			head = prev;
		}
		return reverted;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			Node<T> node = head;

			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				T value = node.value;
				node = node.next;
				return value;
			}
		};
	}
	
	private static class Node<T> {
		
		T value;
		Node<T> next;
		
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

}
