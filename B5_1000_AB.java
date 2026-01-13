import java.io.*;
import java.util.*;

public class B5_1000_AB {    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sb = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(sb.nextToken());
        int B = Integer.parseInt(sb.nextToken());

        System.out.println(A+B);
        
    }    
} 