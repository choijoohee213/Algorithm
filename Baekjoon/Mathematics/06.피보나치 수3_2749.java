import java.io.*;
import java.util.*;

class Main {
    static long[] dp;
    static int n, p = 1000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pisano = 1500000;
        n = (int) (Long.parseLong(br.readLine()) % pisano);
        dp = new long[n+1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % p;
        }

		br.close();
		System.out.print(dp[n]);
	}
}