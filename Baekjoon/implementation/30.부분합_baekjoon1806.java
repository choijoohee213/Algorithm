import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0, right = 0, sum = 0, result = Integer.MAX_VALUE;
		while(true) {
			if(sum >= s) {
				result = Math.min(result, right-left);
				sum -= arr[left++];
				if(left == n) break;
			}
			else {
				if(right == n) break;
				sum += arr[right++];
			}
		}
		br.close();
		System.out.print(result  == Integer.MAX_VALUE? 0 : result);
	}
}