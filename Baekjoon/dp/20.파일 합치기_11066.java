import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int k = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[k+1];
			int[] sum = new int[k+1];
			int[][] dp = new int[k+1][k+1];

			for (int i = 1; i <= k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + arr[i];
			}

			for (int i = 1; i <= k; i++) {
				for (int from = 1; from + i <= k; from++) {
					int to = from + i;
					dp[from][to] = Integer.MAX_VALUE;
					for (int j = from; j < to; j++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][j] + dp[j+1][to] + sum[to] - sum[from-1]);
					}
				}
			}
			System.out.println(dp[1][k]);
		}

		br.close();
	}
}