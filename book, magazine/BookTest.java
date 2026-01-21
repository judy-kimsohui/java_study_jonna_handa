package com.ssafy.ws.step3;

public class BookTest {
	public static void main(String[] args) {
		
		BookManager manager = new BookManager();

		// 도서 추가
		manager.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본 문법"));
		manager.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 응용"));
		manager.add(new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델링"));
		manager.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 2021, 1));

		// 도서 전체 목록
		System.out.println("********************도서 전체 목록********************");
		for (Book b : manager.getList()) {
			System.out.println(b);
		}

		// 일반 도서 목록
		System.out.println("********************일반 도서 목록********************");
		for (Book b : manager.getBooks()) {
			System.out.println(b);
		}

		// 잡지 목록
		System.out.println("********************잡지 목록********************");
		for (Magazine m : manager.getMagazines()) {
			System.out.println(m);
		}

		// 제목 검색
		System.out.println("********************도서 제목 포함검색 : Java********************");
		for (Book b : manager.getList()) {
			if (b.getTitle().contains("Java")) {
				System.out.println(b);
			}
		}

		// 가격 정보
		System.out.println("도서 가격 총합: " + manager.getTotalPrice());
		System.out.println("도서 가격 평균: " + manager.getPriceAvg());
	}
}
