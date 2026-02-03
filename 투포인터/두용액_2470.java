package algorithm;

import java.util.*;
import java.io.*;

public class 두용액_2470 {
	
	// 투포인터로 풀면 된다잉 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		for (String s: br.readLine().split(" "))
			list.add(Integer.parseInt(s));
		
		list.sort(null);
		
		int start = 0;
        int end = list.size() - 1;

        int result = Integer.MAX_VALUE;
        int ans_1 = 0, ans_2 = 0;

        while (start < end) {
            int one = list.get(start);
            int two = list.get(end);
            int sum = one + two;

            int abs = Math.abs(sum);
            if (abs < result) {
                result = abs;
                ans_1 = one;
                ans_2 = two;
            }

            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }

		System.out.println(ans_1 + " " + ans_2);
	}

}
