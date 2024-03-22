import java.util.*;

class Solution {
	public int solution(int[][] land) {
		int answer = 0;

		int m = land.length, n = land[0].length;
		int[][] group = new int[m][n];
		List<Integer> oil = new ArrayList<>();
		boolean[][] visited = new boolean[m][n];
		int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
		int groupCnt = 0;

		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(land[i][j] == 0 || visited[i][j]) continue;

				groupCnt++;
				Queue<Integer> q = new LinkedList<>();
				q.add(i*n+j);
				visited[i][j] = true;
				int cnt = 0;

				while(!q.isEmpty()) {
					int x = q.peek()/n;
					int y = q.peek()%n;
					group[x][y] = groupCnt;
					q.poll();
					cnt++;

					for(int k=0; k<4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if(nx<0 || nx>=m || ny<0 || ny>=n || visited[nx][ny] || land[nx][ny] == 0) continue;
						q.add(nx*n+ny);
						visited[nx][ny] = true;
					}
				}

				oil.add(cnt);
			}
		}

		for(int i=0; i<n; i++) {
			boolean[] groupVisited = new boolean[groupCnt+1];
			int sum = 0;

			for(int j=0; j<m; j++) {
				if(land[j][i] == 0 || groupVisited[group[j][i]]) continue;
				sum += oil.get(group[j][i]-1);
				groupVisited[group[j][i]] = true;
			}
			answer = Math.max(answer, sum);
		}

		return answer;
	}
}