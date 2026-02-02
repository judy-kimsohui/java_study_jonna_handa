// 파리퇴치


package algorithm;

import java.io.*;
import java.util.*;

public class Prefix_2001 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken()); 
        	
        	int[][] map = new int[N][N];
        	for (int row = 0; row < N; row++) {
        		int i = 0;
        		for (String s : br.readLine().split(" "))
        			map[row][i++] = Integer.parseInt(s);		
        	}
        	
//        	for (int[] row : map) System.out.println(Arrays.toString(row));
        	
        	// 누적합
        	int[][] sum = new int[N][N];
        	for (int r = 0; r < N; r++) {
        		for (int c = 0; c < N; c++) {     
        			sum[r][c] = map[r][c];
        	        if (r > 0) sum[r][c] += sum[r-1][c];
        	        if (c > 0) sum[r][c] += sum[r][c-1];
        	        if (r > 0 && c > 0) sum[r][c] -= sum[r-1][c-1];
        		}        		
        	}
        	
        	int Max_num = 0;
        	for (int r = M-1; r < N; r++) {
        	    for (int c = M-1; c < N; c++) {
        	        int temp = sum[r][c];
        	        if (r - M >= 0) temp -= sum[r-M][c];
        	        if (c - M >= 0) temp -= sum[r][c-M];
        	        if (r - M >= 0 && c - M >= 0) temp += sum[r-M][c-M];
        	        Max_num = Math.max(Max_num, temp);
        	    }
        	}
        	        	
        	System.out.println("#" + t + " " + Max_num);
        }        
    }
}
