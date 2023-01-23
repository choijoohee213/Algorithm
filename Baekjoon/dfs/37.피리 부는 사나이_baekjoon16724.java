import java.io.*;
import java.util.*;

class Main {
    static Map<Character, Integer> dirIdx = new HashMap<>();
    static char[][] map;
    static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    static int cnt = 0, result = 0;

    public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int[][] visited = new int[n][m];
        dirIdx.put('D', 0);
        dirIdx.put('R', 1);
        dirIdx.put('U', 2);
        dirIdx.put('L', 3);

        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j] > 0) continue;
                cnt++;
                visited[i][j] = cnt;
                dfs(i,j,visited,cnt);
            }
        }
		br.close();
		System.out.print(result);
	}

    private static void dfs(int x, int y, int[][] visited, int cnt) {
        int dir = dirIdx.get(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(visited[nx][ny] == cnt) {
            result++;
            return;
        } else if(visited[nx][ny] > 0 && visited[nx][ny] != cnt) {
            return;
        }
        visited[nx][ny] = cnt;
        dfs(nx,ny,visited,cnt);
    }
}
