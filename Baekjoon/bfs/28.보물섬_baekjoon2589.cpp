#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int n,m,result=0;
    cin>>n>>m;
    
    int tmap[50][50];
    int visited[50][50];
    int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
    
    for(int i=0; i<n; i++){
        string s;
        cin>>s;
        for(int j=0; j<m; j++)
            tmap[i][j] = s[j] == 'L'? 1: 0; 
    }
    
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(tmap[i][j] == 0) continue;
            queue<pair<int,int>> q;
            memset(visited, -1, sizeof(visited));
            int cnt = 0;
            
            q.push(make_pair(i,j));
            visited[i][j] = 0;
            
            while(!q.empty()){
                int x = q.front().first;
                int y = q.front().second; q.pop();
                
                for(int k=0; k<4; k++){
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    
                    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                    if(visited[nx][ny]>-1 || tmap[nx][ny]==0) continue;
                    
                    q.push(make_pair(nx,ny));
                    visited[nx][ny] = visited[x][y]+1;
                    cnt = max(cnt, visited[nx][ny]);              
                }
            } 
            result = max(result, cnt);
        }
    }
    cout<<result;
}
