import java.io.*;
import java.util.*;

class Main {
	static int C, N;
	static int[][] promotionInfo;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		promotionInfo = new int[N][2];
		dp = new int[C+101];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customerCnt = Integer.parseInt(st.nextToken());
			promotionInfo[i][0] = cost;
			promotionInfo[i][1] = customerCnt;
		}

		for (int i = 1; i <= C+100; i++) {
			dp[i] = 123456789;
		}

		for (int i = 0; i < N; i++) {
			int cost = promotionInfo[i][0];
			int customerCnt = promotionInfo[i][1];
			for (int j = customerCnt; j < C + 101; j++) {
				dp[j] = Math.min(dp[j], cost + dp[j - customerCnt]);
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = C; i < C + 101; i++) {
			result = Math.min(result, dp[i]);
		}

		br.close();
		System.out.print(result);
	}

}