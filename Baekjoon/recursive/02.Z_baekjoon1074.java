import java.io.*;
import java.util.*;

class Main {
	static int n,r,c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		System.out.print(recursive(n,r,c));
	}

	static int recursive(int n, int r, int c) {
		if(n == 0) return 0;
		int half = 1<<(n-1);
		if(r<half && c<half) return recursive(n-1,r,c); //왼쪽위
		if(r<half) return half*half + recursive(n-1,r,c-half);  //오른쪽위
		if(c<half) return half*half*2 + recursive(n-1,r-half,c);  //왼쪽아래
		return half*half*3 + recursive(n-1,r-half,c-half);  //오른쪽아래
	}
}