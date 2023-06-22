import java.io.*;
import java.util.*;

class Main {
    static int n;
    static int[][] arr;
    static int[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n][3];  //가로:0, 세로:1, 대각선:2

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();
		System.out.print(dfs(0,0,0));
	}

    private static int dfs(int x, int y, int dir) {
        if(dp[x][y][dir] != 0) {
            return dp[x][y][dir];
        }

        if((x == n-1 && y == n-2) || (x == n-2 && y == n-1) || (x == n-2 && y == n-2 && dir == 2)) {
            return 1;
        }

        int cnt = 0;
        if(dir == 0) { //가로
            if((y+2) < n && arr[x][y+2] == 0){
                cnt += dfs(x, y+1, 0);
            }
            if((x+1) < n && (y+2) < n && arr[x+1][y+1] == 0 && arr[x+1][y+2] == 0 && arr[x][y+2] == 0) {
                cnt += dfs(x, y+1, 2);
            }
        } else if(dir == 1) {  //세로
            if((x+2) < n && arr[x+2][y] == 0) {
                cnt += dfs(x+1, y, 1);
            }
            if((x+2) < n && (y+1) < n && arr[x+1][y+1] == 0 && arr[x+2][y] == 0 && arr[x+2][y+1] == 0) {
                cnt += dfs(x+1, y, 2);
            }
        } else if(dir == 2) {  //대각선
            if((x+1) < n && (y+2) < n && arr[x+1][y+2] == 0) {
                cnt += dfs(x+1, y+1, 0);
            }
            if((x+2) < n && (y+1) < n && arr[x+2][y+1] == 0) {
                cnt += dfs(x+1, y+1, 1);
            }
            if((x+2) < n && (y+2) < n && arr[x+1][y+2] == 0 && arr[x+2][y+1] == 0 && arr[x+2][y+2] == 0) {
                cnt += dfs(x+1, y+1, 2);
            }
        }
        return dp[x][y][dir] = cnt;
    }
}