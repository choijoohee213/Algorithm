import java.io.*;
import java.util.*;

class Main {
	List<Integer>[] roadList;
	int[] d;

	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		int[] answer = new int[sources.length];
		roadList = new List[n+1];
		d = new int[n+1];
		Queue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		for (int i = 0; i <= n; i++) {
			roadList[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE;
		}

		for (int[] road : roads) {
			roadList[road[0]].add(road[1]);
			roadList[road[1]].add(road[0]);
		}

		d[destination] = 0;
		pq.add(new int[]{destination, 0});

		while(!pq.isEmpty()) {
			int[] x = pq.poll();

			if(d[x[0]] < x[1]) continue;

			for (Integer next : roadList[x[0]]) {
				if(d[next] > (d[x[0]] + 1)) {
					pq.add(new int[]{next, d[x[0]] + 1});
					d[next] = d[x[0]] + 1;
				}
			}
		}

		for (int i = 0; i < sources.length; i++) {
			if(d[sources[i]] == Integer.MAX_VALUE) {
				answer[i] = -1;
				continue;
			}
			answer[i] = d[sources[i]];
		}

		return answer;
	}
}