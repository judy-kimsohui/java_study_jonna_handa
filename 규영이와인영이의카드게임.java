package algorithm;

import java.util.*;
import java.io.*;

public class 규영이와인영이의카드게임 {
	
	static int[] input, card;
    static boolean[] visited;
    static int win, lose;

	public static void main(String[] args) throws Exception {
		
		// 한장씩 내서, 9 라운드를 끝내고 총점이 높은 사람이 게임의 승자가 된다.
		// 높은 점수 카드 = 두 사람 카드 숫자 합. 낮은 점수 카드 = 0점
		// 규영이가 이기는 경우와 지는 경우가 몇가지인지 구하는 프로그램을 작성하자
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//			Arrays.sort(input);
			
			boolean[] used = new boolean[19];
			for (int x : input) used[x] = true;

			card = new int[9];
			int idx = 0;
			for (int i = 1; i <= 18; i++)
			    if (!used[i]) card[idx++] = i;			
			
			win = 0;
            lose = 0;
            visited = new boolean[9];

            dfs(0, 0, 0);

            System.out.println("#" + t + " " + win + " " + lose);
		}
		
	}	

	static void dfs(int depth, int gScore, int iScore) {
	    if (depth == 9) {
	        if (gScore > iScore) win++;
	        else if (gScore < iScore) lose++;
	        return;
	    }

	    for (int i = 0; i < 9; i++) {
	        if (!visited[i]) {
	            visited[i] = true;

	            int sum = input[depth] + card[i];
	            if (input[depth] > card[i]) {
	                dfs(depth + 1, gScore + sum, iScore);
	            } else {
	                dfs(depth + 1, gScore, iScore + sum);
	            }

	            visited[i] = false;
	        }
	    }
	}

}
