import java.io.*;
import java.util.*;

class Node {
    int r, c;
    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Main {

    

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int bfs(int sr, int sc) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(sr, sc));
        visited[sr][sc] = true;

        int area = 1; // 시작칸 포함

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 0) continue; // 그림이 아닌 칸

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc));
                area++;
            }
        }
        return area;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < M; k++) {
                map[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int maxArea = 0;

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                if (!visited[i][k] && map[i][k] == 1) {
                    count += 1;
                    int area = bfs(i, k);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.println(count);
        System.out.println(maxArea);
    }
}
