#include<bits/stdc++.h>
using namespace std;

int n,m,k,result=0,cnt=0;
int d[100][100] = {0,};
bool visited[100][100] = {false,};
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

void dfs(int x, int y){
  cnt++;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
    if(visited[nx][ny] || d[nx][ny]==0) continue;
    visited[nx][ny] = true;
    dfs(nx,ny);  
  } 
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m>>k;
  
  for(int i=0; i<k; i++){
    int r,c;
    cin>>r>>c;
    d[r-1][c-1] = 1;
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(!visited[i][j] && d[i][j]==1){
        visited[i][j] = true;
        dfs(i,j);
        result = max(result, cnt);
        cnt=0;
      }
    }
  }
  cout<<result;
}
