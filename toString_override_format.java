package com.ssafy.ws.step3;
/**
 * 도서 정보를 나타내는 클래스
 */
public class Book {
	
	String isbn;
	String title;
	String author;
	String publisher;
	int price;
	String desc;
	
	Book() {
		
	}
	
	Book(String isbn, String title, String author, String publisher, int price, String desc) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;
	}
	
	@Override
	public String toString() {		
		return String.format(
		    "%s | %-15s | %s | %s | %d | %s",
		    isbn, title, author, publisher, price, desc
		);		
	}
	
}

