import java.util.*;
import java.io.*;

public class 미로2 {

	public static void main(String[] args) throws Exception {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			
			br.readLine();
			char[][] map = new char[100][100];
			
			int startR = 0, startC = 0;
			for (int row = 0; row < 100; row++) {
				
				String line = br.readLine();
				for (int col = 0; col < 100; col++) {
					map[row][col] = line.charAt(col);
					if (map[row][col] == '2') {
						startR = row;
						startC = col;
					}
				}				
			}
			
			ArrayDeque<int[]> Q = new ArrayDeque<>();
			Q.offer(new int[] {startR, startC});
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			boolean[][] visitedL = new boolean[100][100];
			
			sb.append("#" + t + " ");
			boolean Flag = false;
			while (!Q.isEmpty()) {
				
				int[] node = Q.poll();
				if (map[node[0]][node[1]] == '3') {
					Flag = true;
					break;
				}

				int nr = 0, nc = 0;
				for (int i = 0; i < 4; i++) {
					nr = node[0] + dr[i];
					nc = node[1] + dc[i];

					if (nr < 0 || nr >= 100 || nc < 0 || nc >= 100) continue;

					if (map[nr][nc] != '1' && !visitedL[nr][nc]) {
						visitedL[nr][nc] = true;
						Q.offer(new int[] {nr, nc});
					}
				}
				
				
			}
			
			if (Flag) sb.append(1 + "\n");
			else sb.append(0 + "\n");
			
		}
		System.out.println(sb);
	}
	
}
