import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        final int FULL = (1 << 10) - 1; // 1023

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int Number = 0, count = 0;
            int mask = 0;

            for (int n = 0; n < 100; n++) {
                Number += N;
                count++;

                int temp = Number;
                if (temp == 0) {
                    mask |= 1; // 1 << 0
                }
                
                while (temp > 0) {
                    int digit = temp % 10;
                    mask |= (1 << digit);
                    temp /= 10;
                }

                if (mask == FULL) {
                    sb.append("#").append(t + 1).append(" ").append(Number).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
