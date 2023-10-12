import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * k시간 후 살아있는 줄기세포(비활성 + 활성) 총 개수 구하기
 *
 * 초기 : 비활성 상태(x시간동안)
 * 활성
 *  - x시간동안 살아있은 후 죽음
 *  - 첫1시간동안 네방향으로 동시에 번식(번식된 곳은 비활성)
 *  - 이미 줄기세포가 있는곳으론 번식 못함
 *  - 두개이상이 같은곳으로 동시 번식하려고하는 경우 생명력 수치가 높은 줄기세포가 함
 */

class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Cell {
        int x, y;
        int health;
        int createTime;
        int state; //0: 비활성, 1: 활성, 2: 죽음

        public Cell(int x, int y, int health, int createTime, int state) {
            this.x = x;
            this.y = y;
            this.health = health;
            this.createTime = createTime;
            this.state = state;
        }
    }

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            Map<Point, Cell> cells_lived = new HashMap<>();
            Set<Point> cells_dead = new HashSet<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int health = Integer.parseInt(st.nextToken());
                    if(health == 0) continue;
                    cells_lived.put(new Point(i,j), new Cell(i,j, health,0,0));
                }
            }

            int[] dx= {0,1,0,-1}, dy = {1,0,-1,0};
            for (int time = 1; time <= k; time++) {
                Map<Point, Cell> newCells = new HashMap<>();
                List<Point> dead = new ArrayList<>();

                for (Entry<Point, Cell> cellEntry : cells_lived.entrySet()) {
                    int x = cellEntry.getKey().x;
                    int y = cellEntry.getKey().y;
                    Cell cell = cellEntry.getValue();

                    if(cell.state == 2) continue;

                    if(cell.state == 0 && (cell.createTime + cell.health) == time) {
                        cell.state = 1;

                    } else if(cell.state == 1) {
                        if(cell.createTime + cell.health + 1 == time) {
                            //번식
                            for (int i = 0; i < 4; i++) {
                                int nx = x + dx[i];
                                int ny = y + dy[i];
                                Point next = new Point(nx,ny);

                                if(cells_lived.containsKey(next) || cells_dead.contains(next)) continue;

                                if((newCells.containsKey(next) && newCells.get(next).health < cell.health)
                                    || !newCells.containsKey(next)) {
                                        newCells.put(next, new Cell(nx,ny,cell.health,time,0));
                                }
                            }
                        }
                        if(cell.createTime + cell.health * 2 == time) {
                            cell.state = 2;
                            dead.add(new Point(x,y));
                        }
                    }

                }

                cells_lived.putAll(newCells);

                for (Point point : dead) {
                    cells_lived.remove(point);
                }
                cells_dead.addAll(dead);
            }

            sb.append('#').append(tc).append(' ').append(cells_lived.size()).append('\n');
        }
		br.close();
		System.out.print(sb);
	}
}