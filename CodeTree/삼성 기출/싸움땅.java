import java.util.*;
import java.io.*;

/**
 * 플레이어는 초기 능력치 가짐
 * 1. 플레이어 순서대로 보는 방향대로 한칸이동 (맵 벗어나면 반대방향으로 한칸이동)
 * 2-1. 이동한곳에 총이있으면 획득.
 *  -> 이미 가지고있었으면 젤 센 총 가지고 나머지 총들은 내려놓음.
 * 2-2. 이동한곳에 사람이 있으면 싸움(초기 능력치 + 총의 공격력으로 더 큰 사람이. 같으면 초기능력치가 더 높은 사람이.)
 *  -> 진 사람은 총을 그 칸에 내려놓고 보는 방향대로 한칸 이동.
 *      -> 이동하려는 곳에 사람/맵 밖 이면 오른쪽으로 90도씩 회전해서 빈칸있는 방향으로 이동)
 *      -> 이동하려는 곳에 총 있으면 가장 쎈 총 획득하고 나머지는 내려놓음.
 *  -> 이긴 사람은 각자의 초기 능력치 + 총의 공격력의 차이만큼을 점수 획득, 젤 센총 획득하고 나머지는 내려놓음.
 * 3. 각 플레이어의 점수 출력
 */

public class Main {
    static class Area{
        Queue<Integer> guns = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        Area[][] areas = new Area[n][n];
        int[][] player = new int[m+1][5];
        int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                areas[i][j] = new Area();
                int gun = Integer.parseInt(st.nextToken());
                if(gun == 0) continue;
                areas[i][j].guns.add(gun);
            }
        }

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            player[i][0] = x;
            player[i][1] = y;

            map[x][y] = i;
            player[i][2] = Integer.parseInt(st.nextToken());
            player[i][3] = Integer.parseInt(st.nextToken());
            player[i][4] = 0;
        }

        int[] result = new int[m+1];

        while(k-- > 0) {
            for(int i=1; i<=m; i++) {
                //순서대로 보는 방향으로 한칸이동
                int x = player[i][0], y = player[i][1];
                int nx = x + dx[player[i][2]], ny = y + dy[player[i][2]];

                //맵 벗어나면 반대방향으로 이동
                if(nx<0 || nx>=n || ny<0 || ny>=n) {
                    player[i][2] = (2 + player[i][2]) % 4;
                    nx = x + dx[player[i][2]];
                    ny = y + dy[player[i][2]];
                }
                map[x][y] = 0;
                player[i][0] = nx;
                player[i][1] = ny;

                //이동한 곳에 사람이 있으면
                if(map[nx][ny] != 0) {
                    int originPlayer = map[nx][ny];
                    int sum_origin = player[originPlayer][3] + player[originPlayer][4];
                    int sum_new = player[i][3] + player[i][4];
                    int winner, loser;

                    if(sum_origin == sum_new && player[originPlayer][3]>player[i][3]){
                        winner = originPlayer;
                        loser = i;
                    } else if(sum_origin > sum_new){
                        winner = originPlayer;
                        loser = i;
                    } else {
                        winner = i;
                        loser = originPlayer;
                    }

                    //싸움에서 진 사람
                    areas[nx][ny].guns.add(player[loser][4]);
                    player[loser][4] = 0;

                    int dir = player[loser][2];
                    int nx_loser = player[loser][0] + dx[dir];
                    int ny_loser = player[loser][1] + dy[dir];

                    while(nx_loser<0 || nx_loser>=n || ny_loser<0 || ny_loser>=n || map[nx_loser][ny_loser] != 0) {
                        dir = (dir + 1) % 4;
                        nx_loser = player[loser][0] + dx[dir];
                        ny_loser = player[loser][1] + dy[dir];
                    }

                    player[loser][0] = nx_loser;
                    player[loser][1] = ny_loser;
                    player[loser][2] = dir;
                    if(!areas[nx_loser][ny_loser].guns.isEmpty()) {
                        player[loser][4] = areas[nx_loser][ny_loser].guns.poll();
                    }

                    //싸움에서 이긴사람
                    result[winner] += Math.abs(sum_origin - sum_new);
                    if(!areas[nx][ny].guns.isEmpty()) {
                        if(player[winner][4] > 0) { //이미 가지고 있었으면
                            if(areas[nx][ny].guns.peek() > player[winner][4]) {
                                int betterGun = areas[nx][ny].guns.poll();
                                areas[nx][ny].guns.add(player[winner][4]);
                                player[winner][4] = betterGun;
                            }
                        } else {
                            player[winner][4] = areas[nx][ny].guns.poll();
                        }
                    }

                    map[nx][ny] = winner;
                    map[nx_loser][ny_loser] = loser;
                }

                //이동한 곳에 총이 있으면 획득
                else if(!areas[nx][ny].guns.isEmpty()) {
                    if(player[i][4] > 0) { //이미 가지고 있었으면
                        if(areas[nx][ny].guns.peek() > player[i][4]) {
                            int betterGun = areas[nx][ny].guns.poll();
                            areas[nx][ny].guns.add(player[i][4]);
                            player[i][4] = betterGun;
                        }
                    } else {
                        player[i][4] = areas[nx][ny].guns.poll();
                    }
                    map[nx][ny] = i;
                } else {
                    map[nx][ny] = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=m; i++) {
            sb.append(result[i]).append(' ');
        }
        System.out.println(sb.toString());
    }
}
