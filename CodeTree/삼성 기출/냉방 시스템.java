import java.io.*;
import java.util.*;


class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        List<int[]> airConditioner = new ArrayList<>();
        List<Integer> companies = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int d = Integer.parseInt(st.nextToken());

                if(d >= 2) {
                    airConditioner.add(new int[]{i,j,d});
                } else if(d == 1) {
                    companies.add(i*n+j);
                }
            }
        }

        boolean[][][][] isWall = new boolean[n][n][n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            if(s == 0) {
                isWall[x-1][y][x][y] = true;
                isWall[x][y][x-1][y] = true;
            } else {
                isWall[x][y-1][x][y] = true;
                isWall[x][y][x][y-1] = true;
            }
        }

        //왼, 위, 오, 아래
        int[][] dx = {{0,-1,1},{-1,-1,-1},{0,-1,1},{1,1,1}};
        int[][] dy = {{-1,-1,-1},{0,1,-1},{1,1,1},{0,1,-1}};
        int[][] tx = {{0,-1,1},{-1,0,0},{0,-1,1},{1,0,0}};
        int[][] ty = {{-1,0,0},{0,1,-1},{1,0,0},{0,1,-1}};

        int time = 1;
        while (time <= 100) {
            //1. 에어컨 바람 퍼짐
            for (int[] ac : airConditioner) {
                int dir = ac[2] - 2;

                boolean[][] visited = new boolean[n][n];
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{ac[0] + dx[dir][0], ac[1] + dy[dir][0], 5});

                while (!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.peek()[1];
                    int cnt = q.peek()[2];
                    q.poll();

                    map[x][y] += cnt;

                    if (cnt == 1)
                        continue;

                    for (int i = 0; i < 3; i++) {
                        int nx = x + dx[dir][i];
                        int ny = y + dy[dir][i];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny])
                            continue;

                        int tnx = x + tx[dir][i];
                        int tny = y + ty[dir][i];
                        if (isWall[x][y][tnx][tny] || isWall[tnx][tny][nx][ny])
                            continue;

                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, cnt - 1});
                    }
                }
            }

            //2. 공기 섞임
            int[][] newMap = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newMap[i][j] += map[i][j];

                    if (j + 1 < n && !isWall[i][j][i][j + 1]) {
                        int d = Math.abs(map[i][j] - map[i][j + 1]) / 4;

                        if (map[i][j] > map[i][j + 1]) {
                            newMap[i][j] -= d;
                            newMap[i][j + 1] += d;
                        } else {
                            newMap[i][j] += d;
                            newMap[i][j + 1] -= d;
                        }
                    }

                    if (i + 1 < n && !isWall[i][j][i + 1][j]) {
                        int d = Math.abs(map[i][j] - map[i + 1][j]) / 4;

                        if (map[i][j] > map[i + 1][j]) {
                            newMap[i][j] -= d;
                            newMap[i + 1][j] += d;
                        } else {
                            newMap[i][j] += d;
                            newMap[i + 1][j] -= d;
                        }
                    }
                }
            }

            map = newMap;

            //3. 외벽 칸 1 감소
            for (int i = 0; i < n; i++) {
                if (map[0][i] != 0) {
                    map[0][i]--;
                }
                if (map[i][0] != 0 && i != 0) {
                    map[i][0]--;
                }
                if (map[i][n - 1] != 0 && i != 0) {
                    map[i][n - 1]--;
                }
                if (map[n - 1][i] != 0 && i != 0 && i != n - 1) {
                    map[n - 1][i]--;
                }
            }

            //사무실 모두 k이상인지 검사
            boolean success = true;
            for (Integer company : companies) {
                int x = company/n;
                int y = company%n;
                if(map[x][y] < k) {
                    success = false;
                    break;
                }
            }

            if(success) {
                break;
            }
            time++;
        }

		br.close();
		System.out.print(time == 101 ? -1 : time);
	}
}