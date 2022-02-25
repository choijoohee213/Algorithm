import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Main {
	static BigInteger[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		dp = new BigInteger[n+1][n+1];
		br.close();
		comb(n);
		System.out.print(dp[n][m]);
	}

	static void comb(int n) {
		for(int i=1; i<=n; i++) {
			for(int j=0; j<=i; j++) {
				if(j == 0 || i == j) dp[i][j] = new BigInteger("1");
				else dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
			}
		}
	}
}