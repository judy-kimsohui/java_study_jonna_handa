package algorithm;

import java.util.*;
import java.io.*;

public class 한빈이와SpotMart {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] list = new int[N];
			int i = 0;
			for (String s: br.readLine().split(" ")) {
				list[i++] = Integer.parseInt(s);
			}
			
			Arrays.sort(list);
			
			int start = 0, end = N-1;
			int MaxSum = -1;
			
			while (start < end) {
				int sum = list[start] + list[end];

				if (sum > M) {
					end--;
				} else if (sum <= M) {
					MaxSum = Math.max(MaxSum, sum);
					start++;
				}				
			}
			
			System.out.println("#" + t + " " + MaxSum);			
		}
		
	}
}
