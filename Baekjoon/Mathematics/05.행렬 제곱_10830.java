import java.io.*;
import java.util.*;

class Main {
    static int n;
    static final int p = 1000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = pow(arr, b);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] %= p;
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

		br.close();
		System.out.print(sb);
	}

    static int[][] pow(int[][] base, long expo) {
        if(expo == 1) {
            return base;
        }

        int[][] tmp = pow(base, expo / 2);

        tmp = multiply(tmp, tmp);
        if(expo % 2 == 1) {
            tmp = multiply(tmp, base);
        }

        return tmp;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    tmp[i][j] += a[i][k] * b[k][j];
                    tmp[i][j] %= p;
                }
            }
        }

        return tmp;
    }
}