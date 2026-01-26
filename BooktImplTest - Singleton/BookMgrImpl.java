package com.ssafy.sample;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BookMgrImpl implements BookMgr {

	//*****************************************************************//
	//*********** 문서를 읽고 명세와 결과 화면에 따라 구현하세요. *************//
	//*****************************************************************//
	
	int MAX_SIZE = 100;
	Book[] bookList = new Book[MAX_SIZE];
	int size = 0;
	
	
	// 싱글톤
	private static BookMgr instance = new BookMgrImpl();
	
	private BookMgrImpl() {
        // 더미 데이터
        addBook(new Book("ISBN001", "자바의 정석", "남궁성", 30000));
        addBook(new Book("ISBN002", "이것이 자바다", "신용권", 28000));
        addBook(new Book("ISBN003", "Effective Java", "Joshua Bloch", 45000));
 
	}
	
	public static BookMgr getBookMgr() {
	    return instance;
	}

	@Override
	public boolean addBook(Book book) {				
		if (size<=100) {
			bookList[size] = book;
			size += 1;			
			return true;
		}
		else 
			return false;
	}
	
	@Override
	public Book[] searchAll() {
		
		return Arrays.copyOf(bookList.clone(), size);
	}
	

}
