package algorithm;

import java.util.*;
import java.io.*;

public class 특이한자석 {

	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		
		for (int t = 1; t <= T; t++) {
			
			sb.append("#").append(t).append(" ");
			
			int K = Integer.parseInt(br.readLine());

			// 톱니바퀴 정보
			int[][] MagnetList = new int[5][8]; 
			for (int i = 1; i <= 4; i++) {
				 
				String[] input = br.readLine().split(" ");
				int b = 0;
				for (String s : input) {
					MagnetList[i][b++] = Integer.parseInt(s);
				}
			}
			
			// 자석의 회전 정보 : 회전시키려는 자석의 번호, 회전방향
			// 회전방향은 1 일 경우 시계방향이며, -1 일 경우 반시계방향이다.
			for (int k = 0; k < K; k++) {
				String[] input = br.readLine().split(" ");
				int number = Integer.parseInt(input[0]);
				int dir = Integer.parseInt(input[1]);			

				int[] toRotate = new int[5];
				toRotate[number] = dir*10;
				
				// 오른쪽으로 최대 3칸
				int startR = MagnetList[number][2];
				int next_dir = -dir;
				for (int m = 1; m <= 3; m++) {
					if (number + m <= 4) {
						int check = MagnetList[number + m][6];
						if (startR != check) { // 반대로 회전							
							toRotate[number + m] = next_dir * 10;									
							next_dir = -next_dir;
						}
						else { break; }
						startR = MagnetList[number + m][2];
					}
				}
				
				// 왼쪽으로 최대 3칸
				int startL = MagnetList[number][6];
				next_dir = -dir;
				for (int m = 1; m <= 3; m++) {
					if (number - m >= 0) {
						int check = MagnetList[number - m][2];
						if (startL != check) { // 반대로 회전
							toRotate[number - m] = next_dir * 10;									
							next_dir = -next_dir;
						}
						else { break; }
						startL = MagnetList[number - m][6];
					}					
				}		

				// 자석을 회전시킴
				for (int i = 1; i <= 4; i++) {
//					System.out.print(toRotate[i] + " ");
					if (toRotate[i] == 10) {
						roundR(MagnetList[i]);
					}
					else if (toRotate[i] == -10) {
						roundL(MagnetList[i]);
					}
				}					
			}

			sb.append(MagnetList[1][0] + MagnetList[2][0]*2 + MagnetList[3][0]*4 + MagnetList[4][0]*8).append("\n");				
		}		

		System.out.println(sb);
	}	
	
	static void roundR(int[] list) {
		int len = list.length;
		int temp = list[len-1];
		for (int i = len-1; i > 0; i--) {
			list[i] = list[i-1];
		}		
		list[0] = temp;
	}

	static void roundL(int[] list) {
		int len = list.length;
		int temp = list[0];
		for (int i = 0; i < len-1; i++) {
			list[i] = list[i+1];
		}		
		list[len-1] = temp;

	}

}
