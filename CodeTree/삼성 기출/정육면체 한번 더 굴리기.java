import java.io.*;
import java.util.*;

/**
 * 주사위 마주보는 면의 합은 7 (1~6)
 * 1,2번을 m번 움직이면서 얻은 점수 총합 구하기
 *
 * 1. 주사위 움직이기
 * - m번에 걸쳐 주사위를 1칸씩 굴림
 * - 점수 획득 : 칸 숫자 + 상하좌우로 인접하며 같은 숫자인 모든 칸의 합
 *
 * 2. 주사위 이동 방향 결정
 * - 아랫면이 칸 숫자보다 크면 90도 시계 회전하여 다시 이동, 작으면 90도 반시계 회전, 같으면 계속 진행
 * - 움직이다가 맵 벗어나면 방향 반대로 바꿔 한칸 움직임
 *
 */


class Main {
    static int n;
    static int[] dice;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0}; //우하좌상
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        int score = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dir = 0, x = 0, y = 0;
        dice = new int[]{1,3,2,4,5,6};  //상, 오, 아, 왼, 위, 하 (오른쪽 이동)

        while(m-- > 0) {
            //1. 주사위 이동
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            //맵 밖에 나가면 반대방향으로 바꿔 한칸 움직이기
            if(nx<0 || nx>=n || ny<0 || ny>=n) {
                dir = (dir + 2) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            move(dir);  //주사위 굴리기
            score += bfs(map, nx, ny);

            //2. 방향 결정
            int floor = dice[5];
            if(map[nx][ny] < floor) {
                dir = (dir + 1) % 4;
            } else if(map[nx][ny] > floor) {
                dir = (dir + 3) % 4;
            }

            x = nx;
            y = ny;
        }
		br.close();
		System.out.print(score);
	}

    private static int bfs(int[][] map, int startX, int startY) {
        int num = map[startX][startY];

        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.add(startX*n+startY);
        visited[startX][startY] = true;
        int cnt = 0;

        while(!q.isEmpty()) {
            int x = q.peek()/n;
            int y = q.peek()%n;
            q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx<0|| nx>=n || ny<0 || ny>=n || visited[nx][ny] || map[nx][ny] != num) continue;
                q.add(nx*n+ny);
                visited[nx][ny] = true;
            }
        }

        return cnt * num;
    }

    //상, 오, 아, 왼, 위, 하
    //오른쪽 이동 : 상->오, 오->하, 하->왼, 왼->상
    //아래 이동 : 상->아, 아->하, 하->위, 위->상
    //왼쪽 이동 : 상->왼, 왼->하, 하->오, 오->상
    //위 이동 : 상->위, 위->하, 하->아, 아->상
    private static void move(int dir) {
        int[] tmp = Arrays.copyOf(dice, dice.length);
        if(dir == 0) {  //오른쪽
            dice[1] = tmp[0];
            dice[5] = tmp[1];
            dice[3] = tmp[5];
            dice[0] = tmp[3];
        } else if(dir == 1) {  //아래
            dice[2] = tmp[0];
            dice[5] = tmp[2];
            dice[4] = tmp[5];
            dice[0] = tmp[4];
        } else if(dir == 2) {  //왼쪽
            dice[3] = tmp[0];
            dice[5] = tmp[3];
            dice[1] = tmp[5];
            dice[0] = tmp[1];
        } else {
            dice[4] = tmp[0];
            dice[5] = tmp[4];
            dice[2] = tmp[5];
            dice[0] = tmp[2];
        }
    }
}