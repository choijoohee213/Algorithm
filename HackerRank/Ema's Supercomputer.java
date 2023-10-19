import java.io.*;
import java.util.*;

class Result {

    public static int twoPluses(int n, int m, char[][] map) {
        List<int[]> result = new ArrayList<>();
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < m-1; j++) {
                if(map[i][j] == 'B') continue;
                int idx = 1;

                while(true) {
                    boolean success = true;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k] * idx;
                        int ny = j + dy[k] * idx;

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 'B') {
                            success = false;
                            break;
                        }
                    }

                    if(!success) {
                        break;
                    }
                    result.add(new int[]{1 + idx * 4, i, j, idx});
                    idx++;
                }
                result.add(new int[]{1, i, j, 0});
            }
        }

        result.sort(((o1, o2) -> Integer.compare(o2[0], o1[0])));
        int maxValue = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < result.size()-1; i++) {
            for (int j = i+1; j < result.size(); j++) {
                Set<Integer> idx = new HashSet<>();

                int[] r1 = result.get(i);
                int[] r2 = result.get(j);
                boolean success = true;

                idx.add(r1[1]*m+r1[2]);
                for (int k = 1; k <= r1[3]; k++) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = r1[1] + dx[dir] * k;
                        int ny = r1[2] + dy[dir] * k;
                        idx.add(nx*m+ny);
                    }
                }

                if(idx.contains(r2[1]*m+r2[2])) {
                    success = false;
                    continue;
                }

                idx.add(r2[1]*m+r2[2]);
                loop:
                for (int k = 1; k <= r2[3]; k++) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = r2[1] + dx[dir] * k;
                        int ny = r2[2] + dy[dir] * k;

                        if(idx.contains(nx*m+ny)) {
                            success = false;
                            break loop;
                        }
                        idx.add(nx*m+ny);
                    }
                }

                if(success) {
                    maxValue = Math.max(maxValue, r1[0] * r2[0]);
                }
            }
        }
        return maxValue;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        int m = Integer.parseInt(firstMultipleInput[1]);
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        int result = Result.twoPluses(n,m,map);

        bufferedReader.close();
        System.out.println(result);
    }
}