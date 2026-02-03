package com.jurib.live05;

import java.util.NoSuchElementException;

class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    Node(T data) {
        this.data = data;
    }
}

public class DoubleLinkList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /* ================= 추가 ================= */

    // 맨 앞에 추가
    public void addFirst(T data) {
        Node<T> node = new Node<>(data);

        if (size == 0) { // 처음 더블링크리스트 만들 경우  
            head = tail = node;
        } else { // 리스트가 존재할 경우 
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    // 맨 뒤에 추가
    public void addLast(T data) {
        if (size == 0) {
            addFirst(data);
            return;
        }

        Node<T> node = new Node<>(data);
        tail.next = node;
        node.prev = tail;
        tail = node;
        size++;
    }

    /* ================= 조회 ================= */

    private Node<T> node(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException(
                "Index: " + idx + ", Size: " + size
            );
        }

        Node<T> curr;

        // 앞에서 탐색
        if (idx < size / 2) {
            curr = head;
            for (int i = 0; i < idx; i++) {
                curr = curr.next;
            }
        }
        // 뒤에서 탐색
        else {
            curr = tail;
            for (int i = size - 1; i > idx; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    public T get(int idx) {
        return node(idx).data;
    }

    /* ================= 삭제 ================= */

    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        T data = head.data;

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public T removeLast() {
    	if (head == null) {
    		throw new NoSuchElementException();    		
    	}
    	
        if (size == 1) {
            return removeFirst();
        }

        T data = tail.data;

        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public T remove(int idx) {
    	// 예외 처리
        if (idx == 0) return removeFirst();
        if (idx == size - 1) return removeLast();

        Node<T> rm = node(idx);
        T data = rm.data;

        rm.prev.next = rm.next;
        rm.next.prev = rm.prev;

        rm.next = rm.prev = null;
        size--;
        return data;
    }

    /* ================= 기타 ================= */

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> curr = head;

        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
