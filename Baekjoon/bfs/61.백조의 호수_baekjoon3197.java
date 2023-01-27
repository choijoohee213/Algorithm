import java.io.*;
import java.util.*;

class Main {
    static int r,c;
    static char[][] map;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    static int[][] swans = new int[2][2];
    static Queue<int[]> meltQueue = new LinkedList<>();
    static Queue<int[]> swanQueue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        boolean[][] swanVisited = new boolean[r][c];

        int idx = 0;

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'L') {
                    swans[idx][0] = i;
                    swans[idx++][1] = j;
                }
                if(map[i][j] != '.') {
                    meltQueue.add(new int[]{i,j});
                }
            }
        }
        swanQueue.add(new int[]{swans[0][0], swans[0][1]});
        swanVisited[swans[0][0]][swans[0][1]] = true;

        while(!swansCanMeet(swanVisited)) {
            melt();
            answer++;
        }

        br.close();
		System.out.print(answer);
	}

    private static boolean swansCanMeet(boolean[][] swanVisited) {
        Queue<int[]> nextQueue = new LinkedList<>();

        while(!swanQueue.isEmpty()) {
            int x = swanQueue.peek()[0];
            int y = swanQueue.peek()[1];
            swanQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || nx>=r || ny<0 || ny>=c || swanVisited[nx][ny]) continue;
                swanVisited[nx][ny] = true;
                if(nx == swans[1][0] && ny == swans[1][1]) {
                    return true;
                } else if(map[nx][ny] == 'X') {
                    nextQueue.add(new int[]{nx,ny});
                } else {
                    swanQueue.add(new int[]{nx, ny});
                }
            }
        }
        swanQueue = nextQueue;
        return false;
    }

    private static void melt() {
        int size = meltQueue.size();

        for(int i=0; i<size; i++) {
            int x = meltQueue.peek()[0];
            int y = meltQueue.peek()[1];
            meltQueue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
                if(map[nx][ny] == 'X') { //물에 닿아있는 얼음 -> 녹아야함
                    map[nx][ny] = '.';
                    meltQueue.add(new int[]{nx,ny});
                }
            }
        }
    }
}
