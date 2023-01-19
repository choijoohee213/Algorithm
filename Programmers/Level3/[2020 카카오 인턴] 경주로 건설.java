import java.util.*;

public class Solution {
    public int solution(int[][] board) {
        int n = board.length, result = Integer.MAX_VALUE;
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};  //우,하,좌,상
        int[] opposite = {2,3,0,1};

        Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[3], o2[3]);
            }
        });
        int[][][] visited = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        q.add(new int[]{0,0,-1, 0});
        visited[0][0][0] = visited[0][0][1] = visited[0][0][2] = visited[0][0][3] = 0;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int dir = q.peek()[2];
            int sum = q.peek()[3];
            q.poll();

            if(x == n-1 && y == n-1) {
                result = Math.min(sum, result);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if(dir == opposite[i]) continue;
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny<0 || ny>=n || board[nx][ny] == 1) continue;
                int price = 100;
                if((dir % 2 == 0 && i % 2 == 1) || (dir % 2 == 1 && i % 2 == 0)) {
                    price += 500;
                }

                if(visited[nx][ny][i] < (sum + price)) continue;
                visited[nx][ny][i] = sum + price;
                q.add(new int[]{nx, ny, i, visited[nx][ny][i]});
            }
        }
        return result;
    }
}
