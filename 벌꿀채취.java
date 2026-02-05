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


// 치명적인 실수는 “구간(M칸) 안에서 고르는 꿀통 조합”을 슬라이딩 윈도우(연속)로 처리한 것임.
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

실행 시간 : 86 ms
메모리 : 25,984 kb

풀이전략 : 구간 내 최대 점수 찾기 - 부분집합 (비트마스크)
1. 각 행에서 연속된 M칸을 선택한 뒤, 부분집합 전수조사로 꿀 양 합 ≤ C 를 만족하는 제곱합 최대 점수를 미리 계산함
2. 각 위치의 점수를 HoneyScore[r][c]에 저장함
3. 두 일꾼의 구간이 겹치지 않도록 조합하며 HoneyScore의 합의 최댓값을 구함

(부분집합 풀이)
한 사람이 고를 수 있는 꿀통 수 = M (≤ 5), M칸 중 일부만 골라도 됨 (부분집합)
가능한 경우의 수 = 2^M − 1 (최대 31개) → 전부 직접 확인 가능 → 비트마스크가 최적
---
정수 mask 하나로 선택/미선택 상태 표현 
(비트가 1 → 해당 인덱스 꿀통 선택 / 비트가 0 → 선택 안 함)
(예시) mask = 0101 (2진수) → 0번, 2번 꿀통 선택


import java.io.*;
import java.util.*;

public class Main {

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
      
              // M칸으로 구성된 서브 리스트 - 여기서 부분 집합 추출 예정
              int[] subList = new int[M];
              for (int i = 0; i < M; i++) 
	              subList[i] = HoneyList[r][start + i];
      
              // 부분집합 전수조사
              int limit = 1 << M; // 2^M
              for (int mask = 1; mask < limit; mask++) {
                  int sum = 0;
                  int score = 0;
                  
                  // M개 인덱스 중 1인 부분만 추출하여 꿀통 합과 점수 계산
                  for (int i = 0; i < M; i++) {
		                  // 1 << i : i번째 비트만 1
		                  // & 결과 ≠ 0 : i번째 꿀통이 선택된 경우 (꿀통합, 점수 계산)
                      if ((mask & (1 << i)) != 0) {
                          sum += subList[i];
                          score += (int) Math.pow(subList[i], 2); // 거듭제곱
                      }
                  }
                  
                  // 꿀통 합이 C 이하인 경우 최대값 갱신 반영한다.
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
	
	            // 점수2
	            for (int rr = r; rr < N; rr++) {
	                for (int cc = 0; cc <= N - M; cc++) {
	                    find2R = rr; find2C = cc;
	                    if (find1R == find2R && Math.abs(find1C-find2C) < M) continue;
	                    Result = Math.max(Result, HoneyScore[find1R][find1C] + HoneyScore[find2R][find2C]);
	                }
	            }
	        }
			}			
			sb.append(Result + "\n");
		}

		System.out.println(sb);		
	}	
}

