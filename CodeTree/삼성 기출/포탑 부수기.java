import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][2];
        int[][] d = new int[n][k+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if(arr[i][0] > j) {
                    if(i > 0) {
                        d[i][j] = d[i-1][j];
                    }
                } else {
                    if(i == 0) {
                        d[i][j] = arr[i][1];
                    } else {
                        d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - arr[i][0]] + arr[i][1]);
                    }
                }
            }
        }

        br.close();
		System.out.print(d[n-1][k]);
	}
}
