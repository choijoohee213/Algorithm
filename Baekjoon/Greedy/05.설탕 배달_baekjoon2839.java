import java.io.*;
import java.util.*;

public class Main_bj_2839_설탕배달_대전_5반_최주희 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()), cnt = 0;

		while (n > 0) {
			if (n % 5 == 0) {
				cnt += n/5;
				n = 0;
				break;
			}
			else if (n % 3 == 0) {
				cnt++;
				n -= 3;
			} else if (n > 5) {
				cnt++;
				n -= 5;
			} else {
				cnt = -1;
				break;
			}
		}
		System.out.print(cnt);
	}
}
