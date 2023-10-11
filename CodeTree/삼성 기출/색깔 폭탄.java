import java.io.*;
import java.util.*;

/**
 * -1(검정), 0(빨강), 1이상 m이하는 각각 다른색
 * 묶음이 존재 하지 않을떄까지 반복
 * 제거한 폭탄 개수의 제곱만큼 점수 얻음
 *
 * 1. 크기가 가장 큰 폭탄 묶음 찾기
 * - 같은 색깔이 2개 이상의 상하좌우 인접 폭탄
 * - 빨간색 폭탄을 포함하여 두개의 색깔로만 이루어진 것도 가능
 * - 빨간색만으로만 이루어진건 안됨
 * - 크기가 큰 폭탄 여러개라면 -> 빨간색 폭탄이 가장 적게 포함된 것 우선 -> 기준점의 행이 가장 큰 것 우선 -> 기준점의 열이 작은 것
 *      - 기준점 : 폭탄 묶음 안에서 빨간색이 아니면서 행이 가장 큰 칸, 행이 가장큰게 여러개면 열이 작은 칸
 * 2. 선택된 묶음의 폭탄 전부 제거
 * - 제거된 후 폭탄은 중력으로 떨어짐
 * - 돌(검정색)은 안떨어짐
 * 3. 반시계 방향으로 90도 맵 회전
 * 4. 중력이 작용하여 폭탄은 떨어짐
 */

class Main {
    static int n,m,score=0;
    static int[][] map;

    static class BombGroup implements Comparable<BombGroup>{
        int color;
        int colorCnt, redCnt;
        int pointX, pointY;  //기준점
        List<Integer> bombs;

        public BombGroup(int color, int colorCnt, int redCnt, int pointX, int pointY, List<Integer> bombs) {
            this.color = color;
            this.colorCnt = colorCnt;
            this.redCnt = redCnt;
            this.pointX = pointX;
            this.pointY = pointY;
            this.bombs = bombs;
        }

        @Override
        public int compareTo(BombGroup o) {
            if(bombs.size() == o.bombs.size()) {
                if(redCnt == o.redCnt) {
                    if(pointX == o.pointX) {
                        return Integer.compare(pointY, o.pointY); //4.기준점 열이 작은
                    }
                    return Integer.compare(o.pointX, pointX); //3.기준점 행이 큰
                }
                return Integer.compare(redCnt, o.redCnt);  //2.빨간폭탄 개수가 적은
            }
            return Integer.compare(o.bombs.size(), bombs.size());  //1.전체폭탄 개수가 많은
        }
    }

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            BombGroup group = findGroup();  //폭탄 묶음 찾기
            if(group == null) break;
            removeGroup(group);
            rotate();
            fallDown();
        }

		br.close();
		System.out.print(score);
	}

    /*
     * 1. 크기가 가장 큰 폭탄 묶음 찾기
     * - 같은 색깔이 2개 이상의 상하좌우 인접 폭탄
     * - 빨간색 폭탄을 포함하여 두개의 색깔로만 이루어진 것도 가능
     * - 빨간색만으로만 이루어진건 안됨
     * - 크기가 큰 폭탄 여러개라면 -> 빨간색 폭탄이 가장 적게 포함된 것 우선 -> 기준점의 행이 가장 큰 것 우선 -> 기준점의 열이 작은 것
     *      - 기준점 : 폭탄 묶음 안에서 빨간색이 아니면서 행이 가장 큰 칸, 행이 가장큰게 여러개면 열이 작은 칸
     */
    private static BombGroup findGroup() {
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
        boolean[][] visited = new boolean[n][n];
        List<BombGroup> groups = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] || map[i][j] <= 0) continue;

                Queue<int[]> q = new LinkedList<>();
                List<Integer> bombs = new ArrayList<>();
                int colorCnt = 1, redCnt = 0;
                int pointX = i, pointY = j;

                q.add(new int[]{i,j});
                visited[i][j] = true;
                initRedBomb(visited);

                while(!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.peek()[1];
                    q.poll();

                    bombs.add(x*n+y);

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny]
                                || (map[nx][ny] != map[i][j] && map[nx][ny] != 0)) continue;

                        if(map[nx][ny] == 0) {
                            redCnt++;
                        } else if(map[nx][ny] == map[i][j]){
                            colorCnt++;
                            if(pointX < nx || (pointX == nx && pointY > ny)) {
                                pointX = nx;
                                pointY = ny;
                            }
                        }
                        q.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
                if(colorCnt + redCnt >= 2) {
                    groups.add(new BombGroup(map[i][j], colorCnt, redCnt, pointX, pointY, bombs));
                }
            }
        }

        if(groups.isEmpty()) {
            return null;
        }
        Collections.sort(groups);
        return groups.get(0);
    }

    //빨간 폭탄의 방문 여부(visited) 초기화
    private static void initRedBomb(boolean[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 0 && visited[i][j]) {
                    visited[i][j] = false;
                }
            }
        }
    }

    /*
     * 2. 선택된 묶음의 폭탄 전부 제거
     * - 제거된 후 폭탄은 중력으로 떨어짐
     * - 돌(검정색)은 안떨어짐
     */
    private static void removeGroup(BombGroup group) {
        for (Integer bomb : group.bombs) {
            int x = bomb / n;
            int y = bomb % n;
            map[x][y] = -2;
        }
        score += group.bombs.size() * group.bombs.size();
        fallDown();
    }

    //중력 작용
    private static void fallDown() {
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >=0; j--) {
                if(map[j][i] == -2) {  //빈칸

                    boolean flag = false;
                    int x = j-1, y = i;
                    while(x>=0 && map[x][y] != -1) {
                        if(map[x][y] >= 0) {
                            flag = true;
                            break;
                        }
                        x--;
                    }

                    if(flag) {
                        map[j][i] = map[x][y];
                        map[x][y] = -2;
                    }
                }
            }
        }
    }

    //3. 반시계 방향 90도 회전
    private static void rotate() {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newMap[n-1-j][i] = map[i][j];
            }
        }
        map = newMap;
    }
}