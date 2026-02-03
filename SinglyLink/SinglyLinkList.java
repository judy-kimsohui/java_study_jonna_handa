package com.jurib.live04;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

class Node<T> {
	public T data;
	public Node<T> link;
	
		
	public Node(T data) {
		this.data = data;	
	}

	public Node(T data, Node<T> link) {
		super();
		this.data = data;
		this.link = link;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", link=" + link + "]";
	}	
	
}


public class SinglyLinkList<T> {
	
	Node<T> head;
	int size;
	
	public void addFirst(T data) {
		Node<T> node = new Node<>(data);
		node.link = head;
		head = node;
		size++;
	}
	
	
	public String toString() {
		if (head == null) {
			return "[]";
		}
		StringBuffer sb = new StringBuffer("[");
		Node<T> curr = head;
		sb.append(curr.data);
		curr = curr.link;
		while (curr != null) {
			sb.append(", " + curr.data);
			curr = curr.link;
		}
		sb.append("]");
		return sb.toString();
	}
	
	private Node<T> node(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Index:" + idx + ", Size:" + size);
		}
		Node<T> curr = head;
		for (int i = 0; i < idx; i++) {
			curr = curr.link;
		}
		return curr;
	}
	
	public T get(int idx) {
		return node(idx).data;
	}
	
	public void add(T data) {
		
		if (size == 0) {
			addFirst(data);
			return;
		}
		
		Node<T> node = new Node<>(data);
		Node<T> last = node(size-1);
		last.link = node;
		size++;

	}
	
	public T removeFirst() {
		if (head==null) {
			throw new NoSuchElementException();
		}
		Node<T> node = head;
		head = head.link;
		size--;
		return node.data;
	}
	
	public T remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Index:" + idx + ", Size:" + size);
		}
		if (idx == 0) {
			return removeFirst();
		}
		
		Node<T> pre = node(idx - 1);
		Node<T> rm = pre.link;
		
		T data = rm.data;
		pre.link = rm.link;
		rm.link = null;
		size--;		
		return null;
	}

}
