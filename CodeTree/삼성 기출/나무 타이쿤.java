import java.io.*;
import java.util.*;

/**
 * 특수영양제는 나무 높이 + 1, 씨앗일땐(0) 높이 1 나무로.
 * 초기 영양제 위치는 좌하단 4칸
 * 모든 년수가 지나고 남은 나무 높이의 총합
 *
 * 1. 영양제 이동
 * - 정해진 방향(8방 중 하나)으로 칸수만큼 이동
 * - 맵 행,열 이어져잇음
 * 2. 영앙제 투입 하고 영양제 사라짐
 * 3. 나무 성장
 * - 영양제 투입된 나무의 대각선 인접 방향에 높이가 1이상인 나무가 있는만큼 높이 더 성장
 * - 대각선 인접 방향 나무가 맵 벗어나면 세지않음
 * 4.영양제 구입
 * - 영양제 투입된 나무를 제외하고 높이가 2 이상인 나무는 2만큼 베어서 영양제 구입
 * - 해당 위치에 영양제 올려두기
 *
 */

class Main {
    static int n,m;
    static int[][] map, moveInfo;
    static int[] dx = {0,-1,-1,-1,0,1,1,1}, dy = {1,1,0,-1,-1,-1,0,1};
    static Set<Integer> supplements = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        moveInfo = new int[m][2];  //이동 방향, 칸 수

        //초기 영양제 위치는 좌하단 4칸
        supplements.add((n-2)*n);
        supplements.add((n-2)*n+1);
        supplements.add((n-1)*n);
        supplements.add((n-1)*n+1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            moveInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
            moveInfo[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int year = 0; year < m; year++) {
            move(year);
            nourish();
            buy();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += map[i][j];
            }
        }

		br.close();
		System.out.print(result);
	}

    /*
     * 1. 영양제 이동
     * - 정해진 방향(8방 중 하나)으로 칸수만큼 이동
     * - 맵 행,열 이어져잇음
     */
    private static void move(int year) {
        Set<Integer> newSupplements = new HashSet<>();
        int d = moveInfo[year][0];
        int p = moveInfo[year][1];

        for (Integer supplement : supplements) {
            int x = supplement / n;
            int y = supplement % n;

            int nx = (x + dx[d] * p) % n;
            int ny = (y + dy[d] * p) % n;

            if(nx<0) nx = (nx + n) % n;
            if(ny<0) ny = (ny + n ) % n;
            newSupplements.add(nx*n+ny);
        }
        supplements = newSupplements;
    }

    /*
     * 2. 영앙제 투입 하고 영양제 사라짐
     * 3. 나무 성장
     * - 영양제 투입된 나무의 대각선 인접 방향에 높이가 1이상인 나무가 있는만큼 높이 더 성장
     * - 대각선 인접 방향 나무가 맵 벗어나면 세지않음
     */
    private static void nourish() {
        //2. 영양제 투입
        for (Integer supplement : supplements) {
            int x = supplement / n;
            int y = supplement % n;
            map[x][y]++;
        }

        //3. 나무 성장
        int[][] totalCnt = new int[n][n];
        for (Integer supplement : supplements) {
            int x = supplement / n;
            int y = supplement % n;
            int cnt = 0;

            for (int i = 1; i < 8; i+=2) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                if(map[nx][ny] > 0) {
                    cnt++;
                }
            }
            totalCnt[x][y] += cnt;
        }

        for (Integer supplement : supplements) {
            int x = supplement / n;
            int y = supplement % n;
            map[x][y] += totalCnt[x][y];
        }
    }


    /*
     * 4.영양제 구입
     * - 영양제 투입된 나무를 제외하고 높이가 2 이상인 나무는 2만큼 베어서 영양제 구입
     * - 해당 위치에 영양제 올려두기
     */
    private static void buy() {
        Set<Integer> newSupplements = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(supplements.contains(i*n+j) || map[i][j] < 2) continue;
                map[i][j] -= 2;
                newSupplements.add(i*n+j);
            }
        }
        supplements = newSupplements;
    }
}