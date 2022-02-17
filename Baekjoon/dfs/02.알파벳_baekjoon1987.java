import java.io.*;
import java.util.*;

class Main {
	static int r,c, result = 0;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		char[][] map = new char[r][c];
		boolean[] visited = new boolean[26];
		for(int i=0; i<r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited[map[0][0]-'A'] = true;
		dfs(0, 0, 1, map, visited);
		br.close();
		System.out.print(result);
	}

	static void dfs(int x, int y, int cnt, char[][] map, boolean[] visited){
		boolean end = true;

		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx<0 || nx>=r || ny<0 || ny>=c || visited[map[nx][ny]-'A']) continue;
			visited[map[nx][ny]-'A'] = true;
			end = false;
			dfs(nx, ny, cnt+1, map, visited);
			visited[map[nx][ny]-'A'] = false;
		}

		if(end) result = Math.max(result, cnt);
	}
}