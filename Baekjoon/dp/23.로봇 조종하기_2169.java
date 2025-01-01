import java.io.*;
import java.util.*;

class Main {
	static int N,M;
	static boolean[][] visited;
	static int[][] value;
	static int[][][] dp;
	static int dx[] = {0,1,0}, dy[] = {-1,0,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		value = new int[N][M];
		dp = new int[N][M][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				value[i][j] = Integer.parseInt(st.nextToken());

				for (int k = 0; k < 3; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		visited = new boolean[N][M];
		visited[0][0] = true;
		int result = dfs(0,0, value[0][0], 0);

		br.close();
		System.out.print(result);
	}

	static int dfs(int x, int y, int sum, int dir) {
		if(x == N-1 && y == M-1) {
			return sum;
		}

		if(dp[x][y][dir] != Integer.MAX_VALUE) {
			return sum + dp[x][y][dir];
		}

		int maxVal = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0 || nx>=N || ny<0 || ny>=M || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			maxVal = Math.max(maxVal, dfs(nx, ny, sum + value[nx][ny], i));
			visited[nx][ny] = false;
		}

		if(maxVal != Integer.MIN_VALUE) {
			dp[x][y][dir] = maxVal - sum;
		}
		return maxVal;
	}
}