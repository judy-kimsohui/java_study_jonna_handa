package algorithm;

import java.io.*;
import java.util.*;

public class 벌꿀채취 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			
			sb.append("#" + t + " ");
			
			String[] input = br.readLine().split(" ");
			int N = Integer.parseInt(input[0]);
			int M = Integer.parseInt(input[1]);
			int C = Integer.parseInt(input[2]);
			
			int[][] HoneyList = new int[N][N];
			int[][] HoneyScore = new int[N][N];			
			
			int Best = 0;	// 1등
			int Great = 0;	// 2등

			// 꿀통 입력
			for (int r = 0; r < N; r++) {
				int i = 0;
				for (String s : br.readLine().split(" ")) {				
					HoneyList[r][i++] = Integer.parseInt(s);					
				}				
			}
			
			// 구간 내 최대 점수 찾기 (슬라이딩 윈도우 구간합)
			for (int r = 0; r < N; r++) {				
				for (int start = 0; start <= N - M; start++) {
					int[] checkList = Arrays.copyOfRange(HoneyList[r], start, start + M);

					Arrays.sort(checkList);
					int s = 0, e = 0, sum = checkList[s], score = (int)Math.pow(checkList[s], 2);
//					System.out.println(Arrays.toString(checkList));
					while (s <= M-1) {
						if (sum <= C) {
							if(e < M-1) e++;
//							System.out.println(sum);
							HoneyScore[r][start] = Math.max(HoneyScore[r][start], score);
//							for (int k = 0; k < N; k++) {
//								for (int c = 0; c <= N - M; c++) {
////									System.out.print(HoneyScore[k][c] + " ");
//								}
//							System.out.println();	
//							}
//							
							if (e <= M-1) {
								sum += checkList[e];
								score += (int)Math.pow(checkList[e], 2);
							}
						}
						else if (sum > C) {
							if (s <= M-1) {
								sum -= checkList[s];
								score -= (int)Math.pow(checkList[s], 2);								
							}								
							s++;
						}
					}						
				}
			}
			
			// Best, Great 찾기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c <= N - M; c++) {
					int Score = HoneyScore[r][c];
//					System.out.print(HoneyScore[r][c] + " ");
					if (Best < Score) Best = Score;
					else {
						if (Great < Score) Great = Score;
					}
				}
//				System.out.println("");
			}
			
			sb.append(Best + Great + "\n");
		}

		System.out.println(sb);		
	}	
}
