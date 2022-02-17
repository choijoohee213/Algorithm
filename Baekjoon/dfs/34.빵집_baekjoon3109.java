import java.util.*;
import java.io.*;

public class Main {
	static int r,c;
	static char[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for(int i=0; i<r; i++) {
			String s = br.readLine();
			for(int j=0; j<c; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		int cnt = 0;
		for(int i=0; i<r; i++) {
			if(findPath(i,0)) cnt++;
		}
		System.out.print(cnt);
	}

	static boolean findPath(int x, int y) {
		if(y == c-1) {
			return true;
		}
		boolean flag = false;
		for(int i=-1; i<=1; i++) {
			if(x+i<0 || x+i>=r || y+1<0 || y+1>=c || map[x+i][y+1] != '.') continue;
			if(findPath(x+i, y+1)) {
				flag = true;
				break;
			}
		}
		map[x][y] = 'x';
		return flag;
	}
}
