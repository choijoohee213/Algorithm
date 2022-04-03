import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] d = new int[1001][3];
		int[] cost = new int[3];
		d[0][0] = 0;
		d[0][1] = 0;
		d[0][2] = 0;
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cost[0] = Integer.parseInt(st.nextToken());
			cost[1] = Integer.parseInt(st.nextToken());
			cost[2] = Integer.parseInt(st.nextToken());
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + cost[0];
			d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + cost[1];
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + cost[2];
		}
		br.close();
		System.out.print(Math.min(Math.min(d[n][0],d[n][1]), d[n][2]));
	}
}