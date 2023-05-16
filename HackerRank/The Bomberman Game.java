import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static List<String> bomberMan(int r, int c, int n, List<String> grid) {
        if(n == 1) {
            return grid;
        }

        Queue<int[]> bombs = new LinkedList<>();
        char[][] map = new char[r][c];
        boolean[][] isDestroyed = new boolean[r][c];
        int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                map[i][j] = grid.get(i).charAt(j);
                if(map[i][j] == 'O') {
                    bombs.add(new int[]{i,j,0});
                }
            }
        }

        if(n>=6) {
            n %= 4;
            if(n <= 1) {
                n += 4;
            }
        }

        for(int time=2; time<=n; time++) {
            if(time % 2 == 0) {  //plant
                for(int i=0; i<r; i++) {
                    for(int j=0; j<c; j++) {
                        if(map[i][j] == 'O')  continue;
                        bombs.add(new int[]{i,j,time});
                        map[i][j] = 'O';
                    }
                }
            } else { //detonate
                Set<Integer> pos = new HashSet<>();
                boolean[][] isNewDestroyed = new boolean[r][c];

                while(!bombs.isEmpty()) {
                    if(isDestroyed[bombs.peek()[0]][bombs.peek()[1]]) {
                        bombs.poll();
                        continue;
                    }
                    if(time - 3 != bombs.peek()[2]) break;
                    int[] bomb = bombs.poll();
                    pos.add(bomb[0]*c+bomb[1]);

                    for(int i=0; i<4; i++) {
                        int nx = bomb[0] + dx[i];
                        int ny = bomb[1] + dy[i];

                        if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
                        if(map[nx][ny] == 'O') {
                            isNewDestroyed[nx][ny] = true;
                            pos.add(nx*c+ny);
                        }
                    }
                }
                isDestroyed = isNewDestroyed;

                for(int bombPos : pos) {
                    int x = bombPos / c;
                    int y = bombPos % c;

                    map[x][y] = '.';
                }
            }
        }

        List<String> result = new ArrayList<>();

        for(int i=0; i<r; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<c; j++) {
                sb.append(map[i][j]);
            }
            result.add(sb.toString());
        }

        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);
        int c = Integer.parseInt(firstMultipleInput[1]);
        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = Result.bomberMan(r, c, n, grid);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
