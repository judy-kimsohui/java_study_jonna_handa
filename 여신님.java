package algorithm;

import java.util.*;
import java.io.*;

public class 여신님 {

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t < T+1; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // 입력
            int result = Integer.MAX_VALUE;
            int[][] Map = new int[N][M];
            int[][] DevilTime = new int[N][M];
            ArrayDeque<int[]> DevilQ = new ArrayDeque<>();
            int startR = 0, startC = 0;
            int endR = 0, endC = 0;
            for (int r = 0; r < N; r++) {
                String input = br.readLine();
                int c = 0;
                for (int i = 0; i < M; i++) {
                    char ch = input.charAt(i);
                    Map[r][c++] = ch;
                    DevilTime[r][c-1] = N*M+1;
                    if (ch == ('*')) {
                        DevilQ.offer(new int[]{r, c-1, 0});
                        DevilTime[r][c-1] = 0;
                    }
                    else if (ch == ('S')) {
                        startR = r;
                        startC = c-1;
                    }
                    else if (ch == ('D')) {
                        endR = r;
                        endC = c-1;
                    }
                }
            }

            // 악마 확산
            while (!DevilQ.isEmpty()) {
                int[] node = DevilQ.poll();
                int r = node[0];
                int c = node[1];
                int time = node[2];
                for (int[] dir : new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || Map[nr][nc] == ('D') || Map[nr][nc] == ('X') || DevilTime[nr][nc] != N*M+1) continue;
                    DevilTime[nr][nc] = time+1;  
                    DevilQ.offer(new int[]{nr, nc, time+1});
                }

            // 수연이 이동
            boolean[][] visited = new boolean[N][M];
            visited[startR][startC] = true;
            
            ArrayDeque<int[]> Q = new ArrayDeque<>();
            Q.offer(new int[]{startR, startC, 0});
            while (!Q.isEmpty()) {
                int[] node = Q.poll();
                int r = node[0];
                int c = node[1];
                int time = node[2];
                if (r == endR && c == endC) {
                    result = Math.min(result, time);
                }
                for (int[] dir : new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || DevilTime[nr][nc] <= time+1 || Map[nr][nc] == ('X') || visited[nr][nc]) continue;
                    Q.offer(new int[]{nr, nc, time+1});
                    visited[nr][nc] = true;
                }
            }

            if (result != Integer.MAX_VALUE) {
                sb.append("#" + t + " " + result + "\n");
            }
            else sb.append("#" + t + " GAME OVER" + "\n"); 
        }

        System.out.println(sb);
    }
}
