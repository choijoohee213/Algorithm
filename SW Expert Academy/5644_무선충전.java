import java.util.*;
import java.io.*;

/**
 * 특정 충전기 범위에 들어가면 충전기 성능만큼 충전가능
 * 한사람이 두 범위에 속할 경우,하나만 선택(충전량 합이 더 커지는 방향으로.)
 * 한 충전기 범위에 두명 접속하면, 사용자 수만큼 분배해서 충전
 * 모든 사용자가 충전한 양의합 최댓값 구하기
 *
 * 맵 크기는 10*10
 * 사용자는 두명, 한명은 좌상단, 한명은 우하단에서 출발
 * 사용자는 초기위치부터 충전 가능(0초)
 */

public class Solution {

    static int m,n,size=10;
    static int[][] moveInfo, chargerInfo;
    static Area[][] areas;
    static int[] dx = {0,-1,0,1,0}, dy = {0,0,1,0,-1};

    static class Area {
        List<Integer> chargers = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            moveInfo = new int[2][m];
            chargerInfo = new int[n+1][4]; //x,y,충전범위,충전량
            areas = new Area[size][size];

            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    areas[i][j] = new Area();
                }
            }


            for(int i=0; i<2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++) {
                    moveInfo[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=1; i<=n; i++) {
                st = new StringTokenizer(br.readLine());
                chargerInfo[i][1] = Integer.parseInt(st.nextToken()) - 1;
                chargerInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
                chargerInfo[i][2] = Integer.parseInt(st.nextToken());
                chargerInfo[i][3] = Integer.parseInt(st.nextToken());
            }

            bfs();
            sb.append('#').append(tc).append(' ').append(getMaxChargeAmount()).append('\n');
        }
        System.out.print(sb);
    }

    private static void bfs() {
        for(int i=1; i<=n; i++) {
            int cx = chargerInfo[i][0];
            int cy = chargerInfo[i][1];
            int range = chargerInfo[i][2];

            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[size][size];
            q.add(new int[] {cx,cy,0});
            visited[cx][cy] = true;

            while(!q.isEmpty()) {
                int x = q.peek()[0];
                int y = q.peek()[1];
                int cnt = q.peek()[2];
                q.poll();

                areas[x][y].chargers.add(i);

                if(cnt>=range) continue;

                for(int dir=1; dir<=4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(nx<0 || nx>=size || ny<0 || ny>=size || visited[nx][ny]) continue;
                    q.add(new int[] {nx,ny,cnt+1});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static int getMaxChargeAmount() {
        int ax = 0, ay = 0, bx = 9, by = 9, result = 0;

        for(int i=0; i<=m; i++) {
            int sum = 0;


            if(areas[ax][ay].chargers.size() >= 1 && areas[bx][by].chargers.size() >= 1) {
                for(Integer charger1 : areas[ax][ay].chargers) {
                    for(Integer charger2 : areas[bx][by].chargers) {
                        if(charger1 == charger2) {
                            sum = Math.max(sum, chargerInfo[charger1][3]);
                        } else {
                            sum = Math.max(sum, chargerInfo[charger1][3] + chargerInfo[charger2][3]);
                        }
                    }
                }
            } else {
                int sum1=0, sum2=0;

                for(Integer charger1 : areas[ax][ay].chargers) {
                    sum1 = Math.max(sum1, chargerInfo[charger1][3]);
                }

                for(Integer charger2 : areas[bx][by].chargers) {
                    sum2 = Math.max(sum2, chargerInfo[charger2][3]);
                }
                sum = sum1 + sum2;
            }

            result += sum;
            if(i == m) break;

            int aDir = moveInfo[0][i];
            int bDir = moveInfo[1][i];

            ax += dx[aDir];
            ay += dy[aDir];
            bx += dx[bDir];
            by += dy[bDir];
        }
        return result;
    }
}
