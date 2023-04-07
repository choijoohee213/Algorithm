import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 1. 주변 네개칸 중 나무가 있는 칸 수만큼 성장(플러스)
 * 2. 기존 나무들은 빈칸에 번식(나무/주변 빈칸 개수)
 * 3. 나무 칸에 제초제를 뿌리면 4개 대각선 방향으로 k칸만큼 전파(나무 박멸)
 *    -> 제일 많이 퍼질 한 칸에만 뿌리기(동일하다면 행 작고,열 작은 순)
 *    -> 나무 없는 곳 or 벽있는곳 그 칸까지 퍼짐
 * 4. 제초제가 뿌려진칸에는 c년만큼 있다가 c+1년째 없어짐
 *    -> 같은 칸에 새 제초제가 다시 뿌려지면 다시 c년동안 유지
 * 5. 위 세개 과정이 1년동안. m년동안 박멸한 그루수 구하기
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int result = 0;
        int[][] map = new int[n][n];
        int[][] herbicide = new int[n][n];
        int[] dx = {0,1,0,-1,-1,-1,1,1}, dy = {1,0,-1,0,-1,1,-1,1};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(m-->0) {
            //1. 나무 성장
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] <= 0) continue;
                    for (int l = 0; l < 4; l++) {
                        int nx = i + dx[l];
                        int ny = j + dy[l];

                        if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny]<=0) continue;
                        map[i][j]++;
                    }
                }
            }

            //2. 나무 번식
            Map<Integer, Integer> child = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] <= 0) continue;
                    boolean[] dirMap = new boolean[4];
                    int cnt = 0;

                    for (int l = 0; l < 4; l++) {
                        int nx = i + dx[l];
                        int ny = j + dy[l];

                        if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny]!=0 || herbicide[nx][ny]>0) continue;
                        cnt++;
                        dirMap[l] = true;
                    }
                    if(cnt == 0) continue;

                    int childTree = map[i][j]/cnt;
                    for (int l = 0; l < 4; l++) {
                        if(dirMap[l]) {
                            int key = (i + dx[l]) * n + (j + dy[l]);
                            child.put(key, child.getOrDefault(key, 0) + childTree);
                        }
                    }
                }
            }

            for (Entry<Integer, Integer> entry : child.entrySet()) {
                int idx = entry.getKey();
                map[idx/n][idx%n] += entry.getValue();
            }

            //제초제는 1년마다 1씩 사라짐
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(herbicide[i][j] > 0) {
                        herbicide[i][j]--;
                    }
                }
            }

            //3.제초제 전파
            int maxRemoved = 0, max_x = 0, max_y = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] <= 0) continue;
                    int removed = 0;

                    for (int l = 4; l < 8; l++) {
                        for (int o = 1; o <= k; o++) {
                            int nx = i + (dx[l] * o);
                            int ny = j + (dy[l] * o);

                            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                            if(map[nx][ny] <= 0) {
                                break;
                            }
                            removed += map[nx][ny];
                        }
                    }
                    removed += map[i][j];
                    if(removed > maxRemoved) {
                        maxRemoved = removed;
                        max_x = i;
                        max_y = j;
                    }
                }
            }

            //선정된 제초제를 뿌릴 위치에 뿌리기
            result += maxRemoved;
            for (int i = 4; i < 8; i++) {
                for (int j = 1; j <= k; j++) {
                    int nx = max_x + (dx[i] * j);
                    int ny = max_y + (dy[i] * j);

                    if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                    herbicide[nx][ny] = c;
                    if(map[nx][ny] <= 0) {
                        break;
                    }
                    map[nx][ny] = 0;
                }
            }
            herbicide[max_x][max_y] = c;
            map[max_x][max_y] = 0;
        }
        System.out.print(result);
    }
}
