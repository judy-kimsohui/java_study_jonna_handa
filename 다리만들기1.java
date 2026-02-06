package algorithm;

import java.io.*;
import java.util.*;

public class 다리만들기1 {

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;

	static class Node {
		int R;
		int C;
		Node(int r, int c) {
			this.R = r;
			this.C = c;
		}
	}
	
	
	static int[][] Map;
	static boolean[][] VisitedL;
	
	static List<List<Node>> EdgeList;
	
	static void BFS_findEdge(int R, int C) {
		
		VisitedL[R][C] = true;
		
		// 0 Edge Node
		List<Node> Edge = new ArrayList<>();
		
		// 1 Island Node
		ArrayDeque<Node> Q = new ArrayDeque<>();
		Q.offer(new Node(R, C));
		
		while(!Q.isEmpty()) {
			Node node = Q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = node.R + dr[i];
				int nc = node.C + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (VisitedL[nr][nc] != true && Map[nr][nc] == 1) { // 섬인 경우
					VisitedL[nr][nc] = true;
					Q.offer(new Node(nr, nc));
				}
				else if (Map[nr][nc] == 0) { // 엣지인 경우
					Edge.add(new Node(node.R, node.C));
				}
			}			
		}		

		EdgeList.add(Edge);		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Map = new int[N][N];
		VisitedL = new boolean[N][N];
		
		EdgeList = new ArrayList<>();
		
		for (int row = 0; row < N; row++) {
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Map[row] = input;
		}
				
		// 각 섬에 대하여 테두리 부분을 저장해두자	
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (VisitedL[r][c] != true && Map[r][c] == 1) {					
					BFS_findEdge(r, c);
				}
			}
		}
		
		// 테두리 배열을 통해, BFS를 통해 1에 닿는 가장 짧은 다리 길이를 구한다.	
		int MinLength = N*N;
		for (List<Node> EdgeL : EdgeList) {
			for (Node edge : EdgeL) {
				
			}
		}
		
	}
}
