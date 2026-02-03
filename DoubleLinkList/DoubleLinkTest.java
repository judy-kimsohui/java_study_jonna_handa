package com.jurib.live05;

public class DoubleLinkTest {

	public static void main(String[] args) {
		
		DoubleLinkList<String> list = new DoubleLinkList<>();
		list.addFirst("김소희1");
		list.addFirst("김소희2");
		list.addFirst("김소희3");
		list.addFirst("김소희4");
		list.addFirst("김소희5");
		System.out.println(list);
	}
}
