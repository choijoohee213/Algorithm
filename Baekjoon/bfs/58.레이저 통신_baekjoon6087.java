import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int endX = 0, endY = 0;
		char[][] map = new char[w][h];
		int[][][] visited = new int[w][h][4];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				for (int k = 0; k < 4; k++) {
					visited[i][j][k] = -1;
				}
			}
		}

		Queue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[3], o2[3]));

		for (int i = 0; i < w; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < h; j++) {
				if(q.isEmpty() && map[i][j] == 'C') {
					q.offer(new int[]{i,j,-1,0});  //x,y,이전방향,거울 개수
					visited[i][j][0] = 0;
					visited[i][j][1] = 0;
					visited[i][j][2] = 0;
					visited[i][j][3] = 0;
				} else if(!q.isEmpty() && map[i][j] == 'C') {
					endX = i;
					endY = j;
				}
			}
		}

		int result = Integer.MAX_VALUE;
		int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};  //하우상좌
		while(!q.isEmpty()) {
			int[] x = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];
				int mirror = x[3];

				if(nx<0 || nx>=w || ny<0 || ny>=h || map[nx][ny] == '*') continue;
				if(x[2] >=0 && x[2] != i) {
					mirror++; //수직 회전
				}
				if(nx == endX && ny == endY) {
					result = Math.min(result, mirror);
				}

				if(visited[nx][ny][i] == -1 || visited[nx][ny][i] > mirror) {
					visited[nx][ny][i] = mirror;
					q.offer(new int[]{nx,ny,i,mirror});
				}
			}
		}
		br.close();
		System.out.print(result);
	}
}