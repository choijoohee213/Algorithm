#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int n,m,year=0;
    cin>>n>>m;
    
    int ice[300][300] = {0,};
    int d[300][300] = {0,};
    bool visited[300][300] = {false, };
    int dx[4] = {0, 1, 0, -1}, dy[4] = {1,0,-1,0};
    
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++)
            cin>>ice[i][j];
    }
    
    while(true){
        bool notMelted = false;
        queue<pair<int,int>> q;
        memset(d, 0, sizeof(d));
        memset(visited, false, sizeof(visited));

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(ice[i][j] <= 0 || visited[i][j]) continue;
                if(notMelted && !visited[i][j]){
                    cout<<year;
                    return 0;
                }
                q.push(make_pair(i,j));
                visited[i][j] = true;
                notMelted = true;
                
                while(!q.empty()){
                    int x = q.front().first;
                    int y = q.front().second;
                    q.pop();
                    int cnt=0;
                    
                    for(int k=0; k<4; k++){
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        
                        if(nx<0 || nx>=n || ny<0 || ny>=m) continue; 
                        if(visited[nx][ny]) continue;
                        if(ice[nx][ny]<=0) {
                            cnt++; 
                            continue;
                        }               
                        q.push(make_pair(nx,ny));
                        visited[nx][ny] = true;
                    }
                    d[x][y] = cnt;
                }
            }
        }
        if(!notMelted){
            cout<<0;
            return 0;
        } 
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                ice[i][j] -= d[i][j];
        }
        year++;   
    }   
}
