import java.io.*;
import java.util.*;

public class Main {
	static int l,c;
	static char[] arr;
	static boolean[] visited;
	static Set<Character> set = new HashSet<Character>(){{
		add('a'); add('e'); add('i'); add('o'); add('u');
	}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new char[c];
		visited = new boolean[c];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<c; i++)  arr[i] = st.nextToken().charAt(0);

		Arrays.sort(arr);
		permutation(0, "", 0, 0);
		br.close();
	}

	static void permutation(int idx, String str, int cnt1, int cnt2) {
		if(str.length() == l && cnt1>=1 && cnt2>=2) {
			System.out.println(str);
			return;
		}

		for(int i=idx; i<c ;i++) {
			if(visited[i]) continue;
			visited[i] = true;
			boolean flag = set.contains(arr[i]);
			permutation(i+1, str + arr[i], flag?cnt1+1:cnt1, flag?cnt2:cnt2+1);
			visited[i] = false;
		}
	}
}