import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int[] answer = {};
        int r = maps.length, c = maps[0].length(), idx = 0;
        char[][] map = new char[r][c];
        int[][] visited = new int[r][c];
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < maps.length; i++) {
            map[i] = maps[i].toCharArray();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] == 'X' || visited[i][j] != 0) continue;
                idx++;
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i,j});
                visited[i][j] = idx;
                result.add(map[i][j] - '0');

                while(!q.isEmpty()) {
                    int x = q.peek()[0];
                    int y = q.peek()[1];
                    q.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx < 0 || nx>=r || ny<0 || ny>=c || visited[nx][ny] != 0 || map[nx][ny] == 'X') continue;

                        int val = map[nx][ny] - '0';
                        result.set(idx-1, result.get(idx-1) + val);
                        visited[nx][ny] = idx;
                        q.add(new int[]{nx,ny});
                    }
                }
            }
        }
        if(result.isEmpty()) return new int[]{-1};
        return result.stream().sorted().mapToInt(value -> value).toArray();
    }
}