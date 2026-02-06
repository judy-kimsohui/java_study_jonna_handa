package algorithm;

import java.util.*;
import java.io.*;

public class 상호의배틀필드 {

	public static void main(String[] args) throws Exception {
		
		// . 평지(전차가 들어갈 수 있다.)
		// - 물(전차는 들어갈 수 없다.)
		// * 벽돌로 만들어진 벽
		// # 강철로 만들어진 벽
		
		// S 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
			// 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
			// 포탄이 벽에 부딪히면 포탄은 소멸한다
				// 강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
				// 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
		
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			String[] input = br.readLine().split(" ");
			int H = Integer.parseInt(input[0]); // 높이
			int W = Integer.parseInt(input[1]); // 너비
			
			int dir = 0;
			String[] TankList = new String[] {"^", "v", "<", ">"};
			int[] dr = {-1, 1, 0, 0}; // "^", "v", "<", ">"
			int[] dc = {0, 0, -1, 1};
			
			int startR = 0, startC = 0;
			
			// 맵 정보 입력
			String[][] Map = new String[H][W];
			for (int h = 0; h < H; h++) {
				Map[h] = br.readLine().split("");
				for (int w = 0; w < W; w++) {
					if (Map[h][w].equals("^")) { dir = 0; startR = h; startC = w; }
					else if (Map[h][w].equals("v")) { dir = 1; startR = h; startC = w; }
					else if (Map[h][w].equals("<")) { dir = 2; startR = h; startC = w; }
					else if (Map[h][w].equals(">")) { dir = 3; startR = h; startC = w; }
				}
			}
						
			int N = Integer.parseInt(br.readLine());
			String[] UserCode = br.readLine().split("");
			
//		System.out.println(Arrays.toString(UserCode));
			
			int r = startR, c = startC, nr = r, nc = c, pr = r, pc = c;
			for (String u : UserCode) {

				// 포탄 발사
				if (u.equals("S")) {
					pr = r; pc = c;
					for (int k = 0; k < 20; k++) {
						nr = pr + dr[dir];
						nc = pc + dc[dir];
						
						if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;					
						if (Map[nr][nc].equals(".") || Map[nr][nc].equals("-")) { pr = nr; pc = nc; continue; } // 평지
						if (Map[nr][nc].equals("*")) { Map[nr][nc] = "."; pr = nr; pc = nc; break; } // 벽돌벽
						if (Map[nr][nc].equals("#")) { break; } // 강철벽
					}
				}
				else {
					if (u.equals("U")) { dir = 0; Map[r][c] = TankList[dir]; }
					else if (u.equals("D")) { dir = 1; Map[r][c] = TankList[dir]; }
					else if (u.equals("L")) { dir = 2; Map[r][c] = TankList[dir]; }
					else if (u.equals("R")) {  dir = 3; Map[r][c] = TankList[dir];}
					
					nr = r + dr[dir];
					nc = c + dc[dir];
					if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
					if (Map[nr][nc].equals(".")) { Map[nr][nc] = TankList[dir]; Map[r][c] = "."; r = nr; c = nc; continue; } // 평지, 전차 갱신
					
				}
			}
		
			sb.append("#" + t + " ");
			for (int rr = 0; rr < H; rr++) {
				for (int cc = 0; cc < W; cc++) {
					sb.append(Map[rr][cc]);
				}
				sb.append("\n");
			}
			
//			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
