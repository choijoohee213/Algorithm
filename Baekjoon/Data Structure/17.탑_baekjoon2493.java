import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), cnt = 0;
		Stack<Point> st = new Stack<>();
		StringTokenizer stoken = new StringTokenizer(br.readLine(), " ");

		for(int i=0; i<n; i++) {
			int x = Integer.valueOf(stoken.nextToken());
			int left = 0;
			while(!st.isEmpty()) {
				if(st.peek().x <= x) {
					st.pop();
				}
				else {
					left = st.peek().y;
					break;
				}
			}
			st.push(new Point(x, i+1));
			System.out.print(left + " ");
		}
	}
}