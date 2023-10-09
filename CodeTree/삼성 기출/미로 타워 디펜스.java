import java.io.*;
import java.util.*;

/**
 * 1,3번에서 몬스터 없애면서 번호만큼 점수 추가됨
 *
 * 1. 몬스터 없애기 : 상하좌우 방향중 공격칸수 만큼 공격하여 없앰
 * 2. 빈 공간 채우기 : 빈만큼 몬스터가 앞으로 이동하여 채움
 * 3. 몬스터 사라짐
 * - 같은종류가 4번이상 반복하면 동시에 삭제
 * - 삭제 후 당겨서 빈 공간 채우기
 * -> 이후 또 반복 잇으면 삭제(같은종류 4번 반복 없을때까지 계속함)
 * 4. 몬스터 쭉 나열하고 짝 짓기
 * - 차례대로 나열 후 붙어잇는 같은 숫자끼리 짝지음
 * - 각 짝을 (개수, 숫자)로 바꾸어 미로에 다시 넣음 (맵 넘으면 나머지는 무시)
 *
 */

class Main {
    static int n,m,mid,score=0;
    static int[][] map, attackInfo;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mid = n/2;
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        attackInfo = new int[m][2];  //방향, 칸수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            attackInfo[i][0] = Integer.parseInt(st.nextToken());
            attackInfo[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int round = 0; round < m; round++) {
            attack(round);  //몬스터 공격
            Queue<Integer> q = deleteRepeatedMonster(fillEmpty());  //빈 공간 채운 후, 네개이상 반복 몬스터 삭제
            fillMap(q);
        }

		br.close();
		System.out.print(score);
	}

    //1. 몬스터 없애기 : 상하좌우 방향중 공격칸수 만큼 공격하여 없앰
    private static void attack(int round) {
        int d = attackInfo[round][0];
        int p = attackInfo[round][1];

        int x = mid, y = mid;

        while(p-->= 0 && x>=0 && x<n && y>=0 && y<n) {
            score += map[x][y];
            map[x][y] = 0;
            x += dx[d];
            y += dy[d];
        }

    }

    //2. 빈 공간 채우기 : 빈 만큼 몬스터가 앞으로 이동하여 채움
    //좌,하,우,상
    private static Queue<Integer> fillEmpty() {
        boolean flag = false;
        int x = mid, y = mid, cnt = 1, dir = 2;
        Queue<Integer> q = new LinkedList<>();

        loop:
        while(true) {
            for (int i = 0; i < cnt; i++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx<0 || nx>=n || ny<0 || ny>=n) break loop;
                if(map[nx][ny] != 0) q.add(map[nx][ny]);
                x = nx;
                y = ny;
            }
            if(flag) {
                cnt++;
            }
            flag = !flag;
            dir = (dir + 3) % 4;
        }
        return q;
    }

    /*
     * 3. 몬스터 사라짐
     * - 같은종류가 4번이상 반복하면 동시에 삭제
     * - 삭제 후 당겨서 빈 공간 채우기
     * -> 이후 또 반복 잇으면 삭제(같은종류 4번 반복 없을때까지 계속함)
     */
    private static Queue<Integer> deleteRepeatedMonster(Queue<Integer> q) {
        while(true) {
            boolean flag = false;
            Queue<Integer> newQ = new LinkedList<>();
            Queue<Integer> group = new LinkedList<>();

            int prev = 0, cnt = 1;
            while (!q.isEmpty()) {
                int cur = q.poll();

                if (prev == cur) {
                    cnt++;
                } else {
                    if (cnt >= 4) {
                        score += cnt * prev;
                        flag = true;
                    } else {
                        newQ.addAll(group);
                    }
                    group = new LinkedList<>();
                    cnt = 1;
                    prev = cur;
                }
                group.add(cur);
            }

            if (cnt >= 4) {
                score += cnt * prev;
                flag = true;
            } else {
                newQ.addAll(group);
            }
            q = newQ;
            if(!flag) {
                break;
            }
        }
        return q;
    }


    /*
     * 4. 몬스터 쭉 나열하고 짝 짓기
     * - 차례대로 나열 후 붙어잇는 같은 숫자끼리 짝지음
     * - 각 짝을 (개수, 숫자)로 바꾸어 미로에 다시 넣음 (맵 넘으면 나머지는 무시)
     */
    private static void fillMap(Queue<Integer> q) {
        //같은 숫자끼리 짝 짓기
        Queue<Integer> pairs = new LinkedList<>();
        int prev = 0, cnt = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (prev == cur) {
                cnt++;
            } else {
                if(prev != 0) {
                    pairs.add(cnt);
                    pairs.add(prev);
                }
                cnt = 1;
                prev = cur;
            }
        }
        if(prev != 0) {
            pairs.add(cnt);
            pairs.add(prev);
        }

        //미로에 집어넣기
        boolean flag = false;
        int x = mid, y = mid, dir = 2;
        cnt = 1;
        map = new int[n][n];

        loop:
        while(true) {
            for (int i = 0; i < cnt; i++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx<0 || nx>=n || ny<0 || ny>=n || pairs.isEmpty()) break loop;
                map[nx][ny] = pairs.poll();
                x = nx;
                y = ny;
            }
            if(flag) {
                cnt++;
            }
            flag = !flag;
            dir = (dir + 3) % 4;
        }
    }
}