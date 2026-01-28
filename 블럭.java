import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int Line = Integer.parseInt(br.readLine());

            int[] Block = new int[Line];
            int[] Floor = new int[100];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int n = 0; n < Line; n++) {
                int height = Integer.parseInt(st.nextToken());
                Block[n] = height;
            }

            int Max_Height = 0;
            for (int n = Line-1; n >= 0; n--) {
                
                // 블럭이 있음
                if (Block[n] > 0) {
                    for (int i = 0; i < Block[n]; i++) {
                        Max_Height = Math.max(Max_Height, (Line-1-n)-Floor[i]);
                        Floor[i]++;
                    }
                    // System.out.println(Arrays.toString(Floor));
                    // System.out.println(Max_Height);
                }
            }

            System.out.println("#"+t+" "+Max_Height);            
        }
    }
}
