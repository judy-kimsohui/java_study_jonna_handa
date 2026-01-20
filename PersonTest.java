package com.ssafy.ws.step3;

/**
 * 객체 생성 연습하기
 */
public class PersonTest {
	
	public static void main(String[] args) {
		
		Person person1 = new Person();
		person1.showInformation();
		
		System.out.println("기본 생성자를 주석 처리하면 The constructor Person() is undefined 메시지가 출력됩니다.");
		System.out.println("----------------");
	
		Person person2 = new Person("SSAFY", 25, 170.3, "코딩");
		
		person2.showInformation();
		System.out.println("----------------");
		
		Person person3 = new Person("SSAFY", 25, 170.3, "코딩");
		
		// person2와 person3의 hashCode를 호출해 봅시다.
		System.out.println("person2: " + person2.hashCode());
		System.out.println("person3: " + person3.hashCode());
		
		// 동일한 데이터를 주었지만 서로 다른 hashCode임을 확인할 수 있습니다.
	}
}
