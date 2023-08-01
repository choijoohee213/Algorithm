import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] map = new char[n][m];
		boolean[][][][] visited = new boolean[n][m][4][4];
		Queue<int[]> q = new LinkedList<>();
		int[] cPos = new int[2];

		int idx = 0;
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 'S') {
					q.add(new int[]{i,j,-1, 0,0});
				} else if(map[i][j] == 'C') {
					cPos[idx++] = i*m+j;
				}
			}
		}

		while(!q.isEmpty()) {
			int x = q.peek()[0];
			int y = q.peek()[1];
			int dir = q.peek()[2];
			int cnt = q.peek()[3];
			int curPresent = q.peek()[4];
			q.poll();

			if(curPresent == 3) {
				System.out.print(cnt);
				return;
			}

			for (int i = 0; i < 4; i++) {
				if(dir == i) continue;
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nPresent = curPresent;

				if(nx<0 || nx>=n || ny<0 || ny>=m || map[nx][ny] == '#') continue;
				if(map[nx][ny] == 'C') {
					if(nx*m+ny == cPos[0]) {
						nPresent |= 1<<0;
					} else {
						nPresent |= 1<<1;
					}
				}
				if(visited[nx][ny][nPresent][i]) continue;
				visited[nx][ny][nPresent][i] = true;
				q.add(new int[]{nx,ny,i,cnt+1,nPresent});
			}
		}

		br.close();
		System.out.print(-1);
	}
}