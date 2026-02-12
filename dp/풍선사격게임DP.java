package algorithm;

import java.util.*;
import java.io.*;

public class 풍선사격게임DP {
	
	static int[][] DP;
	static int[] balloonL;
	static int result = 0;
	static int N;
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			// 입력
			N = Integer.parseInt(br.readLine());						
			balloonL = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();			
			DP = new int[N][N];			
			
			// DP 계산 - 왼쪽이나 오른쪽이 없으면 1로 계산 [3 1 5 8] -> [1 3 1 5 8 1]
			
			// k번째 풍선을 마지막에 터뜨릴 때 - 왼쪽 구간 + 오른쪽 구간 + 마지막 계산값
			for (int len = 1; len <= N; len++) {

			    for (int start = 0; start + len <= N; start++) {

							// 구간의 시작점, 끝점
			        int end = start + len - 1;
			        int max = 0;

			        for (int k = start; k <= end; k++) {

									// 왼쪽, 오른쪽에 풍선 유무 확인
			            int leftExist = (start - 1 >= 0) ? balloonL[start - 1] : 0;
			            int rightExist = (end + 1 < N) ? balloonL[end + 1] : 0;

			            int point;

			            if (leftExist != 0 && rightExist != 0) // 가운데
			            	point = leftExist * rightExist;
			            else if (leftExist != 0)  // 가장자리 (왼쪽)
			            	point = leftExist;
			            else if (rightExist != 0) // 가장자리 (오른쪽)
			            	point = rightExist;
			            else
			            	point = balloonL[k]; // 길이 1

									// k를 기준으로 왼쪽 구간, 오른쪽 구간의 최대값
			            int leftRes = (k - 1 >= start) ? DP[start][k - 1] : 0;
			            int rightRes = (k + 1 <= end) ? DP[k + 1][end] : 0;

			            max = Math.max(max, leftRes + point + rightRes);
			        }

							// 구간의 최대값 저장
			        DP[start][end] = max;
			    }
			}

			sb.append("#" + t + " " + DP[0][N-1] + "\n");
		}
		
		System.out.println(sb);
		
	}
	
}
