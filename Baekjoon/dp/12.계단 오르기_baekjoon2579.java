import java.io.*;
import java.util.*;

class Main {
    static int[] stairs;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        stairs = new int[n];
        dp = new int[n][2];

        for (int i = 0; i <n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if(n == 1) {
            System.out.println(stairs[0]);
            return;
        }

        getMaxScore(0, 0, n);
        getMaxScore(1, 0, n);
        br.close();
        System.out.print(Math.max(dp[0][0],dp[1][0]));
    }

    private static int getMaxScore(int idx, int consecutive, int n) {
        if(dp[idx][consecutive] != 0) {
            return dp[idx][consecutive];
        }
        if(idx == n-1) {
            return stairs[n-1];
        }

        int maxVal = 0;
        if(consecutive == 0) {
            maxVal = Math.max(maxVal, getMaxScore(idx + 1, 1, n));
        }
        if(idx + 2 <= n-1) {
            maxVal = Math.max(maxVal, getMaxScore(idx + 2, 0, n));
        }
        if(maxVal == 0) {
            return 0;
        }
        return dp[idx][consecutive] = maxVal + stairs[idx];
    }
}
