package com.ssafy.ws.step3;

import java.util.Arrays;

public class BookManager {

	private int MAX_SIZE = 100;
	private Book[] books = new Book[MAX_SIZE];
	private int size = 0;
	
	public void add(Book book) {
		if (size >= MAX_SIZE) {
	        System.out.println("더 이상 추가할 수 없습니다.");
	        return;
	    }
		books[size] = book;
		size++;
	}
	
	public void remove(String isbn) {
		for (int i = 0; i < size; i++) {
			if (books[i].getIsbn().equals(isbn)) {
				for (int k = i; k < size; k++) {
					books[k] = books[k+1];
				}
				size--;
			}
		}
	}
	
	public Book[] getList() {
		return Arrays.copyOf(books, size);
	}
	
	public Book searchByIsbn(String isbn) {
		for (int i = 0; i < size; i++) {
			if (books[i].getIsbn().equals(isbn)) {
				return books[i];				
			}
		}
		return null;
	}
	
	public Book searchByTitle(String title) {
		for (int i = 0; i < size; i++) {
			if (books[i].getTitle().contains(title)) {
				return books[i];				
			}
		}
		return null;
	}
	
	public Magazine[] getMagazines() {
	    int count = 0;
	    for (int i = 0; i < size; i++) {
	        if (books[i] instanceof Magazine) {
	            count++;
	        }
	    }

	    Magazine[] result = new Magazine[count];
	    int idx = 0;
	    for (int i = 0; i < size; i++) {
	        if (books[i] instanceof Magazine) {
	            result[idx++] = (Magazine) books[i];
	        }
	    }
	    return result;
	}

	
	public Book[] getBooks() {
	    int count = 0;
	    for (int i = 0; i < size; i++) {
	        if (!(books[i] instanceof Magazine)) {
	            count++;
	        }
	    }

	    Book[] result = new Book[count];
	    int idx = 0;
	    for (int i = 0; i < size; i++) {
	        if (!(books[i] instanceof Magazine)) {
	            result[idx++] = books[i];
	        }
	    }
	    return result;
	}

	public int getTotalPrice() {
	    int sum = 0;
	    for (int i = 0; i < size; i++) {
	        sum += books[i].getPrice();
	    }
	    return sum;
	}
	
	public double getPriceAvg() {
	    if (size == 0) return 0;
	    return (double) getTotalPrice() / size;
	}	
}
