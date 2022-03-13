import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 1;
		arr[x][y] = 2;

		while (true) {
			int nx,ny;
			if(arr[x+1][y] != 0 && arr[x-1][y] != 0 && arr[x][y+1]!=0 && arr[x][y-1]!=0){
				nx = x + dx[(d + 2) % 4];
				ny = y + dy[(d + 2) % 4];
				if (arr[nx][ny] != 1) {
					x = nx;
					y = ny;
					continue;
				} else {
					//결과 출력
					System.out.print(cnt);
					br.close();
					return;
				}
			}
			d = (d + 3) % 4;
			nx = x + dx[d];
			ny = y + dy[d];

			if (arr[nx][ny] == 0) {
				x = nx;
				y = ny;
				arr[nx][ny] = 2;
				cnt++;
			}
		}
	}
}