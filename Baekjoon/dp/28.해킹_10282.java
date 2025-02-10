import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			List<int[]>[] edges = new List[n+1];
			int[] dp = new int[n+1];

			for (int i = 0; i <= n; i++) {
				edges[i] = new ArrayList<>();
				dp[i] = 987654321;
			}

			for (int j = 0; j < d; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());

				edges[b].add(new int[]{a, s});
			}

			Set<Integer> visitedNode = new HashSet<>();
			Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			pq.add(new int[]{c, 0});
			dp[c] = 0;

			while(!pq.isEmpty()) {
				int node = pq.peek()[0];
				int curSecond = pq.peek()[1];
				pq.poll();
				visitedNode.add(node);

				for (int i = 0; i < edges[node].size(); i++) {
					int nextNode = edges[node].get(i)[0];
					int nextSecond = edges[node].get(i)[1] + curSecond;

					if(dp[nextNode] > nextSecond) {
						dp[nextNode] = nextSecond;
						pq.add(new int[]{nextNode, nextSecond});
					}
				}
			}

			int resultCnt = visitedNode.size();
			int resultSecond = 0;
			for (int i = 0; i <= n; i++) {
				if(dp[i] == 987654321) continue;
				resultSecond = Math.max(resultSecond, dp[i]);
			}
			sb.append(resultCnt).append(' ').append(resultSecond).append('\n');
		}

		br.close();
		System.out.print(sb);
	}
}