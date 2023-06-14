import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        int[] arr = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if(list.isEmpty()) {
                list.add(arr[i]);
                dp[i] = 1;
            } else {
                if(list.get(list.size()-1) < arr[i]) {
                    list.add(arr[i]);
                    dp[i] = list.size();
                } else {
                    int left = 0, right = list.size()-1, mid = 0;
                    while(left < right) {
                        mid = (left + right) / 2;
                        if(arr[i] <= list.get(mid)) {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    }
                    list.set(right, arr[i]);
                    dp[i] = right + 1;
                }
            }
        }

		br.close();
		System.out.print(list.size());
	}
}