#include <bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);  
  
  int m,n,time=0,cheese=0;
  cin>>m>>n;
  int d[100][100] = {0,};
  int dx[4] = {0,1,0,-1}, dy[4]={1,0,-1,0};
  int visited[100][100] = {0,};
  
  for(int i=0; i<m; i++){
    for(int j=0; j<n; j++)
      cin>>d[i][j];
  }
  
  while(true){
    memset(visited, false, sizeof(visited));
    bool melted = true;
    queue<pair<int,int>> q;
    q.push({0,0});
    visited[0][0] = 1;
    
    while(!q.empty()){
      int x = q.front().first;
      int y = q.front().second;
      q.pop();
      
      for(int k=0; k<4; k++){
        int nx = x + dx[k];
        int ny = y + dy[k];
        
        if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
        if(visited[nx][ny] == 1) continue;
        if(d[nx][ny] == 1){
          visited[nx][ny] = 2;
          melted = false;
          continue;
        }
        q.push({nx,ny});
        visited[nx][ny] = 1;        
      }
    }

    if(melted){
      cout<<time<<'\n'<<cheese;
      return 0;
    }
    
    int c = 0;
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(visited[i][j]==2){
          c++;
          d[i][j] = 0;
        }
      }
    }
    cheese = c;    
    time++;  
  }
}
