package algorithm;

import java.io.*;
import java.util.*;

public class 햄버거다이어트 {
	public static void main(String[] args) throws Exception {
		
		// 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해주는 프로그램
		
		// 배낭 문제와 동일함!
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = input[0], Cal = input[1];
			
			int[] Calorie = new int[N];
			int[] Taste = new int[N];
			
			int[] dp = new int[Cal +1];
			for (int n = 0; n < N; n++) {
				input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int taste = input[0];
				int cal = input[1];
				
				for (int c = Cal; c >= cal; c--) {
					dp[c] = Math.max(dp[c], dp[c-cal] + taste);
				}	
			}
						
			sb.append("#" + t + " ");
			sb.append(dp[Cal] + "\n");		
		}
		
		System.out.println(sb);

	}

}
