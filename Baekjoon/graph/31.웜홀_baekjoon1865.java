import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int INT_MAX = 987654321;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			int[][] d = new int[n+1][n+1];
			for(int i=1; i<=n; i++) {
				Arrays.fill(d[i], INT_MAX);
				d[i][i] = 0;
			}

			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				if(d[s][e] > t) d[s][e] = t;
				if(d[e][s] > t) d[e][s] = t;
			}

			for(int i=0; i<w; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				if(d[s][e] > -t) d[s][e] = -t;
			}

			for(int k=1; k<=n; k++) {
				for(int i=1; i<=n; i++) {
					for(int j=1; j<=n; j++) {
						if(d[i][k] != INT_MAX && d[k][j] != INT_MAX) {
							d[i][j] = Math.min(d[i][k] + d[k][j], d[i][j]);
						}
					}
				}
			}

			boolean flag = false;
			for(int i=1; i<=n; i++) {
				if(d[i][i] < 0) {
					flag = true;
					sb.append("YES\n");
					break;
				}
			}
			if(!flag) sb.append("NO\n");
		}
		br.close();
		System.out.print(sb);
	}
}