import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;

        Queue<int[]> q = new LinkedList<>(); //x,y,방향(가로:0, 세로:1),시간
        boolean[][][] visited = new boolean[n][n][3];
        q.add(new int[]{0, 0, 0, 0});
        visited[0][0][0] = true;

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.peek()[1];
            int dir = q.peek()[2];
            int time = q.peek()[3];
            q.poll();

            if((x == n-1 && y == n-2 && dir == 0) || (x == n-2 && y == n-1 && dir == 1)) {
                return time;
            }

            if(dir == 0) {  //가로 방향
                if(x - 1 >= 0 && board[x-1][y] == 0 && board[x-1][y+1] == 0) {
                    if(!visited[x-1][y][0]) {  //위 움직이기
                        q.add(new int[]{x - 1, y, 0, time + 1});
                        visited[x - 1][y][0] = true;
                    }
                    if(!visited[x-1][y][1]) {  //반시계 위 회전
                        q.add(new int[]{x-1, y, 1, time+1});
                        visited[x-1][y][1] = true;
                    }
                    if(!visited[x-1][y+1][1]) {  //시계 위 회전
                        q.add(new int[]{x-1, y+1, 1, time+1});
                        visited[x-1][y+1][1] = true;
                    }
                }
                if(x + 1 < n && board[x+1][y] == 0 && board[x+1][y+1] == 0) {
                    if(!visited[x+1][y][0]) {  //아래 움직이기
                        q.add(new int[]{x + 1, y, 0, time+1});
                        visited[x+1][y][0] = true;
                    }
                    if(!visited[x][y+1][1]) {  //반시계 아래 회전
                        q.add(new int[]{x, y+1, 1, time+1});
                        visited[x][y+1][1] = true;
                    }
                    if(!visited[x][y][1]) {  //시계 아래 회전
                        q.add(new int[]{x, y, 1, time+1});
                        visited[x][y][1] = true;
                    }
                }
                if(y + 2 < n && board[x][y+2] == 0 && !visited[x][y+1][0]) {  //오른쪽 움직이기
                    q.add(new int[]{x,y+1,0,time+1});
                    visited[x][y+1][0] = true;
                }
                if(y-1 >= 0 && board[x][y-1] == 0 && !visited[x][y-1][0]) {  //왼쪽 움직이기
                    q.add(new int[]{x, y-1 , 0, time+1});
                    visited[x][y-1][0] = true;
                }
            } else {  //세로 방향
                if(x-1 >= 0 && board[x-1][y] == 0 && !visited[x-1][y][1]) {  //위 움직이기
                    q.add(new int[]{x-1, y, 1, time+1});
                    visited[x-1][y][1] = true;
                }
                if(x + 2 < n && board[x+2][y] == 0 && !visited[x+1][y][1]) {  //아래로 움직이기
                    q.add(new int[]{x+1, y, 1, time+1});
                    visited[x+1][y][1] = true;
                }
                if(y + 1 < n && board[x][y+1] == 0 && board[x+1][y+1] == 0) {
                    if(!visited[x][y+1][1]) {  //오른쪽 움직이기
                        q.add(new int[]{x, y+1, 1, time+1});
                        visited[x][y+1][1] = true;
                    }
                    if(!visited[x][y][0]) {  //반시계 위 회전
                        q.add(new int[]{x, y, 0, time+1});
                        visited[x][y][0] = true;
                    }
                    if(!visited[x+1][y][0]) {  //시계 아래 회전
                        q.add(new int[]{x+1, y, 0, time+1});
                        visited[x+1][y][0] = true;
                    }
                }
                if(y-1 >= 0 && board[x][y-1] == 0 && board[x+1][y-1] == 0) {
                    if(!visited[x][y-1][1]) {  //왼쪽 움직이기
                        q.add(new int[]{x, y - 1, 1, time + 1});
                        visited[x][y - 1][1] = true;
                    }
                    if(!visited[x+1][y-1][0]) {  //반시계 아래 회전
                        q.add(new int[]{x+1, y-1, 0, time + 1});
                        visited[x+1][y-1][0] = true;
                    }
                    if(!visited[x][y-1][0]) {  //시계 위 회전
                        q.add(new int[]{x, y-1, 0, time+1});
                        visited[x][y-1][0] = true;
                    }
                }
            }
        }
        return 0;
    }
}