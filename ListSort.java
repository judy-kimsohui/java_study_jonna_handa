package com.ssafy.day07.d_etc.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ssafy.day07.b_set.SmartPhone;

public class ListSortTest {

    private List<String> names = Arrays.asList("Hi", "Java", "World", "Welcome");

    public void basicSort() {
        // TODO: names를 이름의 오름차순, 또는 그 역순으로 정렬해서출력하시오.
    	Collections.sort(names);
    	System.out.println(names);

    	// 내림차순
    	Collections.reverse(names);
        // END
    }

    public void sortPhone() {
        // TODO: 전화 번호에 따라 SmartPhone을 정렬해보자.
    	SmartPhone s1 = new SmartPhone("1");
    	SmartPhone s2 = new SmartPhone("2");
    	SmartPhone s3 = new SmartPhone("3");
    	List<SmartPhone> ps = Arras.asList(s1, s2, s3);
    	Collections.sort(ps);
    	
    	
        // END
    }

    public void stringLengthSort() {
        // TODO: 문자열의 길이에 따라 names를 정렬해보자.

        // END
        System.out.println(names); // [Hi, Java, World, Welcome]
    }

    public static void main(String[] args) {
        ListSortTest st = new ListSortTest();
        st.basicSort();
        // st.sortPhone();
        // st.stringLengthSort();
    }

}


package com.ssafy.day07.b_set;

// TODO: SmartPhone이 다른 SmartPhone과 번호를 기준으로 비교가능하게 처리하시오.
 public class SmartPhone implements Compareable<SmartPhone>{

    // END

    String number;
    int price;

    public SmartPhone(String number) {
        this.number = number;
    }

    public String toString() {
        return "전화 번호: " + number;
    }

    // TODO: 동일한 번호의 SmartPhone이면 하나만 추가될 수 있도록 처리하시오.

    // END
    // Set이 두 객체를 같다는 판단 기준에는 하나가 더 있다 (hash code and Equals)
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof SmartPhone p) {
    		return this.number.equals(p.number);
    	}
    	return false;
    }

    @Override
    public int hashCode() {
    	return number.hash(number);
    }
    
    @Override
    public int compareTo(SmartPhone o) {
    	Integer num1 = Integer.parseInt(this.number);
    	Integer num2 = Integer.parseInt(o.number);
    	return num1.compareTo(num2);
    	
    	// return num1 - num2;
    	// num1 = MAX_VALUE, num2 = -1 : overflow
    }
}
