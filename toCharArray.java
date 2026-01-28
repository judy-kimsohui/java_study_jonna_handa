import java.util.*;
import java.io.*;

class toCharArray {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            String Line = br.readLine();

            int count = 0;
            char flag = '0';
            for (char ch : Line.toCharArray()) {
                if (ch != flag) {
                    flag = ch;
                    count++;
                }
            } 
            System.out.println("#"+t+" "+count);            
        }
    }
}
