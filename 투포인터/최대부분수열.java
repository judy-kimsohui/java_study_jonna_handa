import java.io.*;
import java.util.*;

public class 최대부분수열 {

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T+1; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] ArrayL = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] Sum = new int[N];
			Sum[0] = ArrayL[0];
			for (int i = 1; i < N; i++) {
				Sum[i] = Sum[i-1] + ArrayL[i];
			}
			
			
			int tip1 = 0, tip2 = 0;
			
			int Result = Integer.MIN_VALUE;
			for (int i1  = 0+K-1; i1 < N-K; i1++) {
				for (int i2 = i1+K; i2 < N; i2++) {
					int sum;
					if (i1-K >= 0) sum = Sum[i1]-Sum[i1-K] + Sum[i2]-Sum[i2-K];
					else sum = Sum[i1] + Sum[i2]-Sum[i2-K];
					Result = Math.max(Result, sum);
				}
			}
			
			sb.append("#" + t + " " + Result + "\n");
			
		}
		
		System.out.println(sb);
		
	}
	
}
