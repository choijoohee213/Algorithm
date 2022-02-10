import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=n; i++) {
			q.add(i);
		}

		int cnt = 1;
		List<String> list = new ArrayList<>();
		while(!q.isEmpty()) {
			if(cnt == k) {
				cnt = 1;
				list.add(String.valueOf(q.poll()));
			}
			else {
				q.add(q.poll());
				cnt++;
			}
		}
		System.out.printf("<%s>", String.join(", ", list));
	}
}