#include <bits/stdc++.h>
using namespace std;

int m, n;
int dx[] = {0,1,0,-1};
int dy[] = {1,0,-1,0};

struct Point {
    int x;
    int y;
    int lever;
    int val;
};

int bfs(int startX, int startY, vector<string>& maps) {
    queue<Point> q;
    q.push({startX, startY, 0, 0});
    bool visited[m][n][2];
    fill(&visited[0][0][0], &visited[0][0][0] + m*n*2, false);
    visited[startX][startY][0] = true;

    while(!q.empty()) {
        Point p = q.front(); q.pop();

        for(int dir=0; dir<4; dir++) {
            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];

            if(nx<0 || nx>=m || ny<0 || ny>=n || maps[nx][ny] == 'X') continue;
            int nextLever = p.lever;
            if(p.lever == 0 && maps[nx][ny] == 'L') {
                nextLever = 1;
            }

            if(visited[nx][ny][nextLever]) continue;

            visited[nx][ny][nextLever] = true;
            q.push({nx, ny, nextLever, p.val + 1});

            if(maps[nx][ny] == 'E' && nextLever == 1) {
                return p.val + 1;
            }         
        }
    }
    
    return -1;
}

int solution(vector<string> maps) {
    m = maps.size();
    n = maps[0].length();    
    int startX = 0, startY = 0;
    bool find = false;
    
    for(int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            char c = maps[i][j];

            if(c == 'S') {
                startX = i;
                startY = j;
                find = true;
                break;
            }
        }
        if(find) break;
    }

    return bfs(startX, startY, maps);
}