import java.util.*;
import java.io.*;

public class Main_bj_2961_도영이가만든맛있는음식_대전_5반_최주희 {
	static int[] arr1, arr2;
	static int n, result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr1 = new int[n];
		arr2 = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr1[i] = Integer.parseInt(st.nextToken());
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		comb(0, 1, 0);
		System.out.print(result);
	}

	static void comb(int cnt, int sum1, int sum2) {
		if (cnt == n) {
			if(sum1 != 1 && sum2 != 0)
				result = Math.min(result, Math.abs(sum1 - sum2));
			return;
		}
		comb(cnt + 1, sum1 * arr1[cnt], sum2 + arr2[cnt]);
		comb(cnt + 1, sum1, sum2);
	}
}
