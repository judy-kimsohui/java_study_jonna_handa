package algorithm;

import java.util.*;
import java.io.*;

public class 배열돌리기1_16926 {

	public static void main(String[] args) throws Exception {
		
//		N, M, R
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()); // 회전수 
				
		if (M < N) { // 작은 수를 기준으로 Row에 넣음
			int temp = N;
			N = M;
			M = temp;
		}
		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			int c = 0;
			for (String num : br.readLine().split(" ")) {
				map[r][c++] = Integer.parseInt(num);
			}
		}
		
		int[][] newMap = new int[N][M];
		for (int i = 0; i < M/2; i++) {
			
		}
	
	}
	
}
