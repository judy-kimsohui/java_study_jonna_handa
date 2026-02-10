package algorithm;

import java.util.*;
import java.io.*;


public class 숫자만들기 {
	
	static int N;
    static int[] nums;
    static int[] op;   // + - * /
    static int max, min;


	static void dfs(int idx, int current) {
	    if (idx == N) {
	        max = Math.max(max, current);
	        min = Math.min(min, current);
	        return;
	    }

	    for (int i = 0; i < 4; i++) {
	        if (op[i] == 0) continue;

	        op[i]--; // 선택
	        dfs(idx + 1, calc(current, nums[idx], i));
	        op[i]++; // 복구
	    }
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
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        dfs(1, nums[0]);
        
        System.out.println(max);
        System.out.println(min);
		
	}
}
