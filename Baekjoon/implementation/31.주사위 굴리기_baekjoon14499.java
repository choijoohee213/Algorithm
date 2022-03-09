import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};
		int[][] arr = new int[n][m];
		int[] dice = new int[6];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int cmd = Integer.parseInt(st.nextToken()) - 1;

			int nx = x + dx[cmd];
			int ny = y + dy[cmd];
			if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

			int[] tmp = dice.clone();
			if(cmd == 0) { //동
				dice[2] = tmp[0];
				dice[5] = tmp[2];
				dice[0] = tmp[3];
				dice[3] = tmp[5];
			}
			else if(cmd == 1) {  //서
				dice[3] = tmp[0];
				dice[2] = tmp[5];
				dice[5] = tmp[3];
				dice[0] = tmp[2];
			}
			else if(cmd == 2) {  //북
				dice[1] = tmp[0];
				dice[5] = tmp[1];
				dice[4] = tmp[5];
				dice[0] = tmp[4];
			}
			else if(cmd == 3) {  //남
				dice[1] = tmp[5];
				dice[5] = tmp[4];
				dice[4] = tmp[0];
				dice[0] = tmp[1];
			}

			if(arr[nx][ny] == 0) arr[nx][ny] = dice[5];
			else {
				dice[5] = arr[nx][ny];
				arr[nx][ny] = 0;
			}
			sb.append(dice[0]).append('\n');
			x = nx;
			y = ny;
		}
		br.close();
		System.out.print(sb);
	}
}