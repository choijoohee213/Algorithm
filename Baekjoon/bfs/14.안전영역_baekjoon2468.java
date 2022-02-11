import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), cnt, height = 1, result = 0;
		int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
		int[][] map = new int[n][n];
		boolean[][] visited;
		Queue<Integer> q;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				height = Math.max(height, map[i][j]);
			}
		}

		for(int k=0; k<=height; k++) {
			cnt = 0;
			visited = new boolean[n][n];
			q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] || map[i][j] <= k)
						continue;
					cnt++;
					q.offer(i * n + j);
					visited[i][j] = true;

					while (!q.isEmpty()) {
						int x = q.peek() / n;
						int y = q.poll() % n;

						for (int dir = 0; dir < 4; dir++) {
							int nx = x + dx[dir];
							int ny = y + dy[dir];

							if (nx < 0 || nx >= n || ny < 0 || ny >= n
								|| visited[nx][ny] || map[nx][ny] <= k)
								continue;
							q.offer(nx * n + ny);
							visited[nx][ny] = true;
						}
					}
				}
			}
			result = Math.max(result, cnt);
		}
		System.out.print(result);
	}
}