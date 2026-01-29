import java.util.*;
import java.io.*;

// 헤헤 헤헤

class Main {

    static class Point {
        int x;
        int t;
        Point(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        Queue<Point> Q = new LinkedList<>();
        boolean[] visited = new boolean[2*(N+K)];
        
        Q.offer(new Point(N, 0));
        visited[N] = true;
        int time = 2*(N+K);

        while (!Q.isEmpty()) {
            Point node = Q.poll();
            if (node.x+1 >= 0 && node.x+1 < 2*(N+K) && !visited[node.x+1]) {
                Q.offer(new Point(node.x+1, node.t+1));
                if (node.x+1 == K) time = Math.min(time, node.t+1);
                visited[node.x+1] = true;
            }
            if (node.x-1 >= 0 && node.x-1 < 2*(N+K) && !visited[node.x-1]) {
                Q.offer(new Point(node.x-1, node.t+1));
                if (node.x-1 == K) time = Math.min(time, node.t+1);
                visited[node.x-1] = true;
            }
            if (node.x*2 >= 0 && node.x*2 < 2*(N+K) && !visited[node.x*2]) {
                Q.offer(new Point(node.x*2, node.t+1));
                if (node.x*2 == K) time = Math.min(time, node.t+1);
                visited[node.x*2] = true;
            }
        }

        System.out.println(time);
    }
}
