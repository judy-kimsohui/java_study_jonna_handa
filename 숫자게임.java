import java.util.*;
import java.io.*;

class Main {

    static int Count;
    
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int Number = Integer.parseInt(br.readLine());
            Count = 0;
            dfs(Number, 0);
            sb.append("#" + t + " " + Count + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(int number, int count) {
        if (number < 10) return;
        int[] nanuList = {10, 100, 1000, 10000, 100000};
        for (int nanu : nanuList) {
            double numberA = number / nanu;
            int numberB = number % nanu;
            if (numberA > 1) {
                Count = Math.max(Count, count+1);
                dfs((int)numberA * numberB, count+1);
            }
        }
    }
}
