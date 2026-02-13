import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Solution
{
    static class Atom implements Comparable<Atom>{
        int x;
        int y;
        int dir;
        int power;
        public Atom(int x, int y, int dir, int power) {
            this.x = x*2;
            this.y = y*2;
            this.dir = dir;
            this.power = power;
        }
        boolean is_deleted = false;
        @Override
        public int compareTo(Atom o) {
            if(this.x==o.x)
                return o.y-this.y;
            return o.x-this.x;
        }
    }
    static int n;
    static int result;
    static Atom[] atoms;
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String args[]) throws Exception
    {
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case+" ");
            n = Integer.parseInt(br.readLine());
            result = 0;
            atoms = new Atom[n];
            Map<Integer,ArrayList<Atom[]>> conflict_map = new HashMap<>();
            for(int i = 0;i<n;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());
                atoms[i] = new Atom(x,y,dir,power); 
            }
            Arrays.sort(atoms);
            // 무조건 j의 x가 i의 x보다 크고,
            // i와 j의 x가 서로 같다면 j의 y가 더 크다.
            for(int i = 0;i<n-1;i++) {
                int cur_x = atoms[i].x;
                int cur_y = atoms[i].y;
                int cur_dir = atoms[i].dir;
                for(int j = i+1;j<n;j++) {
                    int time = -1;
                    int next_x = atoms[j].x;
                    int next_y = atoms[j].y;
                    int next_dir = atoms[j].dir;
                    // 서로 y값이 같다면, cur_x가 무조건 더 큼
                    if(cur_y==next_y && (cur_dir==LEFT&&next_dir==RIGHT)) time = (cur_x-next_x)/2;
                    // 서로 x값이 같다면, cur_y가 무조건 더 큼
                    else if(cur_x==next_x&&(cur_dir==DOWN&&next_dir==UP)) time = (cur_y-next_y)/2;
                    // 행과 열이 모두 다름
                    else {
                        if(Math.abs(cur_x-next_x)==Math.abs(cur_y-next_y)) {
                            if((cur_dir==DOWN&&next_dir==RIGHT&&next_y<cur_y)
                                    ||(cur_dir==UP&&next_dir==RIGHT&&cur_y<next_y)
                                    ||(cur_dir==LEFT&&next_dir==UP&&next_y<cur_y)
                                    ||(cur_dir==LEFT&&next_dir==DOWN&&next_y>cur_y)) {
                                time = Math.abs(cur_x-next_x);
                            }
                        }
                    }
                    if(time!=-1) {
                        conflict_map.computeIfAbsent(time, k-> new ArrayList<Atom[]>()).add(new Atom[] {atoms[i],atoms[j]});
                    }
                }
            }
            // 충돌 시점 기준으로 정렬
            // 충돌 시점이 같다고 무조건 서로 같은 충돌은 아님
            // 충돌은 결국에, 두 개가 서로 부딛히게 되면 인식을 함
            // 그리고 a에는 항상 cur_x가 더 큰 거, 혹은 cur_y가 더 큰 게 저장이 되어 있음
            // 충돌 시점이 같고, 그때의 a도 같으면 
            List<Integer> times = new ArrayList<>(conflict_map.keySet());
            Collections.sort(times);
            for (int t : times) {
                ArrayList<Atom[]> histories = conflict_map.get(t);
 
                HashSet<Atom> boom_list = new HashSet<>();
 
                for (Atom[] pair : histories) {
                    Atom a = pair[0];
                    Atom b = pair[1];
 
                    if (!a.is_deleted && !b.is_deleted) {
                        boom_list.add(a);
                        boom_list.add(b);
                    }
                }
 
                for (Atom a : boom_list) {
                    if (!a.is_deleted) {
                        a.is_deleted = true;
                        result += a.power;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
