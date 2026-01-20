package com.ssafy.ws.step3;

/**
 * 클래스 생성 연습하기
 * 
 * 클래스는 멤버 변수(필드), 생성자, 멤버 메소드로 이루어져있습니다.
 * "기본 생성자"와 "매개변수가 있는 생성자"를 명시적으로 선언
 * 
 */
public class Person {
	// 멤버 변수
	String name;
	int age;
	double height;
	String hobby;
		
	// 기본 생성자
	Person() {
		this.name = null;
		this.age = 0;
		this.height = 0.0d;
		this.hobby = null;		
	}
	
	// 매개변수가 있는 생성자
	Person(String name, int age, double height, String hobby) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.hobby = hobby;		
	}
	
	void showInformation() {
		System.out.printf("저의 이름은 %s입니다.\n", name);
		System.out.printf("저의 나이는 %d입니다.\n", age);
		System.out.printf("저의 키는 %.1f입니다.\n", height);
		System.out.printf("저의 취미는 %s입니다.\n", hobby);
		
	}	
}
