import java.io.*;
import java.util.*;

class Main {
	static int n, result = Integer.MAX_VALUE;
	static int[] arr;
	static boolean[][] linked;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		linked = new boolean[n+1][n+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				int x = Integer.parseInt(st.nextToken());
				linked[i][x] = true;
				linked[x][i] = true;
			}
		}
		comb(1, new boolean[n+1]);
		br.close();
		System.out.print(result == Integer.MAX_VALUE? -1 : result);
	}

	static void comb(int cnt, boolean[] team) {
		if(cnt == n+1) {
			bfs(team);
			return;
		}
		team[cnt] = true;
		comb(cnt+1, team);
		team[cnt] = false;
		comb(cnt+1, team);
	}

	static void bfs(boolean[] team) {
		int cnt =0;
		boolean[] visited = new boolean[n+1];

		for (int i = 1; i <= n; i++) {
			if(visited[i]) continue;
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			visited[i] = true;
			cnt++;

			while(!q.isEmpty()) {
				int x = q.poll();

				for (int j = 1; j <= n; j++) {
					if(x == j || !linked[x][j]) continue;
					if(team[x] == team[j] && !visited[j]) {
						q.offer(j);
						visited[j] = true;
					}
				}
			}
		}
		if(cnt == 2) {
			int aSum = 0, bSum = 0;
			for (int i = 1; i <= n; i++) {
				if(team[i]) aSum += arr[i];
				else bSum += arr[i];
			}
			result = Math.min(result, Math.abs(aSum - bSum));
		}
	}
}