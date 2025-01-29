import java.io.*;
import java.util.*;

class Main {
	static int n,m,groupCnt = 0;
	static int[][] map, group;
	static int[] dx = {1,0,-1,0}, dy = {0, 1, 0, -1};
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		group = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				char c = str.charAt(j);
				int index = 0;
				if(c == 'S') index = 0;
				else if(c == 'E') index = 1;
				else if(c == 'N') index = 2;
				else index = 3;
				map[i][j] = index;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(group[i][j] > 0) continue;
				dfs(i,j);
			}
		}
		br.close();
		System.out.print(groupCnt);
	}

	static int dfs(int x, int y) {
		if(x<0 || x>=n || y<0 || y>=m) {
			return ++groupCnt;
		}

		if(visited[x][y]) {
			if(group[x][y] != 0) {
				return group[x][y];
			}
			return ++groupCnt;
		}

		visited[x][y] = true;

		int nx = x + dx[map[x][y]];
		int ny = y + dy[map[x][y]];
		int groupNum = dfs(nx, ny);

		return group[x][y] = groupNum;
	}
}