package algorithm;

import java.util.*;
import java.io.*;

public class 수영장 {

	static int[] priceL;
	static int[] planL;
	static int result;
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			// day, month, 3month, year
			priceL = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			planL = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	
			
			result = priceL[3]; // 1년권
					
			dfs(0, 0); // month, cost
			
			sb.append("#" + t + " " + result + "\n");
		}		
		
		System.out.println(sb);
	}	
	
	static void dfs(int month, int cost) { 

		if (cost >= result) return;
		
		if (month >= 12) {
			result = Math.min(result, cost);
			return;			
		}		
		
		if (planL[month] == 0) {
		    dfs(month + 1, cost);
		    return;
		}
		
		// 3개월 추가
		dfs(month+3, cost + priceL[2]);
		
		// 1개월 추가
		dfs(month+1, cost + priceL[1]);
		
		// 1일권으로 1개월 추가
		dfs(month+1, cost + priceL[0]*planL[month]);		
	}	
}
