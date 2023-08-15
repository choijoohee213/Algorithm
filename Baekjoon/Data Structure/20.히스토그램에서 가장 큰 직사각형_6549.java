import java.io.*;
import java.util.*;
import java.util.Stack;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());

			if(n == 0) {
				break;
			}

			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Stack<Integer> stack = new Stack<>();
			long result = 0;

			for (int i = 0; i < n; i++) {
				while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
					int idx = stack.pop();
					long w = stack.isEmpty()? i : i - 1 - stack.peek();
					result = Math.max(result, arr[idx] * w);
				}
				stack.add(i);
			}

			while(!stack.isEmpty()) {
				int idx = stack.pop();
				long w = stack.isEmpty()? n : n - 1 - stack.peek();
				result = Math.max(result, arr[idx] * w);
			}
			sb.append(result).append('\n');
		}

		br.close();
		System.out.print(sb);
	}
}