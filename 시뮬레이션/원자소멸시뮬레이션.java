package algorithm;

import java.util.*;
import java.io.*;

public class 원자소멸시뮬레이션 {
	
	static class Point {
		int r, c;
		int dir;
		int energy;
		public Point(int r, int c, int dir, int E) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.energy = E;
		}
	}
	
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
	    
	    ArrayDeque<Point> pointQ;
	    
	    int T = Integer.parseInt(br.readLine());
	    for (int t = 1; t <= T; t++) {
	    	
	    	int N = Integer.parseInt(br.readLine());
	    	//  x 위치, y 위치, 이동 방향, 보유 에너지 K
	    	
	    	// 원자들은 2차원 평면 위에서 움직이며 원자들이 움직일 수 있는 좌표의 범위에 제한은 없다

	    	// int key = nr * 4001 + nc;
	    	HashMap<Integer, ArrayList<Point>> visitedL = new HashMap<>();
	    	pointQ = new ArrayDeque<>();
	    	
	    	for (int n = 0; n < N; n++) {
	    		st = new StringTokenizer(br.readLine());
	    		int c = (Integer.parseInt(st.nextToken())+1000) << 1;
	    		int r = (Integer.parseInt(st.nextToken())+1000) << 1;
	    		int dir = Integer.parseInt(st.nextToken());
	    		int E = Integer.parseInt(st.nextToken());
	    		
	    		if ((r >= 4000 && dir == 0) || (r <= 0 && dir == 1) || (c >= 4000 && dir == 3) || (c <= 0 && dir == 2)) continue;
	    		pointQ.offer(new Point(r, c, dir, E));	    		
	    	}
	    	
	    	int EnergySum = 0, count = 0;
	    	for (int time = 0; time < 4000; time++) {
	    		
    			if (count >= N) break;

    			visitedL = new HashMap<>();
    			int length = pointQ.size();
	    		for (int p = 0; p < length; p++) {	    			
	    				    	
	    			Point point = pointQ.poll();	    			
    				int nr = point.r + dr[point.dir];
    				int nc = point.c + dc[point.dir]; 
    				
    				if (nr < 0 || nr > 4000 || nc < 0 || nc > 4000) continue;

    				int key = nr * 4001 + nc;			
    				visitedL.computeIfAbsent(key, k -> new ArrayList<>()).add(point);    			
    				point.r = nr;
    				point.c = nc;
	    		}
	    		
	    		// 충돌 처리
	    		for (ArrayList<Point> list : visitedL.values()) {
	    		    if (list.size() >= 2) {
	    		        for (Point p : list) {
	    		            EnergySum += p.energy;
	    		        }
	    		    } else {
	    		        pointQ.offer(list.get(0));
	    		    }
	    		}
	    	}
	    	
	    	sb.append("#" + t + " " + EnergySum + "\n");	    	
	    	
	    }
	    
		System.out.println(sb);
	}	
}
