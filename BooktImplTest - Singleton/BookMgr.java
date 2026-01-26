package com.ssafy.sample;

public interface BookMgr {
	//*****************************************************************//
	//***********주의 : 평가 중 제공 된 Interface를 수정하면 안됩니다. *************//
	//*****************************************************************//

	public boolean addBook(Book book);
	
	public Book[] searchAll();
}
