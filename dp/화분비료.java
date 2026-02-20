import java.util.*;
import java.io.*;

public class 화분비료 {

	public static void main(String[] args) throws Exception {
		
		// 화분에 1번, 2번 비료를 주었을 때 자라는 높이
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			int[] grow1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] grow2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			int[] water1 = new int[N];
			int[] water2 = new int[N];
			
			water1[0] = grow1[0];
			water2[0] = grow2[0];
			
			for (int n = 1; n < N; n++) {
				water1[n] = Math.max(water1[n-1]+grow1[n]-P, water2[n-1]+grow1[n]);
				water2[n] = Math.max(water2[n-1]+grow2[n]-P, water1[n-1]+grow2[n]);
			}
			
			sb.append("#" + t + " " + Math.max(water1[N-1], water2[N-1]) + "\n");
			
		}
		System.out.println(sb);
	}
}
