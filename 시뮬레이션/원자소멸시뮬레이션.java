package algorithm;

import java.util.*;
import java.io.*;

public class 원자소멸시뮬레이션 {
	
	public static void main(String[] args) throws Exception {
		
		// 원자들은 2차원 평면에서 이동하고,
		// 두 개 이상의 원자가 충돌할 경우 충돌한 원자들은 각자 보유한 에너지를 모두 방출하고 소멸된다.
		
		StringBuilder sb = new StringBuilder(); 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 상 +y 0
		// 하 -y 0 
		// 좌 0  -x
		// 우 0  +x
		
		// 모든 원자들은 최초 위치에서 동시에 이동을 시작한다
		
		// N개의 원자들의 위치, 이동 방향, 보유 에너지가 주어질 때,
		// 원자들이 소멸되면서 방출하는 에너지의 총합을 구하자
		
		// 상(0), 하(1), 좌(2), 우(3)
	    int[] dr = {1, -1, 0, 0};
	    int[] dc = {0, 0, -1, 1};
	    
	    int T = Integer.parseInt(br.readLine());
	    for (int t = 1; t <= T; t++) {
	    	
	    	int N = Integer.parseInt(br.readLine());
	    	//  x 위치, y 위치, 이동 방향, 보유 에너지 K
	    	
	    	// 원자들은 2차원 평면 위에서 움직이며 원자들이 움직일 수 있는 좌표의 범위에 제한은 없다
	    	int[][] map = new int[2001][2001];
	    	
	    	st = new StringTokenizer(br.readLine());
	    	int r = (Integer.parseInt(st.nextToken())+1000) << 1;
	    	int c = (Integer.parseInt(st.nextToken())+1000) << 1;
	    	int dir = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	
	    }
	    
		
	}	
}
