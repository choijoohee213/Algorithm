import java.util.*;
import java.io.*;

/** 2:06
 * 1. 3명이상의 한팀이 각 라운드별로 머리사람 따라서 한칸 이동
 * 2. 각 라운드마다 공 던져짐. 우, 상, 좌, 하 (반시계방향) 반복
 *    - 우 : (0,0) 시작 후 다음 라운드는 행 증가
 *    - 상 : (n-1,0) 시작 후 다음 라운드는 열 증가
 *    - 좌 : (n-1,n-1) 시작 후 다음 라운드는 행 감소
 *    - 하 : (0,n-1) 시작 후 다음 라운드는 열 감소
 * 3. 공 던져지면, 해당 선에서 최초로 만나는 사람이 점수얻음(머리사람을 시작으로 k번째 사람이면 k제곱만큼)
 * 4. 점수 얻은 팀은 머리사람과 꼬리사람이 바뀜(방향을 바꾼다는말)
 * 5. 획득한 점수의 총합 구하기
 *
 */

public class Main {
    static class Team {
        int id;
        List<Integer> paths = new ArrayList<>();
        int memberSize;
        int headIdx;
        boolean reversed;

        public Team(int id) {
            this.id = id;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dx = {0,-1,0,1}, dy = {1,0,-1,0};
        int[] ball_dx = {1,0,-1,0}, ball_dy = {0,1,0,-1};
        int[] start_x = {0,n-1,n-1,0}, start_y = {0,0,n-1,n-1};
        int[][] map = new int[n][n];
        int result = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Team[] teams = new Team[m+1];
        boolean[][] visited = new boolean[n][n];
        int idx = 1;

        //팀 찾아서 팀 정보 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    int memberSize = 1;
                    teams[idx] = new Team(idx);
                    visited[i][j] = true;
                    map[i][j] = idx;
                    teams[idx].paths.add(i*n+j);
                    Queue<Integer> q = new LinkedList<>();

                    //시작 방향 찾기
                    for (int l = 0; l < 4; l++) {
                        int nx = i + dx[l];
                        int ny = j + dy[l];
                        if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                        if(map[nx][ny] != 0 && map[nx][ny] != 2) {
                            q.add(nx*n+ny);
                            visited[nx][ny] = true;
                            if(map[nx][ny] == 3) {
                                memberSize++;
                            }
                            map[nx][ny] = idx;
                            teams[idx].paths.add(nx*n+ny);
                            break;
                        }
                    }

                    //같은 팀 찾아서 표시
                    while(!q.isEmpty()) {
                        int x = q.peek()/n;
                        int y = q.peek()%n;
                        q.poll();

                        for (int l = 0; l < 4; l++) {
                            int nx = x + dx[l];
                            int ny = y + dy[l];
                            if(nx<0 || nx>=n || ny<0 || ny>=n || map[nx][ny] == 0 || visited[nx][ny]) continue;
                            q.add(nx*n+ny);
                            visited[nx][ny] = true;
                            teams[idx].paths.add(nx*n+ny);
                            if(map[nx][ny] <= 3) {
                                memberSize++;
                            }
                            map[nx][ny] = idx;
                            break;
                        }
                    }
                    teams[idx].memberSize = memberSize;
                    idx++;
                }
            }
        }

        loop:
        for (int round = 0; round < k; round++) {
            //1. 각 팀은 머리사람 따라서 한칸 이동
            for (int i = 1; i <= m; i++) {
                if(!teams[i].reversed) {
                    teams[i].headIdx = (teams[i].headIdx + 1) % teams[i].paths.size();
                } else {
                    teams[i].headIdx = (teams[i].headIdx + teams[i].paths.size() - 1) % teams[i].paths.size();
                }
            }

            //2. 공 던지기
            int dir = (round/n) % 4;
            int dirIdx = round % n;

            int x = start_x[dir] + (ball_dx[dir] * dirIdx);
            int y = start_y[dir] + (ball_dy[dir] * dirIdx);

            //3.해당 선에서 최초로 만나는 사람이 점수얻음(머리사람을 시작으로 k번째 사람이면 k제곱만큼)
            boolean[] visitTeam = new boolean[m+1];
            int[][] personInfo = new int[n][n];

            for (int i = 0; i < n; i++) {
                if(map[x][y] > 0) {
                    int teamIdx = map[x][y];
                    if(visitTeam[teamIdx] && personInfo[x][y] > 0) {
                        result += personInfo[x][y];
                        teams[teamIdx].reversed = !teams[teamIdx].reversed;
                        continue loop;
                    } else if(!visitTeam[teamIdx]) {
                        visitTeam[teamIdx] = true;
                        int pathSize = teams[teamIdx].paths.size();
                        int pathIdx = teams[teamIdx].headIdx;

                        if (!teams[teamIdx].reversed) {
                            for (int j = 0; j < teams[teamIdx].memberSize; j++) {
                                int curPos = teams[teamIdx].paths.get(pathIdx);
                                personInfo[curPos / n][curPos % n] = (j + 1) * (j + 1);
                                if ((x * n + y) == curPos) {
                                    result += (j + 1) * (j + 1);
                                    teams[teamIdx].reversed = !teams[teamIdx].reversed;
                                    continue loop;
                                }
                                pathIdx = (pathIdx + pathSize - 1) % pathSize;
                            }
                        } else {
                            for (int j = 0; j < teams[teamIdx].memberSize; j++) {
                                int curPos = teams[teamIdx].paths.get(pathIdx);
                                personInfo[curPos / n][curPos % n] = (teams[teamIdx].memberSize - j) * (teams[teamIdx].memberSize - j);
                                if ((x * n + y) == curPos) {
                                    result += (teams[teamIdx].memberSize - j) * (teams[teamIdx].memberSize - j);
                                    teams[teamIdx].reversed = !teams[teamIdx].reversed;
                                    continue loop;
                                }
                                pathIdx = (pathIdx + pathSize - 1) % pathSize;
                            }
                        }
                    }
                }
                x += dx[dir];
                y += dy[dir];
            }
        }
        System.out.print(result);
    }
}
