#include<bits/stdc++.h>
using namespace std;

int n,m;
char d[50][50];
bool visited[50][50] = {false,};
int dx[4] = {0,0,1,-1}, dy[4] = {1,-1,0,0};

void dfs(int x, int y, int sx, int sy, int cnt){
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
    if(d[x][y] != d[nx][ny]) continue;
    if(visited[nx][ny]){
      if(sx == nx && sy == ny && cnt>=4){
        cout<<"Yes";
        exit(0);
      }
    }
    else{
      visited[nx][ny] = true;
      dfs(nx, ny, sx, sy, cnt+1);
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++)
      cin>>d[i][j];
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      memset(visited, false, sizeof(visited));
      visited[i][j] = true;
      dfs(i,j,i,j,1);
    }
  }
  cout<<"No";
}
