package algorithm;

import java.util.*;
import java.io.*;

public class Plus_3260 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String A = st.nextToken();
			String B = st.nextToken();
			
			int AL = A.length();
			int[] AList = new int[101];
			
			int BL = B.length();
			int[] BList = new int[101];
			
			for (int i = 100; i > 100-AL; i--) 
				AList[i] = A.charAt(i-100+AL-1) - '0';
			for (int i = 100; i > 100-BL; i--) 
				BList[i] = B.charAt(i-100+BL-1) - '0';
			
			int[] Result = new int[101];
			for (int i = 100; i > 0; i--) {
				int temp = Result[i] + AList[i] + BList[i];
				if (temp >= 10) {
					Result[i] = temp - 10;
					Result[i-1] = 1;
				}
				else Result[i] = temp;
			}
			
			System.out.print("#" + t + " ");			
			StringBuilder sb = new StringBuilder();
			boolean started = false;
			for (int v : Result) {
			    if (!started) {
			        if (v == 0) continue; // 앞쪽 0 스킵
			        started = true;
			    }
			    sb.append(v);
			}
			System.out.println(sb.toString());		
		}				
	}
}
