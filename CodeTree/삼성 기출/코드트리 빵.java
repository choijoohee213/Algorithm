import java.io.*;
import java.util.*;

/**
 * 1.편의점 방향으로 1칸 움직이기(최단거리가 여러개면 상,좌,우,하 순서로)
 * 2.편의점에 도착한다면 멈추고, 다른사람이 거기 못지나감
 * 3.t분이면 t번 사람이 편의점과 가장 가까운 베이스캠프로 가기(여러개면 행작은거,열작은거 순서로). 마찬가지 다른사람이 거기 못지나감
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        int[] storePos = new int[m];
        Queue<int[]>[] q = new Queue[m];  //x,y
        int[] dx = {-1,0,0,1}, dy = {0,-1,1,0};
        boolean[][][] visited = new boolean[m][n][n];
        boolean[] finished = new boolean[m];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            storePos[i] = (Integer.parseInt(st.nextToken())-1) * n + (Integer.parseInt(st.nextToken()) - 1);
            q[i] = new LinkedList<>();
        }

        int time = 0;
        while(true) {
            for (int i = 0; i < Math.min(time, m); i++) {
                if(finished[i]) continue;

                int size = q[i].size();
                while(size-- > 0) {
                    int x = q[i].peek()[0];
                    int y = q[i].peek()[1];
                    q[i].poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx<0 || nx>=n || ny<0 || ny>=n || visited[i][nx][ny] || map[nx][ny] == 2) continue;
                        if(nx == storePos[i]/n && ny == storePos[i]%n) {
                            finished[i] = true;
                            map[nx][ny] = 2;
                            if(++cnt == m) {
                                System.out.print(time+1);
                                return;
                            }
                        }
                        visited[i][nx][ny] = true;
                        q[i].add(new int[]{nx,ny});
                    }
                }
            }

            //3.time번 편의점에서 가까운 베이스캠프로
            if(time < m) {
                int storeX = storePos[time]/n;
                int storeY = storePos[time]%n;

                Queue<int[]> storeQueue = new LinkedList<>();
                boolean[][] storeVisited = new boolean[n][n];
                storeQueue.add(new int[]{storeX, storeY});
                storeVisited[storeX][storeY] = true;

                loop :
                while(!storeQueue.isEmpty()) {
                    int x = storeQueue.peek()[0];
                    int y = storeQueue.peek()[1];
                    storeQueue.poll();

                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        if(nx<0 || nx>=n || ny<0 || ny>=n || storeVisited[nx][ny] || map[nx][ny] == 2) continue;
                        if(map[nx][ny] == 1) {
                            q[time].add(new int[]{nx,ny});
                            visited[time][nx][ny] = true;
                            map[nx][ny] = 2;
                            break loop;
                        }
                        storeQueue.add(new int[]{nx,ny});
                        storeVisited[nx][ny] = true;
                    }
                }

            }

            time++;
        }
    }
}
