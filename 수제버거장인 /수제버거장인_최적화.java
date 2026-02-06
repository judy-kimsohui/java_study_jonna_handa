import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int n,m;
    public static int[][] arr;
    public static int answer;
    public static HashSet<Integer> set;
    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken()); //재료 개수
            m = Integer.parseInt(st.nextToken()); //궁합이 맞지 않는 쌍의 개수
            
            set = new HashSet<>();
            
            answer = 0;
            for(int i = 0;i<m;i++) {
            	st = new StringTokenizer(br.readLine(), " ");
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	set.add((1<<a-1) | (1<<b-1));
            }
            
            func(0,0);
            
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
    }
    
    public static void func(int cur, int idx) {
    	// 만들 수 없는 조합이 있으면 return
    	for(int s : set) {
    		if((cur & s) == s) {
    			return;
    		}
    	}
    	
    	//마지막까지 왔으면 버거 만들 수 있으니까 answer++
    	if(idx == n) {
    		answer++;
    		return;
    	}
    	
		//선택한거
    	func(cur + (1 << idx), idx + 1);	
	
    	
    	//선택 안한거
    	func(cur, idx + 1);
    }
}
