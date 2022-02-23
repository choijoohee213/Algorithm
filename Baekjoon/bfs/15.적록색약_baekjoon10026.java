import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] map = new char[n][n];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		br.close();
		System.out.print(bfs_RGB(n, map) + " " + bfs_RB(n, map));
	}

	static int bfs_RGB(int n, char[][] map) {
		int cnt = 0;
		boolean[] visited = new boolean[n*n];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		for(int i=0; i<n*n; i++) {
			if(visited[i]) continue;
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			visited[i] = true;
			cnt++;

			while(!q.isEmpty()) {
				int x = q.peek() / n;
				int y = q.peek() % n;
				q.poll();

				for(int j=0; j<4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];

					if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx*n+ny] || map[nx][ny]!=map[x][y]) continue;
					visited[nx*n+ny] = true;
					q.offer(nx*n+ny);
				}
			}
		}
		return cnt;
	}

	static int bfs_RB(int n, char[][] map) {
		int cnt = 0;
		boolean[] visited = new boolean[n*n];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		for(int i=0; i<n*n; i++) {
			if(visited[i]) continue;
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			visited[i] = true;
			cnt++;

			while(!q.isEmpty()) {
				int x = q.peek() / n;
				int y = q.peek() % n;
				q.poll();

				for(int j=0; j<4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];

					if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx*n+ny]) continue;
					if(map[nx][ny] != map[x][y] && (map[nx][ny] == 'B' || map[x][y] == 'B')) continue;
					visited[nx*n+ny] = true;
					q.offer(nx*n+ny);
				}
			}
		}
		return cnt;
	}
}