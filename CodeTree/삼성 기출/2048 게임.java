import java.io.*;
import java.util.*;

public class Main {
    static int n, result = 0;
    static int[] sx, sy;
    static int[] dx = {1,0,-1,0}, dy = {0,-1,0,1}; //상우하좌(시계)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sx = new int[]{0,0,n-1,0};
        sy = new int[]{0,n-1,0,0};
        dfs(0, map);
        System.out.println(result);
    }

    private static void dfs(int cnt, int[][] map) {
        if(cnt == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result = Math.max(result, map[i][j]);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int startx = sx[i], starty = sy[i];
            int[][] newMap = new int[n][n];

            for (int j = 0; j < n; j++) {
                int nx = startx, ny = starty;
                int x = startx, y = starty;
                int prev = map[nx][ny];

                while(true) {
                    nx += dx[i];
                    ny += dy[i];
                    if(nx < 0 || nx > n-1 || ny < 0 || ny > n-1) {
                        break;
                    }
                    if(prev == 0) {
                        prev = map[nx][ny];
                        continue;
                    } else if(map[nx][ny] == 0) {
                        continue;
                    }

                    if(prev == map[nx][ny]) {
                        newMap[x][y] = prev * 2;
                        x += dx[i];
                        y += dy[i];
                        prev = 0;
                    } else {
                        newMap[x][y] = prev;
                        x += dx[i];
                        y += dy[i];
                        prev = map[nx][ny];
                    }
                }

                if(x>=0 && y>=0 && x <= n-1 && y <= n-1 && prev != 0) {
                    newMap[x][y] = prev;
                }

                if(i % 2 == 0) {
                    starty++;
                } else {
                    startx++;
                }
            }
            dfs(cnt+1, newMap);
        }
    }
}
