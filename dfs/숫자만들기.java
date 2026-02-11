package algorithm;

import java.util.*;
import java.io.*;

public class 숫자만들기 {
	
	static int N;
	static int[] opList;
	static int[] numList;
    static int max, min;
    
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			opList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			numList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			max = Integer.MIN_VALUE;
	        min = Integer.MAX_VALUE;

	        dfs(1, numList[0]);
	        
	        sb.append("#" + t + " " + Math.abs(max-min) + "\n");
		}
		
		System.out.println(sb);
	}
	
	static int calc(int a, int b, int op) {
	    switch (op) {
	        case 0: return a + b;
	        case 1: return a - b;
	        case 2: return a * b;
	        case 3: return a / b; // 문제 조건: 0 아님
	    }
	    return 0;
	}
	
	static void dfs(int index, int calculate) {
		
		if (index == N) {
			max = Math.max(max, calculate);
	        min = Math.min(min, calculate);
	        return;
		}
		
		for (int i = 0; i < 4; i++) {
	        if (opList[i] == 0) continue;

	        opList[i]--; // 선택
	        dfs(index + 1, calc(calculate, numList[index], i));
	        opList[i]++; // 복구
	    }
	}	
}
