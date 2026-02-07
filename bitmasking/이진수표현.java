import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = input[0];
            int M = input[1];

            int Check = M & ((1 << N) - 1);
            if (Check == ((1 << N) - 1)) sb.append("#" + t + " ON\n");
            else sb.append("#" + t + " OFF\n");
        }

        System.out.println(sb);
        
    }
}
