// 수영대회 결승전 ( 완전 탐색 + 구현 ) D4
//
// 가로 N 세로 N만큼 공간
// 섬과 같은 지나갈 수 없는 장애물(1)과, 주기적으로 사라졌다 나타나는 소용돌이(2)
//
// 소용돌이는 생성되고 2초동안 유지되다가 1초동안 잠잠해진다.
//     예를들어, 0초에 생성된 소용돌이는 0초, 1초까지 유지되고 2초에 사라지게된다.
//     또한 3초, 4초에는 생성되고 5초에 사라진다.
//     한번 통과한 소용돌이 위에서는 머물러 있을 수 있다.
//
// 바다에서 삼성이를 우승시키려면 어떤 경로로 보내야 될까?

import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int r, c, t;
        Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < T; tc++) {
            out.append("#").append(tc + 1).append(" ");

            // N : 칸 수, M : 스프레이 분사 세기
            int N = Integer.parseInt(br.readLine().trim());
            int[][] List = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    List[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st1.nextToken()); // 시작점
            int sc = Integer.parseInt(st1.nextToken());

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int er = Integer.parseInt(st2.nextToken()); // 도착점
            int ec = Integer.parseInt(st2.nextToken());

            // sr, sc = sr-1, sc-1
            // er, ec = er-1, ec-1

            int Time = 0;
            int[] dr = {0, 0, 1, -1};
            int[] dc = {1, -1, 0, 0};

            // 큐
            ArrayDeque<Node> Q = new ArrayDeque<>();
            Q.addLast(new Node(sr, sc, Time));

            // 방문기록
            int[][] visitedL = new int[N][N];
            visitedL[sr][sc] = 1;

            while (!Q.isEmpty()) {
                Node cur = Q.pollFirst();
                int r = cur.r;
                int c = cur.c;
                int t = cur.t;

                if (r == er && c == ec) {
                    Time = t;
                    break;
                }

                // 현재 시간 t초
                // 째깍 - 1초가 흐를 예정이다
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    // 경계선 안에 있고, 장애물이 아니며, 방문하지 않은 경우 -
                    if (0 <= nr && nr < N && 0 <= nc && nc < N && List[nr][nc] != 1 && visitedL[nr][nc] == 0) {

                        // 다음 칸이 일반 칸인 경우
                        if (List[nr][nc] == 0) {
                            visitedL[nr][nc] = 1;
                            Q.addLast(new Node(nr, nc, t + 1));
                        }

                        // 다음 칸이 소용돌이 칸인 경우
                        else if (List[nr][nc] == 2) {
                            // 다음 턴에 소용돌이가 사라진다면, 지나갈 수 있음
                            if ((t + 1) % 3 == 0) {
                                visitedL[nr][nc] = 1;
                                Q.addLast(new Node(nr, nc, t + 1));
                            } else {
                                Q.addLast(new Node(r, c, t + 1));
                            }
                        }
                    }
                }
            }

            if (Time == 0) {
                out.append("-1\n");
            } else {
                out.append(Time).append("\n");
            }
        }

        System.out.print(out.toString());
    }
}
