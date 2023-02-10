/**
 * dfs + dp
 * 갔던 경로가 k일때 도착할 수 없었으면 그 지점으로부터 k로 도착할수있는 경로는 없는것
 * 즉, 그 지점은 다시 가더라도 그 뒤로 안가봐도됨.(dp로 저장)
 * 사전순 : d , l, r, u;
 */

class Solution {
    int[][][] dp;
    int[] start, end, answer;
    int[] dx = {1,0,0,-1}, dy = {0,-1,1,0};
    int N, M, dist;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if((k - Math.abs(x-r) + Math.abs(c-y)) % 2 == 1){
            return "impossible";
        }
        dp = new int[n][m][k+1];
        start = new int[]{x-1,y-1};
        end = new int[]{r-1,c-1};
        N = n;
        M = m;
        dist = k;
        dfs(0, x-1, y-1, new int[k]);

        if(dp[x-1][y-1][0] == 2) {
            return "impossible";
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i : answer) {
                if(i == 0) {
                    sb.append('d');
                } else if(i == 1) {
                    sb.append('l');
                } else if(i == 2) {
                    sb.append('r');
                } else {
                    sb.append('u');
                }
            }
            return sb.toString();
        }
    }

    private int dfs(int cnt, int x, int y, int[] result) {
        if(cnt == dist) {
            if(x == end[0] && y == end[1]) {
                answer = result;
                return 1;
            } else {
                return 2;
            }
        }

        if(dp[x][y][cnt] != 0) { //0:방문안함,1:도착함,2:도착못함
            return dp[x][y][cnt];
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
            result[cnt] = i;
            if(dfs(cnt+1, nx, ny, result) == 1) {
                return dp[x][y][cnt] = 1;
            }
        }
        return dp[x][y][cnt] = 2;
    }
}