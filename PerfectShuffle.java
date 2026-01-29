
import java.util.*;
import java.io.*;

public class PerfectShuffle {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Queue<String> card1 = new LinkedList<>();
			Queue<String> card2 = new LinkedList<>();
			
			for (int i = 0; i < n/2 + n%2; i++) {
				card1.offer(st.nextToken());
			}
			for (int i = n/2 + n%2; i < n; i++) {
				card2.offer(st.nextToken());
			}
			
			
			System.out.print("#"+t+" ");
			for (int i = 0; i < n/2+1; i++) {
				if (!card1.isEmpty()) System.out.print(card1.poll() + " ");
				if (!card2.isEmpty()) System.out.print(card2.poll() + " ");
			}
			
			System.out.println();
		}		
	}
}
