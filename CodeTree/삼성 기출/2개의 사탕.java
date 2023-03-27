/*
- 빨간색 사탕 뺴내기 (파랑이 먼저나오거나 동시에 나오면안댐)
- 장애물이나 사탕에 부딪힐때까지 미끌어짐
- 빼낼수있는 최소횟수(10이내안되면 -1)
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n*m][n*m];
        int bluePos = 0, redPos = 0;

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if(map[i][j] == 'B') {
                    bluePos = i*m+j;
                } else if(map[i][j] == 'R') {
                    redPos = i*m+j;
                }
            }
        }
        queue.add(new int[]{bluePos, redPos, 0});
        visited[bluePos][redPos] = true;
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};  //우,하,좌,상

        while(!queue.isEmpty()) {
            int[] info = queue.poll();
            int bx = info[0] / m;
            int by = info[0] % m;
            int rx = info[1] / m;
            int ry = info[1] % m;
            int cnt = info[2];

            if(cnt >= 10) {
                System.out.println(-1);
                return;
            }

            loop :
            for (int i = 0; i < 4; i++) {
                int bnx = bx, bny = by;
                while(true) {
                    int nx = bnx + dx[i], ny = bny + dy[i];
                    if(nx<0 || nx>=n || ny<0 || ny>=m || map[nx][ny] == '#') break;
                    if(map[nx][ny] == 'O') continue loop;
                    bnx = nx;
                    bny = ny;
                }

                int rnx = rx, rny = ry;
                while(true) {
                    int nx = rnx + dx[i], ny = rny + dy[i];
                    if(nx<0 || nx>=n || ny<0 || ny>=m || map[nx][ny] == '#') break;
                    if(map[nx][ny] == 'O') {
                        System.out.println(cnt + 1);
                        return;
                    }
                    rnx = nx;
                    rny = ny;
                }

                //같은 위치면, 이전에 있던 사탕을 위치 조정
                if(bnx == rnx && bny == rny) {
                    if(i == 0) { //우
                        if(by < ry) {
                            bny -= dy[i];
                        } else {
                            rny -= dy[i];
                        }
                    } else if(i == 1) { //하
                        if(bx < rx) {
                            bnx -= dx[i];
                        } else {
                            rnx -= dx[i];
                        }
                    } else if(i == 2) {  //좌
                        if(by < ry) {
                            rny -= dy[i];
                        } else {
                            bny -= dy[i];
                        }
                    } else { //상
                        if(bx < rx) {
                            rnx -= dx[i];
                        } else {
                            bnx -= dx[i];
                        }
                    }
                }

                int nblue = bnx * m + bny, nred = rnx * m + rny;
                if(visited[nblue][nred]) continue;
                queue.add(new int[]{nblue, nred, cnt+1});
                visited[nblue][nred] = true;
            }
        }

        System.out.println(-1);
    }
}
