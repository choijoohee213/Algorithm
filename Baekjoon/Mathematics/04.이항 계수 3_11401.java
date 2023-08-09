import java.io.*;
import java.util.*;

class Main {
    static final int p = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long result1 = factorial(n);
        long result2 = factorial(k) * factorial(n-k) % p;

        System.out.print(result1 * pow(result2, p - 2) % p);
		br.close();
	}

    private static long factorial(int n) {
        long x = 1L;

        while(n > 1) {
            x = (x * n) % p;
            n--;
        }
        return x;
    }

    private static long pow(long base, long expo) {
        long x = 1L;

        while(expo > 0) {
            if(expo % 2 == 1) {
                x *= base;
                x %= p;
            }
            base = (base * base) % p;
            expo /= 2;
        }
        return x;
    }
}