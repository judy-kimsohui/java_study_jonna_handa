package com.ssafy.day07.c_map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapTest {
    Map<String, String> hMap = new HashMap<>();

    private void addMethod() {
        System.out.println("추가 성공?: " + hMap.put("andy", "1234"));
        // 동일한 키의 사용 결과는?
        System.out.println("추가 성공?: " + hMap.put("andy", "4567"));
        hMap.put("kate", "9999");
        // 기존에 해당 키에 대한 값이 없을 때만 추가하기
        hMap.putIfAbsent("kate", "1234");

        hMap.put("henry", "4567"); // 동일한 값

        hMap.put("hong", "1234");
        System.out.println("추가 결과: " + hMap);
    }

    private void retrieveMethod() {
        // TODO: kate의 전화번호가 있나요?
    	System.out.println(hMap.get("kate"));
    		
        // END

        // TODO: map이 가지고 있는 key와 거기에 연결된 value를 출력하시오.
    	Set<String> keys = hMap.keySet();
    	for(String k: keys) {
    		System.out.println(k+" : "+nMap.get(k));
    	}
    	
    	Set<Entry<String, String>> entries = hMap.entrySet();
    	for(Entry<String, String> e: entries) {
    		System.out.println(e.getKey() + " : " + e.getValue());
    		
    		if (e.getValue().equals("4567")) {
    			sout("같은애들 ~~");
    		}
    	}
    	
    	
        // END

        // TODO: 값이 4567인 사람의 이름은?

        // END
    }

    private void removeMethod() {
        // TODO: andy의 자료를 삭제하고 출력하시오.

        // END
    }

    public static void main(String[] args) {
        MapTest hmt = new MapTest();
        hmt.addMethod();
        hmt.retrieveMethod();
        hmt.removeMethod();
    }
}
