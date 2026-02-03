package algorithm;

import java.util.*;
import java.io.*;

public class 주몽_1940 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] list = new int[N];		
		int idx = 0;
		for (String s: br.readLine().split(" "))
			list[idx++] = Integer.parseInt(s);				
		Arrays.sort(list);
		
		int start = 0, end = N - 1, count = 0;
		while (start < end) {
			int sum = list[start] + list[end];

			if (sum == M) {
				count++;
				start++;
        	} else if (sum < M) {
        		start++;
        	} else {
        		end--;
            }
        }

		System.out.println(count);		
	}
}
