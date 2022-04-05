import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] visited = new int[d+1];  //초밥 종류
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int cnt = 0; //먹은 갯수
		for (int i = 0; i < k; i++) {
			if(visited[arr[i]]++ == 0) {
				cnt++;
			}
		}
		int result = cnt;
		for (int i = 1; i < n; i++) {
			if(result <= cnt) {
				if(visited[c] == 0) result = cnt + 1;
				else result = cnt;
			}
			visited[arr[i-1]]--; //맨 앞에 빼주기
			if(visited[arr[i-1]] == 0) cnt--;
			if(visited[arr[(i+k-1) % n]]++ == 0) cnt++;  //맨 뒤에 추가하기
		}
		br.close();
		System.out.print(result);
	}
}