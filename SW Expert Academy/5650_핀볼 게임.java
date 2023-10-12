import java.io.*;
import java.util.*;

/**
 *
 * 점수는 벽/블록에 부딪힌 횟수
 * 게임판 위에서 출발위치와 진행방향을 정할수 있을때, 점수의 최대값 구하기 (블록,웜홀,블랙홀에선 출발 불가능)
 *
 * 블록(1~5)
 *  - 경사면 만나면 직각으로 꺾임
 *  - 수평면/수직면은 반대방향으로 바꿔 돌아옴
 * 벽 : 반대방향으로 바꿔 돌아옴
 * 웜홀(6~10) : 동일한 숫자를 가진 다른 웜호롤 빠져나옴, 방향은 유지
 * 블랙홀(-1) : 게임 끝남
 * 출발 위치로 돌아올 경우에도 게임 끝남
 */

class Main {
    static int n, maxScore = 0;
    static int[][] map;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};  //우하좌상
    static Map<Integer, int[]> wormhole;
    static boolean flag;

    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            maxScore = 0;
            map = new int[n][n];
            wormhole = new HashMap<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] >= 6 && map[i][j] <= 10) {
                        if(!wormhole.containsKey(map[i][j])) {
                            wormhole.put(map[i][j], new int[]{i*n+j, 0});
                        } else {
                            wormhole.get(map[i][j])[1] = i*n+j;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] != 0) continue;

                    for (int k = 0; k < 4; k++) {
                        startPinBall(i,j,k);  //0,2,2
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
        }

		br.close();
		System.out.print(sb);
	}

    private static void startPinBall(int x, int y, int dir) {
        int startX = x, startY = y;
        int score = 0;

        while(true) {
            flag = false;
            int nx = x + dx[dir], ny = y + dy[dir];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {  //벽
                dir = (dir + 2) % 4;
                nx = x;
                ny = y;
                score = score * 2 + 1;
                break;
            }

            if (map[nx][ny] >= 1 && map[nx][ny] <= 5) {  //블록
                dir = getBlockDir(map[nx][ny], dir);
                if(flag) {
                    score = score * 2 + 1;
                    break;
                }
                score++;
            } else if (map[nx][ny] >= 6 && map[nx][ny] <= 10) {  //웜홀
                if (wormhole.get(map[nx][ny])[0] == nx * n + ny) {
                    int npos = wormhole.get(map[nx][ny])[1];
                    nx = npos / n;
                    ny = npos % n;
                } else {
                    int npos = wormhole.get(map[nx][ny])[0];
                    nx = npos / n;
                    ny = npos % n;
                }
            } else if (map[nx][ny] == -1 || (nx == startX && ny == startY)) {  //블랙홀 / 처음 위치
                break;
            }

            x = nx;
            y = ny;
        }
        maxScore = Math.max(score, maxScore);
    }

    private static int getBlockDir(int block, int dir) {
        //우하좌상
        if(block == 1) {
            if(dir == 0 || dir == 3) {  //왼쪽,아래에서 오면 반대방향으로
                dir = (dir+2) % 4;
                flag = true;
            } else if(dir == 1) { //위쪽에서 오면
                dir = 0;
            } else {  //오른쪽에서
                dir = 3;
            }
        } else if(block == 2) {
            if(dir == 0 || dir == 1) {  //왼쪽,위에서 오면 반대방향으로
                dir = (dir+2) % 4;
                flag = true;
            } else if(dir == 3) { //아래에서 오면
                dir = 0;
            } else {
                dir = 1;
            }
        } else if(block == 3) {
            if(dir == 1 || dir == 2) {
                dir = (dir + 2) % 4;
                flag = true;
            } else if(dir == 3) {
                dir = 2;
            } else {
                dir = 1;
            }
        } else if(block == 4) {
            if(dir == 2 || dir == 3) {
                dir = (dir + 2) % 4;
                flag = true;
            } else if(dir == 1) {
                dir = 2;
            } else {
                dir = 3;
            }
        } else {
            dir = (dir + 2) % 4;
            flag = true;
        }
        return dir;
    }
}