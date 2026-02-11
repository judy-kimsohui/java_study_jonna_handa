package algorithm;

import java.util.*;
import java.io.*;

public class 장훈이의높은선반 {

	static int N, B;
    static int[] heightL;
    static int answer;
	
	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {

			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			B = Integer.parseInt(input[1]);
			
			heightL = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// 서점에 있는 높이가 B인 선반, 점원의 키를 합하여 
			// 만들 수 있는 높이가 B 이상인 탑 중에서 탑의 높이와 B의 차이가 가장 작은 것을 출력

			answer = Integer.MAX_VALUE;

            dfs(0, 0);

            sb.append("#" + t + " " + answer + "\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int idx, int sum) {

        // 이미 B 이상이면 답 갱신
        if (sum >= B) {
            answer = Math.min(answer, sum - B);
            return;
        }

        // 끝까지 다 본 경우 리턴
        if (idx == N) return;

        // 현재 차이가 이미 answer 이상이면 가지치기
        if (sum-B >= answer) return;
        
        dfs(idx+1, sum + heightL[idx]);	// (idx+1)번째 사람을 선택        
        dfs(idx+1, sum);	// (idx+1)번째 사람을 선택 안함
    }	
}
