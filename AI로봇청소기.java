import java.util.*;
import java.io.*;

public class AI로봇청소기 {
    public static void main(String[] args) throws Exception {
        
        
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
    	for (int k = 1; k <= K; k++) {
    		st = new StringTokenizer(br.readLine());
    		robotL[k] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    	}
    	
    	for (int l = 0; l < L; l++) {
    		
    		// 1. 청소기 이동
    		// 각각의 로봇 청소기는 순서대로 이동 거리가 가장 가까운 오염된 격자로 이동합니다
    		ArrayDeque<int[]> Q = new ArrayDeque<>();
    		for (int k = 0; k < K; k++) {
    			int r = robotL[k][0];
    			int c = robotL[k][0];
    			
    			Q.offer(new int[] {r, c});
    			ArrayList<int[]> checkList = new ArrayList<>();
    			while (!Q.isEmpty()) {    				
    				int len = Q.size();    				
    				boolean find = false;
    				for (int i = 0; i < len; i++) {
    					if (find) break;
    					int[] node = Q.poll();
    					if (map[node[0]][node[1]] != 0) {
    						checkList.add(node);
    						find = true;
    					}
    					if 
    				}
    				
    			}
    			
    		}
    		
    		// 물건이 위치한 격자나 청소기가 있는 격자로는 지나갈 수 없습니다.
    		
    		// 가장 가까운 격자가 여러 개일 경우 행 번호가 작은 격자로 이동하고,
    		// 행 번호가 같을 경우에는 열 번호가 작은 격자로 이동합니다.
    		
    		// 2. 청소
    		// 청소는 청소기마다 순서대로 진행
    		// 청소기는 바라보고 있는 방향을 기준으로,
    		// 본인이 위치한 격자, 자신의 왼쪽 격자, 위쪽 격자, 오른쪽 격자를 청소할 수 있습니다.
    		
    		// 청소할 수 있는 4가지 격자에서 (오른쪽, 아래쪽, 왼쪽, 위쪽 방향의 우선순위로 방향을 선택)
    		// 청소할 수 있는 먼지량이 가장 큰 방향에서 청소를 시작합니다.
    		
    		// 격자마다 청소할 수 있는 최대 먼지량은 20입니다.
    		
    		// 3. 먼지 축적
    		// 먼지가 있는 모든 격자에 동시에 5씩 추가됨
    		
    		// 4. 먼지 확산
    		// 깨끗한 격자에 주변 4방향 격자의 먼지량 합을 10으로 나눈 몫만큼 먼지가 확산됩니다.
    		
    		// 5. 전체 공간의 총 먼지량을 출력합니다.
    	}
    }
}
