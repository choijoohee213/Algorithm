import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int map[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[][] group = new int[m][n];
        List<Integer> roomCntList = new ArrayList<>();
        int groupCnt = 0, maxRoomCnt = 0;
        int[] dx = {0,-1,0,1}, dy = {-1,0,1,0};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(group[i][j] > 0) continue;
                queue.add(i*n+j);
                groupCnt++;
                group[i][j] = groupCnt;
                int roomCnt = 0;

                while(!queue.isEmpty()) {
                    int x = queue.peek() / n;
                    int y = queue.peek() % n;
                    queue.poll();
                    roomCnt++;

                    int info = map[x][y];

                    for(int k=0; k<4; k++) {
                        if(info > 0) {
                            int num = 1 << k;
                            if((info & num) == num) continue;
                        }
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx < 0 || nx >= m || ny < 0 || ny >= n || group[nx][ny] > 0) continue;
                        group[nx][ny] = groupCnt;
                        queue.add(nx*n+ny);
                    }
                }
                maxRoomCnt = Math.max(maxRoomCnt, roomCnt);
                roomCntList.add(roomCnt);
            }
        }

        queue.clear();
        int maxAddedRoomCnt = 0;
        boolean[] groupChecked = new boolean[groupCnt+1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(groupChecked[group[i][j]]) continue;

                int curGroup = group[i][j];
                int curGroupRoomCnt = roomCntList.get(curGroup-1);

                boolean[][] visited = new boolean[m][n];
                groupChecked[curGroup] = true;
                visited[i][j] = true;
                queue.add(i*n+j);

                while(!queue.isEmpty()) {
                    int x = queue.peek() / n;
                    int y = queue.peek() % n;
                    queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx<0 || nx>=m || ny<0 || ny>=n) continue;

                        int nextGroup = group[nx][ny];
                        if(nextGroup != curGroup) {
                            int nextGroupRoomCnt = roomCntList.get(nextGroup-1);
                            maxAddedRoomCnt = Math.max(maxAddedRoomCnt, curGroupRoomCnt + nextGroupRoomCnt);
                            continue;
                        }

                        if(!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.add(nx*n+ny);
                        }
                    }
                }
            }
        }

		br.close();
		System.out.println(groupCnt);
		System.out.println(maxRoomCnt);
		System.out.println(maxAddedRoomCnt);
	}
}