import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][3];
		int[] maxDp = new int[3];
		int[] minDp = new int[3];

		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());

			if(i == 0) {
				maxDp[0] = minDp[0] = arr[i][0];
				maxDp[1] = minDp[1] = arr[i][1];
				maxDp[2] = minDp[2] = arr[i][2];
			} else {
				int tmp0 = maxDp[0], tmp1 = maxDp[1];
				maxDp[0] = Math.max(maxDp[0], maxDp[1]) + arr[i][0];
				maxDp[1] = Math.max(maxDp[2], Math.max(tmp0, maxDp[1])) + arr[i][1];
				maxDp[2] = Math.max(tmp1, maxDp[2]) + arr[i][2];

				tmp0 = minDp[0];
				tmp1 = minDp[1];
				minDp[0] = Math.min(minDp[0], minDp[1]) + arr[i][0];
				minDp[1] = Math.min(minDp[2], Math.min(tmp0, minDp[1])) + arr[i][1];
				minDp[2] = Math.min(tmp1, minDp[2]) + arr[i][2];
			}
		}
		br.close();
		System.out.print(Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]))
			+ " " + Math.min(minDp[0], Math.min(minDp[1], minDp[2])));
	}
}