import java.util.*;
import java.io.*;

public class AI로봇청소기 {
    public static void main(String[] args) throws Exception {
        
    	PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

    	StringBuilder sb = new StringBuilder();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;

    	// 입력
    	st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	int L = Integer.parseInt(st.nextToken());
    	
    	// N줄에 걸쳐 Map
    	int[][] map = new int[N+1][N+1];
    	for (int n = 1; n <= N; n++) {
    		int i = 1;
    		for (String s : br.readLine().split(" ")) {
    			map[n][i++] = Integer.parseInt(s);
    		}
    	}
    	
    	int[][] robotL = new int[K][2];
    	for (int k = 0; k < K; k++) {
    		st = new StringTokenizer(br.readLine());
    		robotL[k] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    		map[robotL[k][0]][robotL[k][1]] += 100;
    	}
    	
    	int[] dr = {0, 1, 0, -1};
    	int[] dc = {1, 0, -1, 0};    
    	
		out.printf("init");
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				out.printf("%3d ", map[r][c]);
			}
			out.println();
		}
		
    	
    	for (int l = 0; l < L; l++) {
    		
    		// 1. 청소기 이동
    		// 각각의 로봇 청소기는 순서대로 이동 거리가 가장 가까운 오염된 격자로 이동합니다    		    		
    		// 각 청소기
    		for (int k = 0; k < K; k++) {
    			int r = robotL[k][0];
    			int c = robotL[k][1];
    			map[r][c] -= 100;	// 청소기 제거
    			
    			ArrayDeque<int[]> Q = new ArrayDeque<>();
    			ArrayList<int[]> checkList = new ArrayList<>();
    			boolean[][] visitedL = new boolean[N+1][N+1];
    			visitedL[r][c] = true;
    			Q.offer(new int[] {r, c});
    			boolean find = false;
    			while (!Q.isEmpty() && !find) {    				
    				int len = Q.size();    				    				
    				for (int i = 0; i < len; i++) {
    					int[] node = Q.poll();
    					// 오염된 격자라면, 후보지에 추가합니다.
    					// 물건이 위치한 격자나 청소기가 있는 격자로는 지나갈 수 없습니다.
    					if (map[node[0]][node[1]]%100 > 0) {
    						checkList.add(node);
    						find = true;
    					}
    					// 탐색 범위를 넓힙니다.
    					for (int d = 0; d < 4; d++) {
    						int nr = node[0] + dr[d];
    						int nc = node[1] + dc[d];
    						if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
    						if (map[nr][nc] != -1 && map[nr][nc]/100 == 0 && !visitedL[nr][nc]) {
    							visitedL[nr][nc] = true;
    							Q.offer(new int[] {nr, nc});

    						}
    					}
    				}
    				
    			}
    			
    			// 가장 가까운 격자가 여러 개일 경우 행 번호가 작은 격자로 이동하고,
        		// 행 번호가 같을 경우에는 열 번호가 작은 격자로 이동합니다.
				if (checkList.isEmpty()) {
					map[r][c] += 100; // 제자리
//					out.println("hey");
				} else {
					checkList.sort((o1, o2) -> {
						if (o1[0] != o2[0]) {
							return o1[0] - o2[0];  // 행 기준
						}
						return o1[1] - o2[1];      // 열 기준
					});
					
					robotL[k][0] = checkList.get(0)[0];
					robotL[k][1] = checkList.get(0)[1];
					map[robotL[k][0]][robotL[k][1]] += 100;	 // 청소기 추가
				}
    		}
    		
    		out.printf("case %d 1. 청소기 이동 \n", l+1);
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				out.printf("%3d ", map[r][c]);
    			}
    			out.println();
    		}
    		
    		
    		// 2. 청소
    		// 청소는 각 청소기마다 순서대로 진행
    		for (int k = 0; k < K; k++) {
    			
    			// 청소기는 바라보고 있는 방향을 기준으로,
    			// 본인이 위치한 격자, 자신의 왼쪽 격자, 위쪽 격자, 오른쪽 격자를 청소할 수 있습니다.
    			
    			// 청소할 수 있는 4가지 격자에서 (오른쪽, 아래쪽, 왼쪽, 위쪽 방향의 우선순위로 방향을 선택)
    			// 청소할 수 있는 먼지량이 가장 큰 방향에서 청소를 시작합니다.
    			int r = robotL[k][0];
    			int c = robotL[k][1];
    			
    			int sum = 0;    			
    			int check = 0;
    			int index = 0;
    			
    			// 첫번째
    			check = map[r][c]%100;
    			if (c+dc[0] <= N) check += map[r][c+dc[0]]%100; // 우
    			if (r+dr[3] >= 1) check += map[r+dr[3]][c]%100; // 상
    			if (r+dr[1] <= N) check += map[r+dr[1]][c]%100; // 하
    			sum = Math.max(sum, check);
    			index = sum > check ? index : 0;
    			
    			// 두번째
    			check = map[r][c]%100;
    			if (c+dc[0] <= N) check += map[r][c+dc[0]]%100; // 우
    			if (c+dc[2] >= 1) check += map[r][c+dc[2]]%100; // 좌
    			if (r+dr[1] <= N) check += map[r+dr[1]][c]%100; // 하
    			sum = Math.max(sum, check);
    			index = sum > check ? index : 1;
    			
    			// 세번째
    			check = map[r][c]%100;
    			if (c+dc[2] >= 1) check += map[r][c+dc[2]]%100; // 좌
    			if (r+dr[3] >= 1) check += map[r+dr[3]][c]%100; // 상
    			if (r+dr[1] <= N) check += map[r+dr[1]][c]%100; // 하
    			sum = Math.max(sum, check);
    			index = sum > check ? index : 2;    			
    			
    			// 네번째
    			check = map[r][c]%100;
    			if (c+dc[0] <= N) check += map[r][c+dc[0]]%100; // 우
    			if (r+dr[3] >= 1) check += map[r+dr[3]][c]%100; // 상
    			if (c+dc[2] >= 1) check += map[r][c+dc[2]]%100; // 좌
    			sum = Math.max(sum, check);
    			index = sum > check ? index : 3;    			
    			
    			// 격자마다 청소할 수 있는 최대 먼지량은 20입니다.
    			switch(index) {
    			case 0:
    				if (map[r][c]%100 < 20) map[r][c] = 0 + (map[r][c]/100)*100; else map[r][c] -= 20;
        			if (c+dc[0] <= N) { if (map[r][c+dc[0]]%100 < 20) map[r][c+dc[0]] = 0 + (map[r][c+dc[0]]/100)*100; else  map[r][c+dc[0]] -= 20; } // 우
        			if (r+dr[3] >= 1) { if (map[r+dr[3]][c]%100 < 20) map[r+dr[3]][c] = 0 + (map[r+dr[3]][c]/100)*100; else  map[r+dr[3]][c] -= 20; } // 상
        			if (r+dr[1] <= N) { if (map[r+dr[1]][c]%100 < 20) map[r+dr[1]][c] = 0 + (map[r+dr[1]][c]/100)*100; else  map[r+dr[1]][c] -= 20; } // 하
        			break;
    			case 1:
    				if (map[r][c]%100 < 20) map[r][c] = 0 + (map[r][c]/100)*100; else map[r][c] -= 20;
        			if (c+dc[0] <= N) { if (map[r][c+dc[0]]%100 < 20) map[r][c+dc[0]] = 0 + (map[r][c+dc[0]]/100)*100; else  map[r][c+dc[0]] -= 20; } // 우
        			if (c+dc[2] >= 1) { if (map[r][c+dc[2]]%100 < 20) map[r][c+dc[2]] = 0 + (map[r][c+dc[2]]/100)*100; else  map[r][c+dc[2]] -= 20; } // 좌
        			if (r+dr[1] <= N) { if (map[r+dr[1]][c]%100 < 20) map[r+dr[1]][c] = 0 + (map[r+dr[1]][c]/100)*100; else  map[r+dr[1]][c] -= 20; } // 하
        			break;
    			case 2:
    				if (map[r][c]%100 < 20) map[r][c] = 0 + (map[r][c]/100)*100; else map[r][c] -= 20;
        			if (c+dc[2] >= 1) { if (map[r][c+dc[2]]%100 < 20) map[r][c+dc[2]] = 0 + (map[r][c+dc[2]]/100)*100; else  map[r][c+dc[2]] -= 20; } // 좌
        			if (r+dr[3] >= 1) { if (map[r+dr[3]][c]%100 < 20) map[r+dr[3]][c] = 0 + (map[r+dr[3]][c]/100)*100; else  map[r+dr[3]][c] -= 20; } // 상
        			if (r+dr[1] <= N) { if (map[r+dr[1]][c]%100 < 20) map[r+dr[1]][c] = 0 + (map[r+dr[1]][c]/100)*100; else  map[r+dr[1]][c] -= 20; } // 하
        			break;
    			case 3:
    				if (map[r][c]%100 < 20) map[r][c] = 0 + (map[r][c]/100)*100; else map[r][c] -= 20;
        			if (c+dc[0] <= N) { if (map[r][c+dc[0]]%100 < 20) map[r][c+dc[0]] = 0 + (map[r][c+dc[0]]/100)*100; else  map[r][c+dc[0]] -= 20; } // 우
        			if (r+dr[3] >= 1) { if (map[r+dr[3]][c]%100 < 20) map[r+dr[3]][c] = 0 + (map[r+dr[3]][c]/100)*100; else  map[r+dr[3]][c] -= 20; } // 상
        			if (c+dc[2] >= 1) { if (map[r][c+dc[2]]%100 < 20) map[r][c+dc[2]] = 0 + (map[r][c+dc[2]]/100)*100; else  map[r][c+dc[2]] -= 20; } // 좌
        			break;
    			}
    		}
    		
    		out.printf("case %d 2. 청소 \n", l+1);
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				out.printf("%3d ", map[r][c]);
    			}
    			out.println();
    		}
    		
    		// 3. 먼지 축적
    		// 먼지가 있는 모든 격자에 동시에 5씩 추가됨
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				if (map[r][c]%100 > 0) map[r][c] += 5;
    			}
    		}
    		
    		out.printf("case %d 3. 먼지 축적 \n", l+1);
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				out.printf("%3d ", map[r][c]);
    			}
    			out.println();
    		}
    		
    		// 4. 먼지 확산
    		// 깨끗한 격자에 주변 4방향 격자의 먼지량 합을 10으로 나눈 몫만큼 먼지가 확산됩니다.
    		boolean[][] clean = new boolean[N+1][N+1];
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				boolean flag = false;
    				if (map[r][c]%100 == 0) {
    					clean[r][c] = true;
    					if (map[r][c] == 100) flag= true;
    					for (int i = 0; i < 4; i++) {
    						int nr = r + dr[i];
    						int nc = c + dc[i];
    						if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
    						if (map[nr][nc]%100 > 0 && !clean[nr][nc]) {
    							if (map[nr][nc] > 100) map[r][c] += (map[nr][nc]-100);
    							else map[r][c] += map[nr][nc];
    						}
    					}
    					if (flag) map[r][c] -= 100;
    					map[r][c] /= 10;
    					if (flag) map[r][c] += 100;
    				}
    			}
    		}
    		
    		for (int k = 0; k < K; k++) {		
    			map[robotL[k][0]][robotL[k][1]] = map[robotL[k][0]][robotL[k][1]]%100 + 100;
    		}

    		out.printf("case %d 4. 먼지 확산 \n", l+1);
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				out.printf("%3d ", map[r][c]);
    			}
    			out.println();
    		}
    		
    		// 5. 전체 공간의 총 먼지량을 출력합니다.
    		int map_sum = 0;
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				if (map[r][c]%100 > 0) map_sum += map[r][c]%100;
    			}
    		}
    		sb.append(map_sum);
			if (l != L-1)  sb.append("\n");
    		

    		
    		out.printf("case %d 5. 결과 \n", l+1);
    		for (int r = 1; r <= N; r++) {
    			for (int c = 1; c <= N; c++) {
    				out.printf("%3d ", map[r][c]);
    			}
    			out.println();
    		}
    		
    		
    	}
    	
    	System.out.println(sb);
    	out.close();
    }
}
