import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder sb = new StringBuilder();
		Stack<Character> st = new Stack<>();
		boolean flag = false;

		for(Character c : s.toCharArray()) {
			if (c == ' ') {
				while(!st.isEmpty()) {
					char top = st.pop();
					sb.append(top);
				}
			} else if(c == '<') {
				flag = true;
				while(!st.isEmpty()) {
					char top = st.pop();
					sb.append(top);
				}
			} else if (c == '>') {
				flag = false;
			}
			else if(!flag) {
				st.push(c);
				continue;
			}
			sb.append(c);
		}
		while(!st.isEmpty()) {
			char top = st.pop();
			sb.append(top);
		}
		System.out.println(sb);
	}
}