import java.util.*;
import java.io.*;

class Solution {

    static ArrayList<int[]> edges;
    static int[] parentL;
    static int[] rank;
    
    public static int find(int x) {
        if (parentL[x] != x)
            parentL[x] = find(parentL[x]);
        return parentL[x];
    }

    public static boolean union(int a, int b) {

        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;
        if (rank[pa] < rank[pb]) {
            parentL[pa] = pb;
        } else {
            parentL[pb] = pa;
            if (rank[pa] == rank[pb]) {
                rank[pa] += 1;
            }
        }        
        return true;
    }

    
    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
        
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            parentL = new int[V+1];
            rank = new int[V+1];

            for (int v = 1; v < V+1; v++) {
                parentL[v] = v;
            }
            
            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new int[] {cost, a, b});
            }

            edges.sort((e1, e2) -> Integer.compare(e1[0], e2[0]));
            long mstCost = 0;
            for (int e = 0; e < E; e++) {
                int cost = edges.get(e)[0];
                int a = edges.get(e)[1];
                int b = edges.get(e)[2];
                
                if (union(a, b)) {
                    mstCost += cost;
                }
            }
            sb.append("#" + t + " " + mstCost + "\n");                            
        }

        System.out.println(sb);
            
    }
}
