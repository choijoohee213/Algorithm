#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n;
    cin>>n;
    vector<vector<int>> d(n+1, vector<int>(n+1));
    vector<vector<int>> visit(n+1, vector<int>(n+1, -1));

    int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

    //길은 1, 벽은 0
    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            cin>>d[i][j];
        }
    }

    queue<pair<int, pair<int,int>>> q; //카운트,x,y
    if(d[1][1] == 1) q.push({0,{1,1}});
    visit[1][1] = 0;

    while(!q.empty()){
        int cnt = q.front().first;
        int x = q.front().second.first;
        int y = q.front().second.second; q.pop();   
        
        if(x == n && y == n) {
            cout<<visit[n][n];
            return 0;
        }
        if(cnt == 2){
            q.push({1,{x,y}});
            visit[x][y] += 2;
            continue;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx<1 || nx>n || ny<1 || ny>n || d[nx][ny] == 0) continue;
            if(visit[nx][ny] != -1) continue;
            visit[nx][ny] = visit[x][y] + 1;       
            q.push({cnt+1, {nx,ny}});
        }     
    }
    cout<<-1;
}