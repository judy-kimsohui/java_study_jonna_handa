package algorithm;

import java.util.*;
import java.io.*;

public class 수제버거장인 {

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
				
		for (int t = 1; t <= T; t++) {
			
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = input[0], M = input[1];
			int Result = (1 << N);
			

			HashSet<Integer> exceptList = new HashSet<>();
			for (int m = 0; m < M; m++) {
				input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				exceptList.add( (1 << (input[0] - 1)) + (1 << (input[1] - 1)) );
			}

			for (int Case = 0; Case < (1 << N); Case++) {				
				for (int except : exceptList) {
					if ((Case & except) == except) {
						Result--;
						break;
					}
				}				
			}
			
			sb.append("#" + t + " " + Result + "\n");
		}
		
		System.out.println(sb);
	}
	
}
