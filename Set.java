package com.ssafy.day07.b_set;

// TODO: SmartPhone이 다른 SmartPhone과 번호를 기준으로 비교가능하게 처리하시오.
 public class SmartPhone {

    // END

    String number;

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
}



package com.ssafy.day07.b_set;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    Set<Object> hset = new HashSet<Object>();

    private void addMethod() {
        hset.add(Integer.valueOf(1));
        hset.add("Hello");
        hset.add("Hello"); // 동일한 데이터 추가 확인
        hset.add(1); // 기본형은 wrapper를 통해 추가
        // TODO: SmartPhone 타입의 객체를 추가해보자.
        hset.add(new SmartPhone("010"));
        hset.add(new SmartPhone("010"));
        
        // equals 메소드 재정의
        
        // 입력 순서는 보장되지 않는다.
        
        // END
        System.out.println("데이터 추가 결과: " + hset);
    }

    private void retrieveMethod() {
        // TODO:hset에서 필요한 정보를 조회해보자.
    	Iterator<Object> iter = hset.iterator();
    	while(iter.hasNext()) {
    		System.out.println(iter.next());
    	}
    	
    	for (Object obj: hset) {
    		System.out.println();
    	}
        // END
    }
    
    // Set 은 업데이트가 안된다. 그 위치에 가서 값을 바꿔야하지만, 위치가 없기 때문에 수정 불가능, 넣은 순서대로 출력 안됨
    
    
    private void removeMethod() {
        // TODO:hset에서 hello를 삭제해보자.

        // END
        System.out.println("데이터 삭제 결과: " + hset);
    }

    public static void main(String[] args) {
        SetTest test = new SetTest();
        test.addMethod();
        test.retrieveMethod();
        test.removeMethod();
    }
}
