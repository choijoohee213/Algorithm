#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int n,landCnt=1;
    int mapArr[100][100];
    int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
    int visited[100][100] = {0,};
    vector<pair<int,int>> v;
    queue<pair<int,int>> q;

    cin>>n;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            int x;
            cin>>x;
            mapArr[i][j] = x == 1 ? -1 : 0;
        }
    }
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            if(visited[i][j]!=0 || mapArr[i][j]!=-1) continue;            
            q.push(make_pair(i,j));
            visited[i][j] = 1;
            mapArr[i][j] = landCnt;
            
            while(!q.empty()){
                int x = q.front().first;
                int y = q.front().second; q.pop();
                
                for(int k=0; k<4; k++){
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    
                    if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                    if(visited[nx][ny]!=0) continue;
                    if(mapArr[nx][ny] == 0){
                        v.push_back({x,y});
                        continue;
                    }
                    
                    q.push(make_pair(nx,ny));
                    visited[nx][ny] = 1;
                    mapArr[nx][ny] = landCnt;
                }
            }
            landCnt++;
        }
    }
    
    sort(v.begin(), v.end());
    v.erase(unique(v.begin(), v.end()), v.end());
    int result = 1e9;

    for(int i=0; i<v.size(); i++){
        int a = v[i].first;
        int b = v[i].second;
        
        q.push({a,b});
        memset(visited, 0, sizeof(visited));
        int minValue = 1e9;
        int m = mapArr[a][b];
        
        while(!q.empty()){
            int x = q.front().first;
            int y = q.front().second; q.pop();
            
            for(int k=0; k<4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                if(visited[nx][ny] != 0) continue;
                if(mapArr[nx][ny] == m) continue;
                if(mapArr[nx][ny] != 0){
                    minValue = min(minValue, visited[x][y]);
                    continue;
                }       
                q.push({nx,ny});
                visited[nx][ny] = visited[x][y] + 1;
            }
        }        
        result = min(result, minValue);
    }
    cout<<result;
}
