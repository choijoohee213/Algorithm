import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int result = 0;
		Stack<Integer> st = new Stack<>();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < n; i++) {
			while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
				int idx = st.pop();
				int w = st.isEmpty()? i : i - st.peek() - 1;
				result = Math.max(w * arr[idx], result);
			}
			st.add(i);
		}
		while(!st.isEmpty()) {
			int idx = st.pop();
			int w = st.isEmpty()? n : n - st.peek() - 1;
			result = Math.max(w * arr[idx], result);
		}
		br.close();
		System.out.print(result);
	}
}