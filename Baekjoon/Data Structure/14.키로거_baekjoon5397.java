import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String cmd;
		Stack<Character> st_left, st_right;

		for (int tc = 0; tc < T; tc++) {
			cmd = br.readLine();
			st_left = new Stack<>();
			st_right = new Stack<>();
			for (int i = 0; i < cmd.length(); i++) {
				char c = cmd.charAt(i);
				if (c == '<') {
					if (!st_left.isEmpty())
						st_right.push(st_left.pop());
				} else if (c == '>') {
					if (!st_right.isEmpty())
						st_left.push(st_right.pop());
				} else if (c == '-') {
					if (!st_left.isEmpty())
						st_left.pop();
				} else
					st_left.push(c);
			}
			StringBuilder result = new StringBuilder();
			while (!st_right.isEmpty()) {
				st_left.push(st_right.pop());
			}
			for(int i=0; i<st_left.size(); i++) {
				result.append(st_left.elementAt(i));
			}
			System.out.println(result);
		}
	}
}