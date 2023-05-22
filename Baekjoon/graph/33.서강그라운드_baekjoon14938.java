import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] items = new int[n];
        List<int[]>[] edges = new List[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int l = Integer.parseInt(st.nextToken());

            edges[a].add(new int[]{b, l});
            edges[b].add(new int[]{a, l});
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            //노드, 수색범위, 아이템 수
            int sum = 0;
            Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
            int[] d = new int[n];
            for (int j = 0; j < n; j++) {
                d[j] = Integer.MAX_VALUE;
            }

            d[i] = 0;
            pq.add(new int[]{i, 0});
            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int x = cur[0];
                int dist = cur[1];

                for (int j = 0; j < edges[x].size(); j++) {
                    int[] next = edges[x].get(j);
                    if((dist + next[1]) > m || d[next[0]] < (dist + next[1])) continue;
                    d[next[0]] = dist + next[1];
                    pq.add(new int[]{next[0], dist + next[1]});
                }
            }
            for (int j = 0; j < n; j++) {
                if(d[j] != Integer.MAX_VALUE) {
                    sum += items[j];
                }
            }
            result = Math.max(sum, result);
        }
		br.close();
		System.out.print(result);
	}
}