import java.io.*;
import java.util.*;

class Main {
    static int[] arr, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr[1];
        if(n > 1) {
            dp[2] = arr[1] + arr[2];
        }

        int result = 0;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]);
            dp[i] = Math.max(dp[i], dp[i-1]);
        }

		br.close();
		System.out.print(dp[n]);
	}

}