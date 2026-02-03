import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoPointer1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int Target = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(arr));
		
		Arrays.sort(arr);
		int s = 0, e = N-1, ans = 0;
		while (s<e) {
			// 두 포인터가 가리키는 값을 더함 
			int sum = arr[s] + arr[e];
			if (sum == Target) {
				++ans;
				++s;
				--e;
			} else if (sum< Target) {
				// 값이 목표값보다 작다 = 값을 크게 만들자 
				++s;				
			} else {
				// 값이 목표값보다 크다 = 값을 작게 만들자
				--e;
			}
		}
		
	}
	
}
