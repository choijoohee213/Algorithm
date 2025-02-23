import java.io.*;
import java.util.*;

class Main {
	static int n, result = Integer.MAX_VALUE;
	static int[] paperCnt = {0, 5, 5, 5, 5, 5};
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = 10;
		arr = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		find(0, 0, 0);

		br.close();
		System.out.print(result == Integer.MAX_VALUE ? -1 : result);
	}

	static void find(int x, int y, int cnt) {
		if(x >= 9 && y > 9) {
			result = Math.min(result, cnt);
			return;
		}

		if(result <= cnt) {
			return;
		}

		if(y > 9) {
			find(x+1, 0, cnt);
			return;
		}

		if(arr[x][y] == 1) {
			for (int i = 5; i >= 1; i--) {
				if(isAttached(x, y, i) && paperCnt[i] > 0) {
					paperCnt[i]--;
					attach(x, y, i);
					find(x, y + i, cnt + 1);
					paperCnt[i]++;
					detach(x, y, i);
				}
			}
		} else {
			find(x, y+1, cnt);
		}
	}
	
	static boolean isAttached(int x, int y, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nx = x + i;
				int ny = y + j;

				if(nx >= n || ny >= n || arr[nx][ny] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	static void attach(int x, int y, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nx = x + i;
				int ny = y + j;

				arr[nx][ny] = 2;
			}
		}
	}

	static void detach(int x, int y, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nx = x + i;
				int ny = y + j;

				arr[nx][ny] = 1;
			}
		}
	}
}