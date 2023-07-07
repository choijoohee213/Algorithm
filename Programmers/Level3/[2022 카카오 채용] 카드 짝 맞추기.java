import java.util.*;

/**
 * 카드 종류 찾아가는 순서는 순열로 하자
 */

class Solution {
	int[][] map;
	int n = 4, startX, startY, answer = Integer.MAX_VALUE;
	int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	List<Integer> cardNums;
	Map<Integer, int[]> cardPos = new HashMap<>();

	public int solution(int[][] board, int r, int c) {
		map = board;
		startX = r;
		startY = c;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(board[i][j] > 0) {
					if(!cardPos.containsKey(board[i][j])) {
						cardPos.put(board[i][j], new int[2]);
						cardPos.get(board[i][j])[0] = i*n+j;
					} else {
						cardPos.get(board[i][j])[1] = i*n+j;
					}
				}
			}
		}

		cardNums = new ArrayList<>(cardPos.keySet());
		permutation(0, new int[cardPos.size()], new boolean[cardPos.size()]);
		return answer;
	}

	private void permutation(int cnt, int[] arr, boolean[] visited) {
		if(cnt == cardNums.size()) {
			startGame(arr);
			return;
		}

		for (int i = 0; i < cardNums.size(); i++) {
			if(visited[i]) continue;
			visited[i] = true;
			arr[cnt] = cardNums.get(i);
			permutation(cnt+1, arr, visited);
			visited[i] = false;
		}
	}

	private void startGame(int[] arr) {
		int[][] newMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		int sx = startX, sy = startY;
		int cnt = 0;

		//그림 순서 순열 돌기
		for (int num : arr) {
			int pos1 = cardPos.get(num)[0], pos2 = cardPos.get(num)[1];
			if(sx*n+sy != pos1 && sx*n+sy != pos2) {
				int[] result = bfs(sx, sy, num, newMap);
				sx = result[0];
				sy = result[1];
				cnt += result[2];
			}

			//짝 -> 짝
			int[] result;
			if(sx*n+sy == pos1) {
				result = bfs(pos1/n, pos1%n, num, newMap);
			} else {
				result = bfs(pos2/n, pos2%n, num, newMap);
			}
			sx = result[0];
			sy = result[1];
			cnt += (2 + result[2]);
			newMap[pos1/n][pos1%n] = 0;
			newMap[pos2/n][pos2%n] = 0;
		}

		answer = Math.min(answer, cnt);
	}

	private int[] bfs(int sx, int sy, int num, int[][] newMap) {
		Queue<int[]> q = new LinkedList<>();
		int[][] visited = new int[n][n];
		q.add(new int[]{sx, sy});
		visited[sx][sy] = 1;
		int cnt = 0, returnX = 0, returnY = 0;

		loop :
		while(!q.isEmpty()) {  //그림 찾아가기 bfs
			int x = q.peek()[0];
			int y = q.peek()[1];
			q.poll();

			for (int i = 0; i < 4; i++) {  //한칸씩 이동
				int nx = x + dx[i];
				int ny = y + dy[i];
//					int nState = state;

				if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny] > 0) continue;
				if(newMap[nx][ny] == num) {
					returnX = nx;
					returnY = ny;
					cnt += visited[x][y];
					break loop;
				}
				visited[nx][ny] = visited[x][y] + 1;
				q.add(new int[]{nx,ny});
			}

			for (int i = 0; i < 4; i++) {  //컨트롤 + 이동
				int nx = x;
				int ny = y;

				while(true) {
					nx += dx[i];
					ny += dy[i];
					if(nx<0 || nx>=n || ny<0 || ny>=n) {
						nx -= dx[i];
						ny -= dy[i];
						break;
					}
					if(newMap[nx][ny] > 0) {
						break;
					}
				}

				if(visited[nx][ny] > 0) continue;
				if(newMap[nx][ny] == num) {
					returnX = nx;
					returnY = ny;
					cnt += visited[x][y];
					break loop;
				}
				visited[nx][ny] = visited[x][y] + 1;
				q.add(new int[]{nx,ny});
			}
		}
		return new int[]{returnX, returnY, cnt};
	}
}