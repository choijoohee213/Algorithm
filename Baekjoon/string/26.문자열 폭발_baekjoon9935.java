import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine(), bomb = br.readLine();
		Stack<Character> st = new Stack<>();

		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			st.push(c);
			if(st.size() >= bomb.length()) {
				boolean flag = true;
				for(int j=0; j<bomb.length(); j++) {
					if(st.get(st.size() - bomb.length() + j) != bomb.charAt(j)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for(int j=0; j<bomb.length(); j++) {
						st.pop();
					}
				}
			}
		}

		while(!st.isEmpty()) {
			sb.append(st.pop());
		}
		br.close();
		System.out.print(sb.length() == 0 ? "FRULA" : sb.reverse().toString());
	}
}