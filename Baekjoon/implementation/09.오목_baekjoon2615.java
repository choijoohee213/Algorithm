import java.util.Scanner;

class Main {
	static final int[] dx = {-1, 0, 1, 1};
	static final int[] dy = {1, 1, 1, 0};

	public static void main(String[] args) {
		//System.setIn(new FileInputStream("Test5.txt"));
		Scanner sc = new Scanner(System.in);

		int size = 19, color = 0, resultX = 0, resultY = 0;
		int[][] board = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 0)
					continue;  //바둑알이 없는 자리는 건너뛰기

				for (int dir = 0; dir < 4; dir++) {  //4방 탐색
					int x = i, y = j;
					boolean success = true;

					for (int k = 0; k < 5; k++) {  //바둑알이 5개 이어져있는지 확인(오목 체크)
						if (x < 0 || x >= size || y < 0 || y >= size || board[i][j] != board[x][y]) { //배열 범위 벗어남
							success = false;
							break;
						}
						x += dx[dir];
						y += dy[dir];
					}

					if (success) {  //오목 발견

						if ((x >= 0 && x < size && y >= 0 && y < size && board[i][j] == board[x][y])
							|| (i - dx[dir] >= 0 && i - dx[dir] < size && j - dy[dir] >= 0 && j - dy[dir] < size
							&& board[i][j] == board[i - dx[dir]][j - dy[dir]])) {
							continue;
						}
						color = board[i][j];
						resultX = i + 1;
						resultY = j + 1;
						System.out.println(color + "\n" + resultX + " " + resultY);
						return;
					}
				}
			}
		}
		System.out.println("0");
		sc.close();
	}
}
