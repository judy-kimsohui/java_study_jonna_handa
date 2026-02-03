package algorithm;

import java.io.*;
import java.util.*;

public class 사칙연산유효성검사 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {			
//			계산이 가능한지가 아닌 유효성을 검사하는 문제이므로 0으로 나누는 경우는 고려하지 않는다.
//			트리는 완전 이진 트리 형식으로 주어진다
			
			int N = Integer.parseInt(br.readLine());
			
			int Result = 1; // true
			for (int n = 1; n <= N; n++) {
				
				String[] input = br.readLine().split(" ");
				String value = input[1];
				
				boolean isOperator = value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
				
//				노드당 하나의 연산자 또는 숫자만 저장할 수 있다.
				
				// 부호의 경우
				if (isOperator) {
				    // 오류1 : 연산자인데 자식이 2개가 아님
				    if (input.length != 4) {
				    	Result = 0;
				    }
				} else {
				    // 오류2 : 숫자인데 자식이 있음
				    if (input.length != 2) {
				    	Result = 0;
				    }
				}
				
			}
			System.out.println("#" + t + " " + Result);
		}
	}

}
