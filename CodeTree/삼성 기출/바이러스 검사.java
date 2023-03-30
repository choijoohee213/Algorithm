import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] clients = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            clients[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int leader = Integer.parseInt(st.nextToken());
        int member = Integer.parseInt(st.nextToken());
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            long num = clients[i];
            num -= leader;
            cnt++;
            if(num <= 0) {
                continue;
            }
            cnt += (long)Math.ceil(num / (double)member);
        }
        System.out.println(cnt);
    }
}
