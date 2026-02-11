5
4
1 2 3 4
5
3 10 1 2 5
7
12 48 28 21 67 75 85
8
245 108 162 400 274 358 366 166
10
866 919 840 944 761 895 701 912 848 799

#1 20
#2 100
#3 16057
#4 561630
#5 6455522
  

package algorithm;

import java.util.*;
import java.io.*;

public class 풍선사격게임 {
	
	static LinkedList<Integer> balloonL;
	static int result = 0;
	static int N;
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			// 입력
			N = Integer.parseInt(br.readLine());			
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			balloonL = new LinkedList<>();
			for (int num : input) { balloonL.add(num); }

			// dfs 계산
			dfs(0);
			
			sb.append("#" + t + " " + result + "\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void dfs(int calculate) {
		
		int length = balloonL.size();
		
		if (length == 1) {
			// 획득할 수 있는 최대 점수
			result = Math.max(result, calculate + balloonL.get(0));
			return;
		}
		
		for (int i = 0; i < length; i++) {

		    // 다음 점수
			int gain = 0;
		    if (i == 0) gain = balloonL.get(1);
		    else if (i == length - 1) gain = balloonL.get(length - 2);
		    else gain = balloonL.get(i-1) * balloonL.get(i+1);

		    // 재귀
		    int removed = balloonL.remove(i);   // 삭제
		    dfs(calculate + gain);             
		    balloonL.add(i, removed);           // 복구
		}	
	}

}
