package com.ssafy.sample;

import java.util.Scanner;

public class BookMgTest {
	// *****************************************************************//
	// ***********평가 중 제공 된 메인메서드를 포함하는 파일에는 수정할 부분이 없습니다.**********//
	// ***********명세에 따라 구현하였다면, 정상적으로 실생되어야 하는 코드가 이곳에 구현되어 제공됩니다.********//
	// *****************************************************************//
	private static BookMgr bookMgr = BookMgrImpl.getBookMgr();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("========= 도서 관리 시스템 =========");
			System.out.println("1.도서 등록");
			System.out.println("2.도서 목록 보기");
			System.out.println("0.종료");
			System.out.println("===========================");
			System.out.println("원하는 번호를 선택하세요.");
			int num = sc.nextInt();
			sc.nextLine();
			switch (num) {
			case 0: {
				System.out.println("========= 도서 관리 시스템 종료 =========");
				return;
			}
			case 1: {
				System.out.println("등록할 도서 정보를 입력하세요");
				System.out.println("ISBN : ");
				String isbn = sc.nextLine();
				System.out.println("도서 이름 : ");
				String title = sc.nextLine();
				System.out.println("도서 저자 : ");
				String author = sc.nextLine();
				System.out.println("도서 가격 : ");
				int price = Integer.parseInt(sc.nextLine());

				boolean result = bookMgr.addBook(new Book(isbn, title, author, price));
				if (result) {
					System.out.println("등록이 완료되었습니다.");
				} else {
					System.out.println("이미 등록 된 도서 ISBN입니다.");
				}
				break;
			}
			case 2: {
				System.out.println("========== 전체 도서 목록 ==========");
				Book[] books = bookMgr.searchAll();

				for (int i = 0; i < books.length; i++) {
					System.out.println("도서 " + (i + 1) + " " + books[i]);
				}
				break;
			}
			default: {
				System.out.println("메뉴를 다시 선택해주세요.");
			}

			}
		}
	}
}