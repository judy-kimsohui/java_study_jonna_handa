package algorithm;

import java.util.*;
import java.io.*;

public class Maze1_1226 {

	static class Node {
		int row;
		int col;
		
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		for (int t = 1; t <= 10; t++) {
			br.readLine();
			int N = 16;
			
			int[][] map = new int[16][16];
			int sr = 0, sc = 0;
			int er = 0, ec = 0;

			for (int r = 0; r < N; r++) { 				
				String line = br.readLine();
				for (int i = 0; i < N; i++) {
					map[r][i] = line.charAt(i) - '0';
					if (map[r][i] == 2) { sr = r; sc = i; }
					if (map[r][i] == 3) { er = r; ec = i; }
				}
			}			

			int[] dr = {-1, 1, 0, 0}; 
			int[] dc = {0, 0, -1, 1};
			
			boolean Result = false;
			
			ArrayDeque<Node> Q = new ArrayDeque<>();
			boolean[][] visitedL = new boolean[16][16];
			
			Q.offer(new Node(sr, sc));
			while (!Q.isEmpty()) {
				Node node = Q.poll();
				if (node.row == er && node.col == ec) { Result = true; break; } 
				for (int i = 0; i < 4; i++) {
					int nr = node.row + dr[i];
					int nc = node.col + dc[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					if (visitedL[nr][nc] == true || map[nr][nc] == 1) continue;
					
					Q.offer(new Node(nr, nc));		
					visitedL[nr][nc] = true;
				}				
			}
			
			System.out.print("#" + t + " ");
			if (Result) System.out.println(1);
			else System.out.println(0);
		}
	}	
}
