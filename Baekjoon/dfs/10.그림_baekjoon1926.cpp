#include<bits/stdc++.h>
using namespace std;

int n,m,cnt=0, pcnt=0, psize=0;
int d[500][500];
int dx[4] = {0, 1, 0, -1}, dy[4] = {1,0,-1,0};
bool visited[500][500] = {false,};

void dfs(int x, int y){ 
  cnt++;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
    if(visited[nx][ny]) continue;
    if(d[nx][ny] == 0) continue;
    visited[nx][ny] = true;
    dfs(nx,ny);
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);

  cin>>n>>m;
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      cin>>d[i][j];
    }
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(d[i][j] == 1 && !visited[i][j]){
        visited[i][j] = true;
        pcnt++;
        dfs(i,j);
        psize = max(psize, cnt);
        cnt = 0;
      }
    }
  }
  cout<<pcnt<<'\n'<<psize;
}
