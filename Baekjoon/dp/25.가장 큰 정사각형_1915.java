import java.io.*;
import java.util.*;

class Main {
	static int n,m;
	static int[][] map, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();

			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		int result = 0;
		int[] dx = {-1, -1, 0}, dy = {-1, 0, -1};
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 0) continue;

				int min = Integer.MAX_VALUE;
				for (int k = 0; k < 3; k++) {
					int prevX = i + dx[k];
					int prevY = j + dy[k];

					if(prevX<0 || prevX>=n || prevY<0 || prevY>=m) {
						min = Math.min(0, min);
					} else {
						min = Math.min(dp[prevX][prevY], min);
					}
				}

				dp[i][j] = min + 1;
				result = Math.max(result, dp[i][j]);
			}
		}

		br.close();
		System.out.print(result * result);
	}
}