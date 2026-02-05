// #1 174
// #2 131
// #3 145
// #4 155
// #5 166
// #6 239
// #7 166
// #8 172
// #9 291
// #10 464


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

			// 꿀통 입력
			for (int r = 0; r < N; r++) {
				int i = 0;
				for (String s : br.readLine().split(" ")) {				
					HoneyList[r][i++] = Integer.parseInt(s);					
				}				
			}
			
			// 구간 내 최대 점수 찾기 (부분집합)
            for (int r = 0; r < N; r++) {
                for (int start = 0; start <= N - M; start++) {
                    int best = 0;
            
                    // M칸 값 뽑기
                    int[] arr = new int[M];
                    for (int i = 0; i < M; i++) arr[i] = HoneyList[r][start + i];
            
                    // 부분집합 전수조사
                    int limit = 1 << M;
                    for (int mask = 1; mask < limit; mask++) {
                        int sum = 0;
                        int score = 0;
                        for (int i = 0; i < M; i++) {
                            if ((mask & (1 << i)) != 0) {
                                sum += arr[i];
                                score += arr[i] * arr[i];
                            }
                        }
                        if (sum <= C) best = Math.max(best, score);
                    }
            
                    HoneyScore[r][start] = best;
                }
            }

			
            int Result = 0;	// 최종 합 (최대값)
            int find1R = 0, find1C = 0;
            int find2R = 0, find2C = 0;
			for (int r = 0; r < N; r++) {                               
                for (int c = 0; c <= N - M; c++) {

                    // 점수1
                    find1R = r; find1C = c;
					// System.out.print(HoneyScore[r][c] + " ");

                    // 점수2
                    for (int rr = r; rr < N; rr++) {
                        for (int cc = 0; cc <= N - M; cc++) {
                            find2R = rr; find2C = cc;
                            if (find1R == find2R && Math.abs(find1C-find2C) < M) continue;
                            Result = Math.max(Result, HoneyScore[find1R][find1C] + HoneyScore[find2R][find2C]);
                        }
                    }
                }
//				System.out.println("");
			}

			
			sb.append(Result + "\n");
		}

		System.out.println(sb);		
	}	
}
