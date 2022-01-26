import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = n / 10, b = n % 10, sum = 9;
		if(a == 0) {
			sum = b;
		}
		else {
			String s = String.valueOf(a);
			for(int i=1; i<s.length(); i++) {
				sum += ((9 * Math.pow(10, i)) * (i+1));
			}
			sum += (n + 1 - (Math.pow(10, s.length()))) * (s.length() + 1);
		}
		System.out.println(sum);
	}
}