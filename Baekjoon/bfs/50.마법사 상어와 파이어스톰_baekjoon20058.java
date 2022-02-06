import java.io.*;
import java.util.*;

class Main {
	static int size, q, l;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		size = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
		q = Integer.parseInt(st.nextToken());
		map = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < q; i++) {
			l = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
			if(l != 1) map = rotate();
			map = melt();
		}
		bfs();
	}

	static int[][] rotate() {
		//격자 부분 구분하여 시계방향 90도 돌리기
		int[][] result = new int[size][size];

		int x = 0;
		for (int j = 0; j < size/l; j++) {
			int y = 0;
			if(j != 0) x += l;
			for (int k = 0; k < size/l; k++) {
				if(k != 0) y += l;
				for(int a=0; a<l; a++) {
					for(int b = 0; b<l; b++){
						result[x+b][y+l-a-1] = map[x+a][y+b];
					}
				}
			}
		}
		return result;
	}

	static int[][] melt() {
		//얼음 녹음
		int[][] result = new int[size][size];

		for (int j = 0; j < size; j++) {
			for (int k = 0; k < size; k++) {
				int cnt = 0;
				for(int i=0; i<4; i++) {
					int nx = j + dx[i];
					int ny = k + dy[i];

					if(nx<0 || nx>=size || ny<0 || ny>=size) continue;
					if(map[nx][ny]>=1) cnt++;
				}
				result[j][k] = map[j][k];
				if (cnt < 3 && result[j][k] > 0) result[j][k]--;
			}
		}
		return result;
	}

	static void bfs() {
		//bfs로 얼음 합, 덩어리 확인
		int sum = 0, totalCnt = 0;
		boolean[][] visited = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += map[i][j];
				if (map[i][j] == 0 || visited[i][j])
					continue;
				int cnt = 1;
				Queue<Integer> queue = new LinkedList<>();
				queue.add(i * size + j);
				visited[i][j] = true;

				while (!queue.isEmpty()) {
					int x = queue.peek() / size;
					int y = queue.peek() % size;
					queue.poll();

					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if (nx < 0 || nx >= size || ny < 0 || ny >= size || map[nx][ny]==0 || visited[nx][ny]) continue;
						visited[nx][ny] = true;
						queue.add(nx * size + ny);
						cnt++;
					}
				}
				totalCnt = Math.max(totalCnt, cnt);
			}
		}
		System.out.println(sum + "\n" + totalCnt);
	}
}