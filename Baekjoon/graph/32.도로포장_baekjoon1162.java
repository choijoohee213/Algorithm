import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<long[]> q = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[1], o2[1]);
            }
        });
        List<int[]>[] nodeList = new List[n+1];
        long[][] dp = new long[n+1][k+1];
        for (int i = 1; i <= n; i++) {
            nodeList[i] = new ArrayList<>();
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodeList[a].add(new int[]{b,c});
            nodeList[b].add(new int[]{a,c});
        }
        q.add(new long[]{1,0,0});  //node,time,k
        dp[1][0] = 0;
        long minTime = Long.MAX_VALUE;

        while(!q.isEmpty()) {
            int node = (int) q.peek()[0];
            long time = q.peek()[1];
            int k_cnt = (int) q.peek()[2];
            q.poll();

            if(node == n) {
                break;
            }
            if(dp[node][k_cnt] < time) continue;

            for (int[] next : nodeList[node]) {
                int nextNode = next[0], val = next[1];
                if(dp[nextNode][k_cnt] > time + val) {
                    q.add(new long[]{nextNode, time + val, k_cnt});
                    dp[nextNode][k_cnt] = time + val;
                }
                if(k_cnt < k && dp[nextNode][k_cnt+1] > time) {
                    q.add(new long[]{nextNode, time, k_cnt + 1});
                    dp[nextNode][k_cnt+1] = time;
                }
            }

        }

        br.close();
        for (int i = 1; i <= k; i++) {
            minTime = Math.min(minTime, dp[n][i]);
        }
        System.out.print(minTime);
    }
}
