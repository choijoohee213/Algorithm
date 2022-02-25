import java.io.*;
import java.util.*;

class Main {
	static int r, c, t;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		int idx = 0;
		int[][] airCleaner = new int[2][2];
		int[][] arr = new int[r][c];

		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1){
					airCleaner[idx][0] = i;
					airCleaner[idx++][1] = j;
				}
			}
		}
		while(t-- > 0) {
			arr = bfs(arr);
			airClean(arr, airCleaner);
		}

		int sum = 0;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(arr[i][j]>0) sum += arr[i][j];
			}
		}
		br.close();
		System.out.print(sum);
	}

	private static void airClean(int[][] arr, int[][] airCleaner) {
		int[][] dirX = {{0,-1,0,1}, {0,1,0,-1}};
		int[][] dirY = {{1,0,-1,0}, {1,0,-1,0}};
		int[] endX = {0, r-1};

		for(int i=0; i<2; i++) {
			int topX = airCleaner[i][0];
			int topY = airCleaner[i][1];
			int x = topX, y = topY, prev = 0;
			int dir = 0;
			while(true) {
				int tmp = prev;
				x += dirX[i][dir];
				y += dirY[i][dir];
				if(x == topX && y == topY) break;
				prev = arr[x][y];
				arr[x][y] = tmp;
				if((x == topX && y == c-1) || (x == endX[i] && y == c-1) ||(x == endX[i] && y == 0) )
					dir = (dir+1) % 4;
			}
			arr[topX][topY] = -1;
		}
	}

	private static int[][] bfs(int[][] arr) {
		int[][] newArr = new int[r][c];

		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(arr[i][j] <= 0) continue;
				int dust = arr[i][j] / 5;
				newArr[i][j] += arr[i][j];

				for(int k=0; k<4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if(nx<0 || nx>=r || ny<0 || ny>=c || arr[nx][ny] == -1) continue;
					newArr[i][j] -= dust;
					newArr[nx][ny] += dust;
				}
			}
		}
		return newArr;
	}
}