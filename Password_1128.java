package algorithm;

import java.io.*;
import java.util.*;

public class Password_1129_mod {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {

            br.readLine(); // N 

            LinkedList<Integer> list = new LinkedList<>();
            for (String s : br.readLine().split(" "))
                list.add(Integer.parseInt(s));

            br.readLine(); // M 

            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                st.nextToken(); // "I"
                int idx = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                for (int i = 0; i < cnt; i++)
                    list.add(idx + i, Integer.parseInt(st.nextToken()));
            }

            System.out.print("#" + t + " ");
            for (int i = 0; i < 10; i++)
                System.out.print(list.get(i) + " ");
            System.out.println();
        }
    }
}
