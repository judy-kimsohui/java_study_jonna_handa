package com.jurib.live04;

public class SinglyLinkTest {

	public static void main(String[] args) {
		SinglyLinkList<String> link = new SinglyLinkList<>();
		link.addFirst("김소희");
		link.addFirst("김소희");
		System.out.println(link);
		System.out.println(link.get(0));
		System.out.println(link.get(1));
//		System.out.println(link.get(2));
		
		link.add("A");
		link.add("B");
		System.out.println(link);
		
		link.removeFirst();
		System.out.println(link);
		
		link.remove(2);
		System.out.println(link);
	}
}
