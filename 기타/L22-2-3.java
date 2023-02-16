import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 불 : 상,하,좌,우,대각선 포함
 * 얼음 : 상,하,좌,우 bfs로 불/얼음 방문순서 따진다음, (m - 방문순서)를 구함
 * 불은 이것을 +로 얼음은 -로 하여 다 더한값..
 */
class Main {
    static int[][] answer;

    public static int[][] solution(int n, int m, int[][] fires, int[][] ices) {
        answer = new int[n][n];

        for (int i = 0; i < fires.length; i++) {
            bfs(n, m, fires[i][0]-1, fires[i][1]-1, 8);
        }

        for (int i = 0; i < ices.length; i++) {
            bfs(n, m, ices[i][0]-1, ices[i][1]-1, 4);
        }
        return answer;
    }

    private static void bfs(int n, int m, int startX, int startY, int k) {
        int[] dx = {0, 1, 0, -1, 1, 1, -1, -1}, dy = {-1, 0, 1, 0, 1, -1, 1, -1};
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], -1);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(startX * n + startY);
        map[startX][startY] = 0;

        while (!q.isEmpty()) {
            int x = q.peek() / n;
            int y = q.peek() % n;
            q.poll();

            for (int i = 0; i < k; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] != -1) {
                    continue;
                }
                map[nx][ny] = map[x][y] + 1;
                if (map[nx][ny] >= m) {
                    continue;
                }
                q.add(nx * n + ny);
            }
        }

        map[startX][startY] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                if (k == 8) {
                    answer[i][j] += (m + 1) - map[i][j];
                } else {
                    answer[i][j] -= (m + 1) - map[i][j];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[][] arr = solution(3,2,new int[][]{{1,1}}, new int[][]{{3,3}});
//        int[][] arr = solution(5,3,new int[][]{{5,5},{1,3},{5,2}}, new int[][]{{1,5},{3,2}});
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
