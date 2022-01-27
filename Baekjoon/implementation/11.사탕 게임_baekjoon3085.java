import java.io.*;
import java.util.*;

class Main {
	private static char[][] board;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int result = 0;
		board = new char[n][n];
		for(int i=0; i<n; i++) {
			board[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(j+1 < n) {
					char tmp = board[i][j];
					board[i][j] = board[i][j+1];
					board[i][j+1] = tmp;
					result = Math.max(result, checkCandy());
					tmp = board[i][j];
					board[i][j] = board[i][j+1];
					board[i][j+1] = tmp;
				}

				if(i+1 < n) {
					char tmp = board[i][j];
					board[i][j] = board[i+1][j];
					board[i+1][j] = tmp;
					result = Math.max(result, checkCandy());
					tmp = board[i][j];
					board[i][j] = board[i+1][j];
					board[i+1][j] = tmp;
				}
			}
		}
		System.out.println(result);
	}

	private static int checkCandy() {
		int cnt = 1;
		for(int i=0; i<n; i++) {
			int sum = 1;
			for(int j=0; j<n-1; j++) {
				if(board[i][j] == board[i][j+1]) {
					sum++;
				} else {
					sum = 1;
				}
				cnt = Math.max(cnt, sum);
			}

			sum = 1;
			for(int j=0; j<n-1; j++) {
				if(board[j][i] == board[j+1][i]) {
					sum++;
				} else {
					sum = 1;
				}
				cnt = Math.max(cnt, sum);
			}
		}
		return cnt;
	}
}