import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		List<int[]>[] edgeList = new ArrayList[V+1];
		for(int i=1; i<=V; i++) edgeList[i] = new ArrayList<>();
		int[] d = new int[V+1];
		Arrays.fill(d, Integer.MAX_VALUE);
		Queue<int[]> q = new PriorityQueue<>(1, (int[] a, int[] b) -> a[0] < b[0] ? -1 : 1);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			edgeList[u].add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}

		q.offer(new int[]{0,K});
		d[K] = 0;
		while(!q.isEmpty()) {
			int w = q.peek()[0];
			int node = q.peek()[1];
			q.poll();

			if(d[node] < w) continue;
			for(int[] arr : edgeList[node]) {
				int newNode = arr[0];
				int newCost = w + arr[1];
				if(newCost < d[newNode]) {
					d[newNode] = newCost;
					q.offer(new int[]{newCost, newNode});
				}
			}
		}

		for(int i=1; i<=V; i++) {
			if(d[i] == Integer.MAX_VALUE) sb.append("INF");
			else sb.append(d[i]);
			sb.append("\n");
		}
		br.close();
		System.out.print(sb);
	}
}