import java.io.*;
import java.util.*;

/**
 * n*n (1,1) 시작. 벽 못감. 상하좌우 움직임 -> bfs
 * 벽은 1~9 내구도. 회전할때 내구도가 1씩 감소. 0되면 빈칸.
 * 1. 참가자 이동 : 모든 참가자가 동시에 움직임
 *  - 출구까지 최단거리가 가장짧은 곳으로 움직임(2개이상이면 상하가 먼저)
 *  - 움직일 수 없으면 안움직임.
 *  - 한칸에 여러명 가능 -> 리스트로
 * 2. 미로 회전 : 이동 다 끝나면 회전
 *  - 한명 이상 참가자와 출구를 포함한 가장 작은 정사각형 잡기(여러개면 행작은거, 열작은거 순)
 *  - 시계방향으로 90도 회전하면서 벽 내구도 깎임
 * 1번과 2번을 k번 반복.
 * 모두 탈출 or k초 지나면 모든 이동거리 합과 출구 좌표 출력 끝.
 *
 *
 */

class Main {
    static int n,m,k,endX,endY;

    static class Area {
        int val;
        List<Integer> people = new ArrayList<>();

        public Area(int val) {
            this.val = val;
        }

        public void addPeople(int idx) {
            people.add(idx);
        }

        public void removePeople(int idx) {
            people.remove((Integer) idx);
        }
    }

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
        Set<Integer> exited = new HashSet<>();

        Area[][] map = new Area[n][n];
        int[][] participants = new int[m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = new Area(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            participants[i][0] = Integer.parseInt(st.nextToken()) - 1;
            participants[i][1] = Integer.parseInt(st.nextToken()) - 1;
            map[participants[i][0]][participants[i][1]].addPeople(i);
        }

        st = new StringTokenizer(br.readLine());
        endX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;
        int move = 0;

        /*
         * 1. 참가자 이동 : 모든 참가자가 동시에 움직임
         *  - 출구까지 최단거리가 가장짧은 곳으로 움직임(2개이상이면 상하가 먼저)
         *  - 움직일 수 없으면 안움직임.
         *  - 한칸에 여러명 가능 -> 리스트로
         */

        while(k-- > 0 && exited.size() < m) {
            for (int i = 0; i < m; i++) {
                if(exited.contains(i)) continue;
                int x = participants[i][0];
                int y = participants[i][1];
                int dir = -1, minDist = getDistance(x, y);

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny].val > 0)
                        continue;
                    int nDist = getDistance(nx, ny);
                    if (minDist > nDist) {
                        minDist = nDist;
                        dir = j;
                    }
                }

                if (dir == -1)
                    continue;
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                map[x][y].removePeople(i);
                move++;
                if (nx == endX && ny == endY) {
                    exited.add(i);
                    continue;
                }
                participants[i][0] = nx;
                participants[i][1] = ny;
                map[nx][ny].addPeople(i);
            }

            if(exited.size() == m) {
                break;
            }

            /*
             * 2. 미로 회전 : 이동 다 끝나면 회전
             *  - 한명 이상 참가자와 출구를 포함한 가장 작은 정사각형 잡기(여러개면 좌상단 행작은거, 열작은거 순)
             *  - 시계방향으로 90도 회전하면서 벽 내구도 깎임
             */

            //가장 작은 정사각형 잡기
            int size = 2;
            int width = 0, sx=0, sy=0;

            while (size < n) {
                sx = Math.max(endX - size + 1, 0);
                sy = Math.max(endY - size + 1, 0);
                int initSy = sy;
                boolean flag = false;

                loop:
                for (; sx <= endX; sx++) {
                    for (sy = initSy; sy <= endY; sy++) {
                        for (int i = sx; i < sx + size && i >= 0 && i < n; i++) {
                            for (int j = sy; j < sy + size && j>=0 && j < n; j++) {
                                if (!map[i][j].people.isEmpty()) {
                                    flag = true;
                                    break loop;
                                }
                            }
                        }
                    }
                }

                if (flag) {
                    break;
                }
                size++;
            }

            //회전
            Area[][] newMap = new Area[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newMap[i][j] = map[i][j];
                }
            }

            for (int i = sx; i < sx + size && i >= 0 && i < n; i++) {
                for (int j = sy; j < sy + size && j>=0 && j < n; j++) {
                    if (map[i][j].val > 0) {
                        map[i][j].val--;
                    }
                    newMap[sx + j - sy][sy + size - 1 - (i - sx)] = map[i][j];
                    for (Integer person : map[i][j].people) {
                        participants[person][0] = sx + j - sy;
                        participants[person][1] = sy + size - 1 - (i - sx);
                    }
                }
            }
            map = newMap;

            int nEndX = sx + endY - sy;
            int nEndY = sy + size - 1 - (endX - sx);
            endX = nEndX;
            endY = nEndY;
        }

		br.close();
        System.out.println(move);
		System.out.print((endX+1) + " " + (endY+1));
	}

    static int getDistance(int x, int y) {
        return Math.abs(endX - x) + Math.abs(endY - y);
    }
}