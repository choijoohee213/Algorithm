import java.util.*;
import java.io.*;

class Main {
	static List<Integer>[] edges;
	static int[][] dp;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		edges = new List[N+1];
		for (int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		dp = new int[N+1][2];
		visited = new boolean[N+1];

		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			edges[u].add(v);
			edges[v].add(u);
		}

		find(1);
		System.out.print(Math.min(dp[1][0], dp[1][1]));
	}

	static void find(int node) {
		visited[node] = true;
		dp[node][0] = 1;

		for (int i = 0; i < edges[node].size(); i++) {
			int next = edges[node].get(i);
			if(visited[next]) continue;
			find(next);
			dp[node][1] += dp[next][0];
			dp[node][0] += Math.min(dp[next][0], dp[next][1]);
		}
	}
}