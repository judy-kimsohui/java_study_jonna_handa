import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(br.readLine());
            int[] xList = new int[N+1];
            int[] yList = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                xList[x] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                yList[y] = Integer.parseInt(st.nextToken());
            }

            double em = Double.parseDouble(br.readLine());

            // 모든 해저터널을 이은 리스트를 만들고, 첫번째 노드부터 시작하여 해저터널 탐색 후 꺼냄
            ArrayList<long[]>[] DistanceL = new ArrayList[N+1];
            for (int i = 0; i <= N; i++) {
                DistanceL[i] = new ArrayList<>();
            }
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    long dx = xList[i] - xList[j];
                    long dy = yList[i] - yList[j];
                    long dist = dx*dx + dy*dy;
                    DistanceL[i].add(new long[] {j, dist});
                    DistanceL[j].add(new long[] {i, dist});
                }                
            }

            PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[1], b[1]));
            pq.offer(new long[]{0,0});
            
            boolean[] visitedL = new boolean[N];
            long Result = 0;
            int count = 0;
            
            while (!pq.isEmpty()) {
            
                long[] cur = pq.poll();
                int node = (int)cur[0];
                long cost = cur[1];
            
                if (visitedL[node]) continue;
            
                visitedL[node] = true;
                Result += cost;
                count++;
            
                if (count == N) break;
            
                for (long[] n : DistanceL[node]) {
            
                    int newNode = (int)n[0];
                    long dist = n[1];
            
                    if (!visitedL[newNode]) {
                        pq.offer(new long[]{newNode, dist});
                    }
                }
            }

            sb.append("#" + t + " " + Math.round(Result*em) + "\n");

        }
        
        System.out.println(sb);
        
    }
}
