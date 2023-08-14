import java.io.*;
import java.util.*;
import java.util.LinkedList;

class Main {
    static int h,w;
    static char[][] map;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;

            map = new char[h][w];
            List<Integer> startPos = new ArrayList<>();
            startPos.add(0);

            for (int i = 0; i < h; i++) {
                if(i == 0 || i == h-1) {
                    Arrays.fill(map[i], '.');
                    continue;
                }
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    if (j == 0 || j == w-1) {
                        map[i][j] = '.';
                    } else {
                        map[i][j] = input.charAt(j-1);
                        if(map[i][j] == '$') {
                            startPos.add(i*w+j);
                        }
                    }
                }
            }

            int[][][] result = new int[3][h][w];
            for (int i = 0; i < 3; i++) {
                result[i] = bfs(startPos.get(i)/w, startPos.get(i)%w);
            }

            int ans = Integer.MAX_VALUE;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(map[i][j] == '*') continue;
                    int sum = 0;
                    for (int k = 0; k < 3; k++) {
                        sum += result[k][i][j];
                    }

                    if(map[i][j] == '#') {
                        sum -= 2;
                    }
                    ans = Math.min(ans, sum);
                }
            }
            sb.append(ans).append('\n');
        }
		br.close();
		System.out.print(sb);
	}

    private static int[][] bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        visited[startX][startY] = 0;
        q.add(new int[]{startX, startY, 0});

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int door = q.peek()[2];
            q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nDoor = door;

                if(nx < 0 || nx>=h || ny<0 || ny>=w || map[nx][ny] == '*') continue;
                if (map[nx][ny] == '#') {
                    nDoor++;
                }
                if(visited[nx][ny] == Integer.MAX_VALUE || visited[nx][ny] > nDoor) {
                    visited[nx][ny] = nDoor;
                    q.add(new int[]{nx, ny, nDoor});
                }
            }
        }
        return visited;
    }
}