package ru.job4j.collection.linkedlist;

public class Node<E> {
	
	E item;
	Node<E> prev;
	Node<E> next;
	
	Node(Node<E> prev, E element, Node<E> next) {
		this.prev = prev;
		this.item = element;
		this.next = next;
	}

}
