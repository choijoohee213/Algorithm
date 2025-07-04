import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x;
        int y;

        Point() {}

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
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
    static BufferedReader br;
    static int n,q, groupIdx = 1;
    static Map<Point, Integer> arr;
    static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
    static Map<Integer, List<Point>> microbeByGroup;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        arr = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr.put(new Point(i,j), 0);
            }
        }

        for (int i = 0; i < q; i++) {
            inputMicrobe();
            moveMicrobe();
            int result = recordResult();
            System.out.println(result);
        }
    }

    private static int recordResult() {
        Point p = new Point();
        boolean[][] visited = new boolean[n][n];
        boolean[][] checkedGroup = new boolean[250][250];
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p.x = i;
                p.y = j;
                int idx = arr.get(p);
                if(idx == 0 || visited[i][j]) continue;

                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i,j});
                visited[i][j] = true;

                while(!queue.isEmpty()) {
                    int x = queue.peek()[0];
                    int y = queue.peek()[1];
                    queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        p.x = nx;
                        p.y = ny;

                        if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny] || arr.get(p) == 0) continue;
                        if(arr.get(p) == idx) {
                            queue.add(new int[]{nx,ny});
                            visited[nx][ny] = true;
                        } else {
                            int nIdx = arr.get(p);
                            if(!checkedGroup[nIdx][idx] && !checkedGroup[idx][nIdx]) {
                                checkedGroup[nIdx][idx] = true;
                                checkedGroup[idx][nIdx] = true;
                                result += (microbeByGroup.get(idx).size() * microbeByGroup.get(nIdx).size());
                            }
                        }
                    }
                }

            }
        }
        return result;
    }

    private static void moveMicrobe() {
        Queue<int[]> movingOrder = new PriorityQueue<>((o1, o2)-> {
            if(o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o2[0], o1[0]);
        });

        for(Map.Entry<Integer, List<Point>> entry : microbeByGroup.entrySet()) {
            int idx = entry.getKey();
            int size = entry.getValue().size();
            movingOrder.add(new int[]{size, idx});
        }

        Map<Point, Integer> newArr = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newArr.put(new Point(i,j), 0);
            }
        }

        while(!movingOrder.isEmpty()) {
            int idx = movingOrder.poll()[1];
            int startX = 0, startY;
            Point p = new Point();
            boolean success = false;

            for (; startX < n; startX++) {
                if(success) break;

                for (startY = 0; startY < n; startY++) {
                    if(success) break;
                    p.x = startX;
                    p.y = startY;
                    if(newArr.get(p) != 0) continue;

                    success = true;

                    List<Point> groupPos = new ArrayList<>(microbeByGroup.get(idx));
                    int gapX = startX - groupPos.get(0).x;
                    int gapY = startY - groupPos.get(0).y;
                    for(Point pos : groupPos) {
                        int nx = pos.x + gapX;
                        int ny = pos.y + gapY;

                        p.x = nx;
                        p.y = ny;
                        if(nx< 0 || nx>=n || ny<0 || ny>=n || newArr.get(p) != 0) {
                            success = false;
                            break;
                        }
                    }

                    if(success) {
                        microbeByGroup.get(idx).clear();

                        for(Point pos : groupPos) {
                            int nx = pos.x + gapX;
                            int ny = pos.y + gapY;
                            newArr.put(new Point(nx,ny), idx);
                            microbeByGroup.get(idx).add(new Point(nx,ny));
                        }
                    }
                }
            }

        }

        arr.clear();
        arr.putAll(newArr);
    }

    private static void inputMicrobe() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        Set<Integer> eatenGroup = new HashSet<>();
        Point p = new Point();

        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                p.x = i;
                p.y = j;
                if(arr.get(p) > 0) {
                    eatenGroup.add(arr.get(p));
                }
                arr.put(p, groupIdx);
            }
        }

        Set<Integer> groupToRemove = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        Set<Integer> visitedGroup = new HashSet<>();

        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j]) continue;
                p.x = i;
                p.y = j;
                int idx = arr.get(p);
                if(!eatenGroup.contains(idx)) continue;

                visited[i][j] = true;
                queue.add(new int[]{i, j});
                if(!visitedGroup.add(idx)) {
                    groupToRemove.add(idx);
                }

                while(!queue.isEmpty()) {
                    int x = queue.peek()[0];
                    int y = queue.peek()[1];
                    queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        p.x = nx;
                        p.y = ny;
                        if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny] || idx != arr.get(p)) continue;

                        queue.add(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }

            }
        }

        microbeByGroup = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                p.x = i;
                p.y = j;
                int idx = arr.get(p);
                if(idx == 0) continue;
                if(groupToRemove.contains(idx)) {
                    arr.put(p, 0);
                    continue;
                }

                if(!microbeByGroup.containsKey(idx)) {
                    microbeByGroup.put(idx, new ArrayList<>());
                }
                microbeByGroup.get(idx).add(new Point(i,j));
            }
        }

        groupIdx++;
    }


}