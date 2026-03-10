import java.util.*;
import java.io.*;

class Main {

    static int[] xList;
    static int[] yList;

    static int[][] graph;

    static int[][] dp;
    static int V;
    static int homeX, homeY;

    static int INF = Integer.MAX_VALUE;

    // 외판원 순회, 비트마스킹으로 현재 방문 노드에 따라 dp의 cost를 최소값으로 갱신한다.
    // 1011 -> 0번,1번,3번 고객 방문
    // visited -> 지금까지 방문한 고객들의 상태 (비트마스크)
    static int tsp(int cur, int visited) {

        // 모든 고객을 방문 완료한 경우, 집까지 도착하고 cost를 리턴
        if (visited == (1 << V) - 1) 
            return Math.abs(xList[cur] - homeX) + Math.abs(yList[cur] - homeY);

        // 이미 계산된 상태라면, 다시 탐색하지 않고 계산된 최소 비용바로 반환
        if (dp[cur][visited] != -1) return dp[cur][visited];

        // 아직 계산되지 않은 상태라면 큰 값(INF)으로 초기화
        dp[cur][visited] = INF;

        for (int next = 0; next < V; next++) {

            // 이미 방문한 next 집이라면 제외
            // (예시)
            // visited = 1011
            // next = 1
            // 1011 & 0010 = 0010 -> 이미 방문
            if ((visited & (1 << next)) != 0) continue;

            // next 고객으로 이동하는 비용, next 고객을 방문했다고 표시, 다음 고객으로 넘어감
            int cost = graph[cur][next] + tsp(next, visited | (1 << next));

            // 값 갱신
            dp[cur][visited] = Math.min(dp[cur][visited], cost);
        }

        // 계산된 최소 비용 반환
        return dp[cur][visited];
    }
    
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            V = Integer.parseInt(br.readLine());

            xList = new int[V];
            yList = new int[V];

            graph = new int[V][V];

            st = new StringTokenizer(br.readLine());

            // 회사 위치
            int companyX = Integer.parseInt(st.nextToken());
            int companyY = Integer.parseInt(st.nextToken());

            // 집 위치
            homeX = Integer.parseInt(st.nextToken());
            homeY = Integer.parseInt(st.nextToken());

            // 고객의 위치
            for (int v = 0; v < V; v++) {
                xList[v] = Integer.parseInt(st.nextToken());
                yList[v] = Integer.parseInt(st.nextToken());
            }

            // 고객 사이의 거리(cost)를 모두 저장함
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    graph[i][j] = Math.abs(xList[i] - xList[j]) + Math.abs(yList[i] - yList[j]);
                }
            }

            // 모든 방문 경우의 수에 대한 (V까지 왔을 때 어디 방문함) dp (cost 저장)
            dp = new int[V][1 << V];
            for (int i = 0; i < V; i++) {
                Arrays.fill(dp[i], -1);
            }

            int answer = INF;

            for (int i = 0; i < V; i++) {

                // 회사에서 첫고객(i) + tsp(모든 고객 탐색 + 집)
                int companyToCostomer = Math.abs(companyX - xList[i]) + Math.abs(companyY - yList[i]);
                int cost = companyToCostomer + tsp(i, 1 << i);

                // 최단 이동거리 갱신
                answer = Math.min(answer, cost);
            }

            sb.append("#" + t + " " + answer + "\n");
        }

        System.out.println(sb);
    }
}
