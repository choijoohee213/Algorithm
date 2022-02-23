import java.io.*;
import java.util.*;

class Main {
	static int n,m, result = Integer.MAX_VALUE;
	static List<Integer> chicken = new ArrayList<>();
	static List<Integer> house = new ArrayList<>();
	static List<Integer> picked;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		picked = new ArrayList<>(m);
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x == 1) {
					house.add(i*n+j);
				} else if(x == 2) {
					chicken.add(i*n+j);
				}
			}
		}
		dfs(0, 0);
		br.close();
		System.out.print(result);
	}

	private static void dfs(int idx, int cnt) {
		if(cnt == m) {
			int sum = 0;
			for(int i : house) {
				int min = Integer.MAX_VALUE;
				for(int j : picked) {
					min = Math.min(min, Math.abs(i/n-j/n) + Math.abs(i%n-j%n));
				}
				sum += min;
			}
			result = Math.min(result, sum);
			return;
		}

		for(int i=idx; i< chicken.size(); i++) {
			picked.add(chicken.get(i));
			dfs(i+1, cnt+1);
			picked.remove(chicken.get(i));
		}
	}
}