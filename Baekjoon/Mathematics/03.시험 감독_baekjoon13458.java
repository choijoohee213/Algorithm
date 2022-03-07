import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[n+1];
		long[] d = new long[1000001];
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		long cnt = 0;
		for(int i=1; i<=n; i++) {
			int x = arr[i];
			if(d[x] != 0) {
				cnt += d[x];
				continue;
			}
			boolean flag = false;
			long total = 0;
			while(x>0) {
				if(!flag) {
					x-=b;
					total++;
					flag = true;
				}
				else {
					x-=c;
					total++;
				}
			}
			cnt += total;
			d[arr[i]] = total;
		}
		br.close();
		System.out.print(cnt);
	}
}