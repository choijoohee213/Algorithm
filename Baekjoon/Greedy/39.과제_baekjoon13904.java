import java.io.*;
import java.util.*;

public class Main {
    static class Homework implements Comparable<Homework>{
        int d,w;

        public Homework(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Homework o) {
            if(this.w == o.w) {
                return Integer.compare(o.d, this.d);
            }
            return Integer.compare(o.w, this.w);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()), m = 0;
        List<Homework> homeworks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            m = Math.max(d,m);
            homeworks.add(new Homework(d,w));
        }
        homeworks.sort(null);
        boolean[] day = new boolean[m+1];
        int answer = 0;

        for (Homework homework : homeworks) {
            for (int i = homework.d; i >= 1; i--) {
                if(!day[i]) {
                    day[i] = true;
                    answer += homework.w;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
