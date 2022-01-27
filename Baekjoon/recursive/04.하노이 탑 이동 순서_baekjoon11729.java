import java.util.Scanner;

class Main {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		sb.append((int)(Math.pow(2,n)-1)).append('\n');
		move(n, 1, 2, 3);
		System.out.println(sb.toString());
	}

	private static void move(int x, int from, int mid, int to) {
		if(x == 1) {
			sb.append(from).append(" ").append(to).append('\n');
			return;
		}
		move(x-1, from, to, mid);
		sb.append(from).append(" ").append(to).append('\n');
		move(x-1, mid, from, to);
	}
}