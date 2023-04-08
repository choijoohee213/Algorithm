import java.io.*;
import java.util.*;

/** 12:50
 * 1. a와 b의 조화로움 : (a칸수 + b칸수) * a숫자값 * b숫자값 * a와b가 맞닿아있는 변의수
 *      -> bfs
 * 2. 초기 예술 점수 : 그룹 쌍 간의 총 조화로움 구하기
 *      -> 조합
 * 3. 배열 회전
 *      - 정중앙을 기준으로 십자모양은 반시계 90도 회전
 *      - 나머지 4개 정사각형은 각각 시계방향 90도 회전
 * 4. 예술 점수 다시 구하기(이게 1회전임)
 * 5. 초기예술점수 + 1,2,3회전 이후 예술점수 합 구하기
 *
 */
public class Main {
    static class Group {
        int idx;
        int size;
        int num;

        public Group(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][] groupMap = new int[n][n];
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
        int score = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int dir = 0; dir < 4; dir++) {
            //1. 조화로움 구하기 위한 bfs
            boolean[][] visited = new boolean[n][n];
            int idx = 0;
            List<Group> groups = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j]) continue;
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i*n+j);
                    visited[i][j] = true;
                    int num = map[i][j];
                    int size = 1;
                    groupMap[i][j] = idx;
                    Group group = new Group(idx, num);

                    while(!q.isEmpty()) {
                        int x = q.peek()/n;
                        int y = q.peek()%n;
                        q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny] || map[nx][ny] != num) continue;
                            size++;
                            visited[nx][ny] = true;
                            q.add(nx*n+ny);
                            groupMap[nx][ny] = idx;
                        }
                    }
                    idx++;
                    group.size = size;
                    groups.add(group);
                }
            }

            //그룹 간의 맞닿은 변 구하기
            int[][] closedGroup = new int[idx][idx];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n || groupMap[nx][ny] <= groupMap[i][j]) continue;
                        closedGroup[groupMap[i][j]][groupMap[nx][ny]]++;
                    }
                }
            }

            //예술 점수 구하기
            for (int i = 0; i < idx; i++) {
                for (int j = i+1; j < idx; j++) {
                    if(closedGroup[i][j] == 0) continue;
                    Group group1 = groups.get(i);
                    Group group2 = groups.get(j);
                    score += (group1.size + group2.size) * group1.num * group2.num * closedGroup[i][j];
                }
            }

            //배열 회전
            int half = (n * n) / 2;
            for (int i = 1; i <= n/2; i++) {
                int[] four = {half-(n*i), half-i, half+(n*i), half+i};
                int tmp, prev = map[four[0]/n][four[0]%n];
                for (int j = 0; j < 3; j++) {
                    tmp = map[four[j+1]/n][four[j+1]%n];
                    map[four[j+1]/n][four[j+1]%n] = prev;
                    prev = tmp;
                }
                map[four[0]/n][four[0]%n] = prev;
            }

            int[][] newMap = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newMap[i][j] = map[i][j];
                }
            }

            int[] start = {0, n/2+1, (n/2+1)*n, (n/2+1)*n+n/2+1};
            for (int i = 0; i < 4; i++) {
                int startX = start[i]/n, startY = start[i]%n;
                for (int x = 0+startX; x < n/2+startX; x++) {
                    for (int y = 0+startY; y <n/2+startY; y++) {
                        int newX = y-startY+startX;
                        int newY = n/2-1-x+startY+startX;
                        newMap[newX][newY] = map[x][y];
                    }

                }
            }
            map = newMap;
        }
        System.out.print(score);
    }
}
