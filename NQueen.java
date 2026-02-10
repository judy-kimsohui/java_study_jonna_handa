package algorithm;

import java.util.*;
import java.io.*;

public class NQueen {
	
	static int N, count;
	static boolean[] col;
	static boolean[] diag1; // row + col
	static boolean[] diag2; // row - col + N - 1

	static void dfs(int row) {
	    if (row == N) {
	        count++;
	        return;
	    }

	    for (int c = 0; c < N; c++) {
	        if (col[c] || diag1[row + c] || diag2[row - c + N - 1])
	            continue; // 가지치기

	        col[c] = diag1[row + c] = diag2[row - c + N - 1] = true;
	        dfs(row + 1);
	        col[c] = diag1[row + c] = diag2[row - c + N - 1] = false; // 복구
	    }
	}
	
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    N = Integer.parseInt(br.readLine());

	    col = new boolean[N];
	    diag1 = new boolean[2 * N];        // row + col: 0 ~ 2N-2
	    diag2 = new boolean[2 * N];        // row - col + N - 1: 0 ~ 2N-2

	    count = 0;

	    dfs(0); // 0행부터 시작

	    System.out.println(count);
	}


}
