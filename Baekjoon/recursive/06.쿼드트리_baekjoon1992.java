import java.util.*;
import java.io.*;

public class Main_bj_1992_쿼드트리_대전_5반_최주희 {
	static int n;
	static char[][] arr;
	static StringBuilder result = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];

		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		recursive(n, 0, 0);
		System.out.print(result);
	}

	static void recursive(int n, int x, int y) {
		char c = arr[x][y];
		boolean flag = true;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(c != arr[x+i][y+j]) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				break;
			}
		}

		if(!flag) {
			int half = n/2;
			result.append("(");
			recursive(half, x, y);
			recursive(half, x, y+half);
			recursive(half, x+half, y);
			recursive(half, x+half, y+half);
			result.append(")");
		} else {
			result.append(c);
		}
 	}
}
