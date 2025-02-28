import java.io.*;
import java.util.*;

class Main {
	static int n,m;
	static int[] d, dp;
	static List<int[]>[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		d = new int[n+1];
		dp = new int[n+1];
		edges = new List[n+1];

		for (int i = 0; i <= n; i++) {
			d[i] = -1;
			dp[i] = -1;
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			edges[y].add(new int[]{x, k});
			if(d[x] == -1) d[x] = 0;
			if(d[y] == -1) d[y] = 0;
			d[x]++;
		}

		for (int i = 1; i <= n; i++) {
			if(d[i] == -1 || d[i] > 0) continue;

			int totalCnt = dfs(i);
			sb.append(i).append(' ').append(totalCnt).append('\n');
		}

		br.close();
		System.out.print(sb);
	}

	static int dfs(int node) {
		if(edges[node].isEmpty()) {
			return 1;
		}
		if(dp[node] != -1) {
			return dp[node];
		}

		int totalCnt = 0;
		for (int i = 0; i < edges[node].size(); i++) {
			int nextNode = edges[node].get(i)[0];
			int needCnt = edges[node].get(i)[1];
			totalCnt += needCnt * dfs(nextNode);
		}
		return dp[node] = totalCnt;
	}
}