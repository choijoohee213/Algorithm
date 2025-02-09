import java.io.*;
import java.util.*;

class Main {
	static boolean[] visited;
	static int[] peoplCnt;
	static int[][] dp;
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		peoplCnt = new int[N+1];
		dp = new int[N+1][2];
		edges = new List[N+1];
		visited = new boolean[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			peoplCnt[i] = Integer.parseInt(st.nextToken());
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[u].add(v);
			edges[v].add(u);
		}

		selectBestVillage(1);
		br.close();
		System.out.print(Math.max(dp[1][0], dp[1][1]));
	}

	static void selectBestVillage(int node) {
		visited[node] = true;
		dp[node][1] = peoplCnt[node];

		for (int i = 0; i < edges[node].size(); i++) {
			int next = edges[node].get(i);

			if(visited[next]) continue;
			selectBestVillage(next);

			dp[node][0] += Math.max(dp[next][0], dp[next][1]);
			dp[node][1] += dp[next][0];
		}
	}
}