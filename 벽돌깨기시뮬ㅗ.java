import java.util.*;
import java.io.*;

class Main {

    static int N, W, H;
    static int maxCount;
    static int[][] map;
    static int brick;

    // 남은 벽돌의 개수를 구하라!
    static void dfs(int sum, int count) {

        if (count == N) {
            maxCount = Math.max(maxCount, sum);
            return;
        }

        for (int nw = 0; nw < W; nw++) {

            // 벽돌깨기
            int[][] backup = new int[H][W];
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    backup[r][c] = map[r][c];
                }
            }
            int breaked = breakBrick(nw); 
            // System.out.println(breaked);
            dfs(sum + breaked, count+1);
            // 복구
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    map[r][c] = backup[r][c];
                }
            }
            
        }
    }
    
    static int breakBrick(int w) {

        int startC = w;
        int startR = -1;        
        for (int r = 0; r < H; r++) {
            if (map[r][w] != 0) {
                startR = r;
                break;
            }
        }
        if (startR == -1) return 0;
        
        boolean[][] visitedL = new boolean[H][W];
        ArrayDeque<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[] {startR, startC});
        int count = 0;
        visitedL[startR][startC] = true;
        
        while (!Q.isEmpty()) {
            int[] target = Q.poll();
            int targetR = target[0];
            int targetC = target[1];
            int number = map[targetR][targetC];  
            map[targetR][targetC] = 0;
            count += 1;
            
            
            // 1일 경우, 전파 막힘
            if (number == 1) continue;

            // 1보다 클 경우, 벽돌 깨기 전파 진행            
            for (int[] dir : new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                for (int i = 1; i < number; i++) {
                    int dr = dir[0];
                    int dc = dir[1];
                    int nr = targetR + dr*i;
                    int nc = targetC + dc*i;
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W || visitedL[nr][nc]) continue;

                    // 벽돌 깨기, 전파
                    if (map[nr][nc] >= 2 && !visitedL[nr][nc]) {
                        visitedL[nr][nc] = true;
                        Q.offer(new int[] {nr, nc});     
                    }
                    else if (map[nr][nc] == 1) {
                        map[nr][nc] = 0;
                        count += 1;
                    }
                }
            }            
        }  

        // 중력 적용
        int[] height = new int[W];
        for (int c = 0; c < W; c++) {
            height[c] = H-1;
        }
        int[][] changedmap = new int[H][W];
        for (int c = 0; c < W; c++) {
            for (int r = H-1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    changedmap[height[c]--][c] = map[r][c];
                }
            }
        }
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                map[r][c] = changedmap[r][c];
            }
        }

        return count;
    }
    
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            // 입력
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            brick = 0;
            maxCount = 0;
            map = new int[H][W];

            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] != 0) brick += 1;
                }
            }

            // 벽돌 깨기
            dfs(0, 0);
            sb.append("#" + t + " " + (brick-maxCount) + "\n");
        }        
        
       System.out.println(sb);
            
    }
}
