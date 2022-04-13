import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T,n,startX,startY,endX,endY;

		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			boolean[] visited = new boolean[100];

			int[][] conv = new int[n][2];
			Queue<int[]> q = new ArrayDeque<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				conv[i][0] = Integer.parseInt(st.nextToken());
				conv[i][1] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());

			boolean stop = false;
			q.offer(new int[]{startX, startY});

			while(!q.isEmpty()) {
				int[] x = q.poll();

				if(Math.abs(x[0] - endX) + Math.abs(x[1] - endY) <= 1000) {
					stop = true;
					break;
				}

				for (int i = 0; i < n; i++) {
					if(!visited[i] && Math.abs(x[0] - conv[i][0]) + Math.abs(x[1] - conv[i][1]) <= 1000) {
						q.offer(new int[]{conv[i][0], conv[i][1]});
						visited[i] = true;
					}
				}
			}
			if(stop) sb.append("happy\n");
			else sb.append("sad\n");
		}
		br.close();
		System.out.print(sb);
	}
}