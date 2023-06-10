import java.util.*;
import java.io.*;

public class Main {
    static class Path {
        int r,c;
        List<Integer> paths;

        public Path(int r, int c, List<Integer> paths) {
            this.r = r;
            this.c = c;
            this.paths = paths;
        }
    }
    static class God {
        int r,c;
        int power;
        int recentPowerUp;

        public God(int r, int c, int power, int recentPowerUp) {
            this.r = r;
            this.c = c;
            this.power = power;
            this.recentPowerUp = recentPowerUp;
        }
    }

    static int n,m,k;
    static List<God> gods;
    static God[][] map;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    static Set<Integer> affected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new God[n][m];
        gods = new ArrayList<>();
        affected = new HashSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int power = Integer.parseInt(st.nextToken());
                if(power > 0) {
                    map[i][j] = new God(i,j,power,k);
                    gods.add(map[i][j]);
                }
            }
        }

        while(k-- > 0) {
            if(gods.size() <= 1) break;
            affected = new HashSet<>();
            God poweredUpGod = powerUp();
            poweredUpGod.recentPowerUp = k;
            attack(poweredUpGod);
            savePower();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == null) continue;
                result = Math.max(result, map[i][j].power);
            }
        }
        System.out.println(result);
    }

    private static void savePower() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(affected.contains(i*m+j) || map[i][j] == null) continue;
                map[i][j].power++;
            }
        }
    }

    private static void attack(God poweredUpGod) {
        God bossGod = null;
        for (int i = gods.size()-1; i>=0; i--) {
            if(!gods.get(i).equals(poweredUpGod))  {
                bossGod = gods.get(i);
                break;
            }
        }

        Queue<Path> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new Path(poweredUpGod.r, poweredUpGod.c, new ArrayList<>()));
        visited[poweredUpGod.r][poweredUpGod.c] = true;
        boolean success = false;
        List<Integer> finalPath = null;

        loop:
        while(!q.isEmpty()) {
            Path path = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = path.r + dx[i];
                int ny = path.c + dy[i];

                if(nx>=n) {
                    nx = 0;
                }
                if(nx<0) {
                    nx = n-1;
                }
                if(ny>=m) {
                    ny = 0;
                }
                if(ny < 0) {
                    ny = m-1;
                }

                if(visited[nx][ny] || map[nx][ny] == null) continue;
                if(nx == bossGod.r && ny == bossGod.c) {
                    success = true;
                    finalPath = path.paths;
                    break loop;
                }
                visited[nx][ny] = true;
                List<Integer> newPaths = new ArrayList<>(path.paths);
                newPaths.add(nx*m+ny);
                q.add(new Path(nx,ny,newPaths));
            }
        }

        if(success) {
            affected.add(bossGod.r*m+ bossGod.c);
            map[bossGod.r][bossGod.c].power -= poweredUpGod.power;
            if(map[bossGod.r][bossGod.c].power <= 0) {
                gods.remove(map[bossGod.r][bossGod.c]);
                map[bossGod.r][bossGod.c] = null;
            }

            for (Integer path : finalPath) {
                int x = path /m;
                int y = path % m;

                if(map[x][y] == null) continue;
                map[x][y].power -= poweredUpGod.power / 2;
                affected.add(x*m+ y);
                if(map[x][y].power <= 0) {
                    gods.remove(map[x][y]);
                    map[x][y] = null;
                }
            }
            return;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = bossGod.r + i, ny = bossGod.c + j;

                if(nx>=n) {
                    nx = 0;
                }
                if(nx<0) {
                    nx = n-1;
                }
                if(ny>=m) {
                    ny = 0;
                }
                if(ny < 0) {
                    ny = m-1;
                }

                if((nx == poweredUpGod.r && ny == poweredUpGod.c) || map[nx][ny] == null) continue;
                affected.add(nx*m+ ny);

                if(i == 0 && j ==0) {
                    map[nx][ny].power -= poweredUpGod.power;
                } else {
                    map[nx][ny].power -= poweredUpGod.power/2;
                }
                if(map[nx][ny].power <= 0) {
                    gods.remove(map[nx][ny]);
                    map[nx][ny] = null;
                }
            }
        }
    }

    private static God powerUp() {
        gods.sort(new Comparator<God>() {
            @Override
            public int compare(God o1, God o2) {
                if (o1.power != o2.power) {
                    return Integer.compare(o1.power, o2.power);
                }
                if (o1.recentPowerUp != o2.recentPowerUp) {
                    return Integer.compare(o1.recentPowerUp, o2.recentPowerUp);
                }
                if ((o1.r + o1.c) != (o2.r + o2.c)) {
                    return Integer.compare((o2.r + o2.c), (o1.r + o1.c));
                }
                return Integer.compare(o2.c, o1.c);
            }
        });
        God god = gods.get(0);
        affected.add(god.r*m+ god.c);
        god.power += (n+m);
        return god;
    }
}