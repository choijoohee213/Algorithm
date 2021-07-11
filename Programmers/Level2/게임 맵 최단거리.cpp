#include<bits/stdc++.h>
using namespace std;

int solution(vector<vector<int> > maps)
{
    int n = maps.size(), m = maps[0].size();
    vector<vector<int>> visited(n, vector<int>(m, 0));
    queue<pair<int,int>> q;
    int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
    
    q.push({0,0});
    visited[0][0] = 1;
    
    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second; q.pop();
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            if(visited[nx][ny] > 0 || maps[nx][ny] == 0) continue;
            if(nx == n-1 && ny == m-1) return visited[x][y] + 1;
            visited[nx][ny] = visited[x][y] + 1;
            q.push({nx,ny});
        }
    }
    return -1;
}
