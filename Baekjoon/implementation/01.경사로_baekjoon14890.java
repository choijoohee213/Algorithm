import java.io.*;
import java.util.*;

/**
 * 1. 경사로 높이는 항상 1, 길이가 x이상이어야함
 * 2. 열과 행을 다돌면서 불가능한 부분이있다면 다음으로 넘어가자.
 * 3. 경사로가 여러개 겹쳐도 안됨
 */

class Main {
	static int n,x;
	static int[][] arr;
	static boolean[][] slope;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		int cnt = 0;
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//행 체크
		loop : for (int i = 0; i < n; i++) {
			boolean flag = true;
			slope = new boolean[n][n];

			for (int j = 1; j < n; j++) {
				int diff = Math.abs(arr[i][j-1] - arr[i][j]);
				if(diff > 1) flag = false;
				else if(diff == 1) {
					if(arr[i][j-1] > arr[i][j]) flag = checkSlope(i,j-1,0,1);
					else flag = checkSlope(i,j,0,-1);
				}
				if(!flag) continue loop;
			}
			cnt++;
		}

		//열 체크
		loop : for (int i = 0; i < n; i++) {
			boolean flag = true;
			slope = new boolean[n][n];

			for (int j = 1; j < n; j++) {
				int diff = Math.abs(arr[j-1][i] - arr[j][i]);
				if(diff > 1) flag = false;
				else if(diff == 1) {
					if(arr[j-1][i] > arr[j][i]) flag = checkSlope(j-1,i,1,0);
					else flag = checkSlope(j,i,-1,0);
				}
				if(!flag) continue loop;
			}
			cnt++;
		}
		br.close();
		System.out.print(cnt);
	}

	static boolean checkSlope(int curX, int curY, int dirx, int diry) {
		int val = arr[curX+dirx][curY+diry];

		for (int i = 0; i < x; i++) {
			curX += dirx;
			curY += diry;
			if(curX<0 || curX>=n || curY<0 || curY>=n) return false;
			if(val != arr[curX][curY]) return false;
			if(slope[curX][curY]) return false;
			slope[curX][curY] = true;
		}
		return true;
	}
}