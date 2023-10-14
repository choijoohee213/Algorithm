import java.util.*;
import java.io.*;

/**
 * 항상 최단거리로 이동
 * 모든 승객을 다 데려다줄수있는지 알아내고, 그럴경우 남는 배터리 양 출력
 *
 * 1. 태울 승객 고르기
 * - 현재위치에서 최단거리가 가장짧은 승객 (BFS로 구하기)
 * - 여러명일 경우 -> 행이 작은순 -> 열이 작은 순
 * 2. 목적지에 데려다주기
 * - 목적지에 태워주면 이동하며 소모한 배터리 두배만큼 충전한뒤 다시 이동
 * - 이동하는 도중 다 소모하면 종료(도착한 동시에 다 소모되는건 ㄱㅊ)
 * - 마지막 승객 태워다줫을때도 충전은 함
 *
 */

public class Main {
    static int n,m,c,carX, carY, successPassenger = 0;
    static int[][] map, destination;
    static boolean success = true;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        carX = Integer.parseInt(st.nextToken()) - 1;
        carY = Integer.parseInt(st.nextToken()) - 1;
        destination = new int[m+1][2];

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = i;
            destination[i][0] = Integer.parseInt(st.nextToken())-1;
            destination[i][1] = Integer.parseInt(st.nextToken())-1;
        }

        boolean success = true;

        while(!success || successPassenger < m) {
            int passenger = findPassenger();  //1.태울 승객 고르기
            if(c <= 0 || passenger == 0) {
                success = false;
                break;
            }

            goToDestination(passenger);

        }

        System.out.println(!success? -1 : c);
    }


    /*
     * 1. 태울 승객 고르기
     * - 현재위치에서 최단거리가 가장짧은 승객 (BFS로 구하기)
     * - 여러명일 경우 -> 행이 작은순 -> 열이 작은 순
     * - 이동하는 도중 다 소모하면 종료
     */
    private static int findPassenger() {
        int passenger = 0, passengerX = 0, passengerY = 0, moveCnt = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        int[] dx = {-1,0,0,1}, dy = {0,-1,1,0};

        q.add(carX*n+carY);
        visited[carX][carY] = 1;

        while(!q.isEmpty()) {
            int x = q.peek()/n;
            int y = q.peek()%n;
            q.poll();

            if(map[x][y] > 0 && moveCnt >= visited[x][y]-1) {
                if(moveCnt > visited[x][y]-1) {
                    passengerX = x;
                    passengerY = y;
                    passenger = map[x][y];
                    moveCnt = visited[x][y]-1;
                } else if(passengerX > x || (passengerX == x && passengerY > y)) {
                    passengerX = x;
                    passengerY = y;
                    passenger = map[x][y];
                }
            }

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny]>0 || map[nx][ny] == -1) continue;

                q.add(nx*n+ny);
                visited[nx][ny] = visited[x][y] + 1;
            }
        }

        c -= moveCnt;  //배터리 소모
        carX = passengerX;  //차 승객위치로 이동
        carY = passengerY;
        map[passengerX][passengerY] = 0;  //태운 승객은 없애기
        return passenger;
    }


    /*
     * 2. 목적지에 데려다주기
     * - 목적지에 태워주면 이동하며 소모한 배터리 두배만큼 충전한뒤 다시 이동
     * - 이동하는 도중 다 소모하면 종료(도착한 동시에 다 소모되는건 ㄱㅊ)
     * - 마지막 승객 태워다줫을때도 충전은 함
     */
    private static void goToDestination(int passenger) {
        int dstX = destination[passenger][0], dstY = destination[passenger][1], moveCnt = 0;

        //최단거리로 이동 - bfs
        Queue<Integer> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        int[] dx = {-1,0,0,1}, dy = {0,-1,1,0};

        q.add(carX*n+carY);
        visited[carX][carY] = 1;

        loop:
        while(!q.isEmpty()) {
            int x = q.peek()/n;
            int y = q.peek()%n;
            q.poll();

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny]>0 || map[nx][ny] == -1) continue;
                if(nx == dstX && ny == dstY) {
                    moveCnt = visited[x][y];
                    break loop;
                }
                q.add(nx*n+ny);
                visited[nx][ny] = visited[x][y] + 1;
            }
        }

        c -= moveCnt;
        carX = dstX;
        carY = dstY;
        if(c < 0 || moveCnt == 0) {
            success = false;
        } else {
            c += (moveCnt * 2);
            successPassenger++;
        }
    }


}