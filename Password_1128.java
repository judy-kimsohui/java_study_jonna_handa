package algorithm;

import java.util.*;
import java.io.*;

public class Password_1128 {
	
	public static void main(String[] args) throws Exception {
		//	 I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			
		    int N = Integer.parseInt(br.readLine());

		    String input = br.readLine();
		    String[] rawPassword = input.split("\\s+");
		    
		    LinkedList<Integer> list = new LinkedList<>();

		    for (String s : rawPassword) {
		        list.add(Integer.parseInt(s));
		    }

		    int M = Integer.parseInt(br.readLine());

		    input = br.readLine();
		    String[] command = input.split("\\s*\\|\\s*");

//		    System.out.println(Arrays.toString(command));
		    
		    StringTokenizer st = new StringTokenizer(input);

		    while (st.hasMoreTokens()) {
		        String cmd = st.nextToken(); // "I"

		        if (cmd.equals("I")) {
		            int idx = Integer.parseInt(st.nextToken());
		            int cnt = Integer.parseInt(st.nextToken());

		            for (int i = 0; i < cnt; i++) {
		                list.add(idx + i, Integer.parseInt(st.nextToken()));
		            }
		        }
		    }
		    
		    System.out.print("#" + t + " ");

		    // 확인용
		    for (int i = 0; i < 10; i++) {
		        System.out.print(list.get(i) + " ");
		    }
		    System.out.println();
		
		}		
	}
}
