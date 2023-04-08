import java.util.*;
import java.io.*;
import java.util.Map.Entry;

/** 4:30
 * 1. 술래는 중앙
 * 2. 도망자m명. 도망자는 좌우로만 움직이는 사람(오른쪽보고시작), 상하로만 움직이는 사람(아래쪽보고시작)
 * 3. 나무h개.
 *
 * 1. 먼저 도망자가 동시에 움직임(술래와의 거리가 3 이하인 도망자만)
 *      - 바라보는방향으로 한칸 움직일 때 격자 벗어나지 않는 경우 : 술래 있으면 안움직임, 나무 or 빈칸은 움직임)
 *      - 격자 벗어나는 경우 : 방향 반대로 틀어서 한칸 움직임(술래 있으면 안움직임)
 * 2. 술래 움직임 (달팽이 모양) : 끝에도달하면 다시거꾸로 중심으로. 를 반복
 *      - 이동방향이 틀어지는 지점에선 바로 방향 틀기(첫칸이나 중앙칸에서도)
 *      - 술래가 바라보는 방향으로 잇는 도망자 잡기(술래 칸 포함 3칸).
 *          - 나무가 있는 칸의 도망자 제외
 *          - 잡힌 도망자는 사라지고, 턴 수 * 현재 턴에서 잡은 도망자 수 점수 얻기
 * 3. 1,2번을 k번 반복 시 총 점수 출력.
 */

public class Main {
    static class Runner {
        int x,y;
        int d;

        public Runner(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public void changeDir() {
            d = (d+2) % 4;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[][] treeMap = new boolean[n][n];
        Map<Integer, List<Runner>> runners = new HashMap<>();
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
        int[] taggerDx = {-1,0,1,0}, taggerDy = {0,1,0,-1};
        int taggerX = n/2, taggerY = n/2, taggerDir = 0, dirCnt = 0, len = 1, lenCnt = 0;
        int score = 0;
        boolean taggerReversed = false;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            if(!runners.containsKey(x*n+y)) {
                runners.put(x*n+y, new ArrayList<>());
            }
            runners.get(x*n+y).add(new Runner(x,y,d));
        }
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            treeMap[x][y] = true;
        }

        for (int t = 1; t <= k; t++) {
            //1. 도망자 움직임
            Map<Integer, List<Runner>> newRunners = new HashMap<>();
            for (Entry<Integer, List<Runner>> runnerEntry : runners.entrySet()) {
                int pos = runnerEntry.getKey();
                int x = pos/n, y = pos % n;
                List<Runner> runner = runnerEntry.getValue();

                for (Runner run : runner) {
                    if(((int)Math.abs(taggerX - x) + (int)Math.abs(taggerY - y)) <= 3) {
                        int nx = x + dx[run.d];
                        int ny = y + dy[run.d];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                            run.changeDir();
                            nx = x + dx[run.d];
                            ny = y + dy[run.d];
                        }
                        if (taggerX != nx || taggerY != ny) {
                            run.x = nx;
                            run.y = ny;
                        }
                    }
                    if(!newRunners.containsKey(run.x*n+run.y)) {
                        newRunners.put(run.x*n+run.y, new ArrayList<>());
                    }
                    newRunners.get(run.x*n + run.y).add(run);
                }
            }
            runners = newRunners;

            //2. 술래 움직임
            taggerX += taggerDx[taggerDir];
            taggerY += taggerDy[taggerDir];

            //달팽이 방향 움직임
            if(taggerX == 0 && taggerY == 0) {
                taggerReversed = true;
                len = n-1;
                lenCnt = 0;
                dirCnt = -1;
                taggerDir = 2;
            } else if(taggerX == n/2 && taggerY == n/2) {
                taggerReversed = false;
                len = 1;
                lenCnt = 0;
                dirCnt = 0;
                taggerDir = 0;
            } else {
                if (++lenCnt == len) {
                    if(!taggerReversed) {
                        taggerDir = (taggerDir + 1) % 4;
                    } else {
                        taggerDir = (taggerDir + 3) % 4;
                    }
                    lenCnt = 0;
                    if (++dirCnt == 2) {
                        if(!taggerReversed) {
                            len++;
                        } else {
                            len--;
                        }
                        dirCnt = 0;
                    }
                }
            }

            //술래가 바라보는 방향으로 잇는 도망자 잡기(술래 칸 포함 3칸).
            // *          - 나무가 있는 칸의 도망자 제외
            // *          - 잡힌 도망자는 사라지고, 턴 수 * 현재 턴에서 잡은 도망자 수 점수 얻기
            int x = taggerX, y = taggerY, cnt = 0;
            for(int i=0; i<3; i++) {
                if(!treeMap[x][y] && runners.containsKey(x*n+y)) {
                    cnt += runners.get(x*n+y).size();
                    runners.remove(x*n+y);
                }
                x += taggerDx[taggerDir];
                y += taggerDy[taggerDir];
                if(x<0 || x>=n || y<0 || y>=n) break;
            }
            score += t * cnt;
        }
        System.out.print(score);
    }
}
