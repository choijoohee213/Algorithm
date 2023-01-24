import java.io.*;
import java.util.*;

class Main {
    static int n, INF = 987654321;
    static int[][] map, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int max = 1 << n;
        map = new int[n][n];
        dp = new int[n][max];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dfs(0, 1);
        
		br.close();
		System.out.print(result);
	}

    private static int dfs(int node, int visited) {
        if(visited == (1 << n) - 1) {
            if(map[node][0] == 0) return INF;
            return map[node][0];
        }

        if(dp[node][visited] != 0) {
            return dp[node][visited];
        }
        dp[node][visited] = INF;

        for (int i = 0; i < n; i++) {
            int next = visited | (1 << i);
            if((visited & (1 << i)) != 0 || map[node][i] == 0) {
                continue;
            }
            dp[node][visited] = Math.min(dp[node][visited], dfs(i, next) + map[node][i]);
        }

        return dp[node][visited];
    }
}
