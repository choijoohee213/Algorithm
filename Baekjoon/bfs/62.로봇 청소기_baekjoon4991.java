import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			char[][] map = new char[h][w];

			if(w == 0 && h == 0) {
				break;
			}

			Queue<int[]> q = new LinkedList<>();
			Map<Integer, Integer> dirty = new HashMap<>();
			int rx = 0, ry = 0, idx = 0;

			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();

				for (int j = 0; j < w; j++) {
					if(map[i][j] == 'o') {
						rx = i;
						ry = j;
						map[i][j] = '.';
					} else if(map[i][j] == '*') {
						dirty.put(i*w + j, idx++);
					}
				}
			}

			if(dirty.size() == 0) {
				sb.append(0).append('\n');
				continue;
			}

			int dirtyCnt = (int) Math.pow(2, dirty.size());
			int result = -1;
			int[][][] visited = new int[h][w][dirtyCnt];
			q.add(new int[]{rx, ry, 0});
			visited[rx][ry][0] = 1;

			loop :
			while(!q.isEmpty()) {
				int x = q.peek()[0];
				int y = q.peek()[1];
				int state = q.peek()[2];
				q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					int nState = state;

					if(nx<0 || nx>=h || ny<0 || ny>=w || map[nx][ny] == 'x') continue;
					if(map[nx][ny] == '*') {
						nState |= (1 << dirty.get(nx*w+ny));
					}
					if(nState == dirtyCnt - 1) {
						result = visited[x][y][state];
						break loop;
					}
					if(visited[nx][ny][nState] != 0) continue;
					q.add(new int[]{nx,ny,nState});
					visited[nx][ny][nState] = visited[x][y][state] + 1;
				}
			}
			sb.append(result).append('\n');
		}

		br.close();
		System.out.print(sb);
	}
}