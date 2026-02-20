import java.util.*;
import java.io.*;

public class 무선충전 {

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			// 사용자는 총 2명
			int[] aMove = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] bMove = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int[] dr = {0, -1, 0, 1, 0};
			int[] dc = {0, 0, 1, 0, -1};			
			
			// 브루트포스
			ArrayList<ArrayList<Integer>>[][] map = new ArrayList[10][10];
			for (int row = 0; row < 10; row++) {
				for (int col = 0; col < 10; col++) {
					map = new ArrayList<>();
				}
			}
			
			
		}
		
		
	}
}
