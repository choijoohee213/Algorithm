import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] d = new int[n+1][n+1];
		int[][] origin = new int[n+1][n+1];

		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				d[i][j] = cost;
				origin[i][j] = cost;
			}
		}

		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				for(int k=1; k<=n; k++) {
					if(i == j || i == k || j == k) continue;

					if(d[i][j] > d[i][k] + d[k][j])  {
						System.out.println(-1);
						return;
					}

					if(d[i][j] == d[i][k] + d[k][j]) {
						origin[i][j] = 0;
					}
				}
			}
		}

		int result = 0;
		for(int i=1; i<=n; i++) {
			for(int j=i+1; j<=n; j++) {
				result += origin[i][j];
			}
		}
		System.out.print(result);

	}
}