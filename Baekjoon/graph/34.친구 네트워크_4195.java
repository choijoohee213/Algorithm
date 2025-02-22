import java.io.*;
import java.util.*;

class Main {
	static int[] parent, groupCnt;
	static int personTotalCnt;
	static Map<String, Integer> numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			numbers = new HashMap<>();
			parent = new int[200002];
			groupCnt = new int[200002];
			personTotalCnt = 0;

			for (int i = 0; i < F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();

				if(!numbers.containsKey(a)) {
					parent[personTotalCnt] = personTotalCnt;
					groupCnt[personTotalCnt] = 1;
					numbers.put(a, personTotalCnt);
					personTotalCnt++;
				}
				if(!numbers.containsKey(b)) {
					parent[personTotalCnt] = personTotalCnt;
					groupCnt[personTotalCnt] = 1;
					numbers.put(b, personTotalCnt);
					personTotalCnt++;
				}

				int num1 = numbers.get(a);
				int num2 = numbers.get(b);

				if(!isSameParent(num1, num2)) {
					union(num1, num2);
				}

				sb.append(groupCnt[getParent(num1)]).append('\n');
			}
		}

		br.close();
		System.out.print(sb);
	}

	static boolean isSameParent(int num1, int num2) {
		return getParent(num1) == getParent(num2);
	}

	static void union(int num1, int num2) {
		num1 = getParent(num1);
		num2 = getParent(num2);

		if(num1 < num2) {
			parent[num2] = num1;
			groupCnt[num1] += groupCnt[num2];
		} else {
			parent[num1] = num2;
			groupCnt[num2] += groupCnt[num1];
		}
	}

	static int getParent(int num) {
		if(parent[num] == num) {
			return num;
		}
		return parent[num] = getParent(parent[num]);
	}

}