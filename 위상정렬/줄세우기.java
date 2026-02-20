import java.util.*;
import java.io.*;

public class 줄세우기 {

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] graph = new ArrayList[N+1];
			for (int n = 1; n < N+1; n++) {
				graph[n] = new ArrayList<>();
			}
			
			int[] indegree = new int[N+1];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				indegree[b] += 1;
			}
			
			ArrayDeque<Integer> Q = new ArrayDeque<>();
			for (int n = 1; n < N+1; n++) {
				if (indegree[n] == 0) Q.offer(n);
			}
			
			while (!Q.isEmpty()) {
				int node = Q.poll();
				sb.append(node + " ");
				for (int n : graph[node]) {
					if (--indegree[n] == 0) Q.offer(n);					
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
}
