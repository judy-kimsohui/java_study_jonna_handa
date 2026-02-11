SWEA 1767

package algorithm;

import java.io.*;
import java.util.*;

public class 프로세서연결하기 {

    static int N;
    static int[][] map;
    static List<int[]> cores;
    static int maxCore, minLen;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구하고자 한다.
    // 단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.

    
    // 지금 처리 중인 코어 번호, 연결한 코어 수, 전선 길이 합
    static void dfs(int coresIndex, int connected, int length) {
    
    	// 최대한 많은 Core에 전원을 연결
    	if (coresIndex == cores.size()) {
            if (connected > maxCore) {
                maxCore = connected;
                minLen = length;
            } else if (connected == maxCore) {
                minLen = Math.min(minLen, length);
            }
            return;
        }

        // 남은 코어 다 연결해도 max 못 넘으면 가지치기
        if (connected + (cores.size() - coresIndex) < maxCore) return;

        int r = cores.get(coresIndex)[0];
        int c = cores.get(coresIndex)[1];

        // (1. 연결하는 경우) 4방향 연결 시도
        for (int d = 0; d < 4; d++) {
            int len = canConnect(r, c, d);
            if (len == 0) continue; // 연결 불가
            
            setLine(r, c, d, 2);	// 2로 전선 깔기
            dfs(coresIndex + 1, connected + 1, length + len);            
            setLine(r, c, d, 0);	// 복구
        }

        // (2. 연결 안하는 경우)
        dfs(coresIndex + 1, connected, length);
    }

    // 해당 방향으로 끝까지 갈 수 있으면 전선 길이 반환, 아니면 0
    static int canConnect(int r, int c, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        int len = 0;

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (map[nr][nc] != 0) return 0;
            nr += dr[d];
            nc += dc[d];
            len++;
        }
        return len;
    }

    // 전선 설치 or 복구
    static void setLine(int r, int c, int d, int val) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            map[nr][nc] = val;
            nr += dr[d];
            nc += dc[d];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            // 입력
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {

                    	// 가장자리 코어 제외
                        if (i != 0 && i != N - 1 && j != 0 && j != N - 1) { cores.add(new int[]{i, j}); }
                    }
                }
            }

            maxCore = 0;
            minLen = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append("#").append(t).append(" ").append(minLen).append("\n");
        }

        System.out.print(sb);
    }
}
