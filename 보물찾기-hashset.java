import java.util.*;
import java.io.*;

// 보물상자의 비밀번호
// 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수

class Main {
    
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t < T+1; t++) {

            // 입력
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<Integer> numberL = new ArrayList<>();
            for (char c : br.readLine().toCharArray()) {
                int number = Integer.parseInt(String.valueOf(c), 16);
                numberL.add(number);                
            }

            int round = (int)(N / 4);
            HashSet<Integer> set = new HashSet<>();

            for (int turn = 0; turn < round; turn++) {                
                for (int v = 0; v < N; v += round){            
                    int number = 0;            
                    for (int r = 0; r < round; r++) {
                        number = number * 16 + numberL.get(v+r);
                    }            
                    set.add(number);
                }
            
                int tmp = numberL.remove(0); // 회전
                numberL.add(tmp);
            }

            ArrayList<Integer> list = new ArrayList<>(set);
            Collections.sort(list, Collections.reverseOrder());
            int k_number = list.get(K-1);
            
            sb.append("#" + t + " " + k_number + "\n");
        }
        System.out.println(sb);
    }
}
