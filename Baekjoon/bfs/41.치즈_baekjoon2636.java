import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int time = 0, cheese = 0;
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[m][n];
		int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		while (true) {
			int[][] visited = new int[m][n];
			boolean melted = true;
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {0, 0});
			visited[0][0] = 1;

			while (!q.isEmpty()) {
				int[] x = q.poll();

				for (int k = 0; k < 4; k++) {
					int nx = x[0] + dx[k];
					int ny = x[1] + dy[k];

					if (nx < 0 || nx >= m || ny < 0 || ny >= n)
						continue;
					if (visited[nx][ny] == 1)
						continue;
					if (arr[nx][ny] == 1) {  //치즈
						visited[nx][ny] = 2;
						melted = false;  //다녹은거 아님
						continue;
					}
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = 1;
				}
			}
			if (melted) {
				System.out.print(time + "\n" + cheese);
				br.close();
				return;
			}

			int cnt = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(visited[i][j] == 2){  //치즈 갯수 세기(모두 녹기 한시간 전이면 치즈가 모두 2값을 가짐)
 						cnt++;
						arr[i][j] = 0;
					}
				}
			}
			cheese = cnt;
			time++;
		}
	}
}