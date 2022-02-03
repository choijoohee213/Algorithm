import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][] board = new int[n][m];
		int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < r; i++) {
			int x1 = 0, x2 = n, y1 = 0, y2 = m;
			do {
				int x = x1;
				int y = y1;
				int k = 0, prev = board[x][y];
				while (k < 4) {
					int nx = x + dx[k];
					int ny = y + dy[k];

					if (nx < x1 || nx >= x2 || ny < y1 || ny >= y2)
						k++;
					else {
						int tmp = prev;
						prev = board[nx][ny];
						board[nx][ny] = tmp;
						x = nx;
						y = ny;
					}
				}
				board[x1 + 1][y1] = prev;
				x1++;
				x2--;
				y1++;
				y2--;
			} while (x1 < x2 && y1 < y2);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
}