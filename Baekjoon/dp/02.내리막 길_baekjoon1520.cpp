#include<bits/stdc++.h>
using namespace std;

int m,n;
int board[500][500];
int dp[500][500];
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

int dfs(int x, int y){
  if(x == m-1 && y == n-1) return 1;
  if(dp[x][y]>=0) return dp[x][y];
  
  dp[x][y] = 0;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
    if(board[x][y]<=board[nx][ny]) continue;
    
    dp[x][y] += dfs(nx, ny);
  }
  return dp[x][y];
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>m>>n;
  memset(dp, -1, sizeof(dp));
  for(int i=0; i<m; i++){
    for(int j=0; j<n; j++)
      cin>>board[i][j];
  }
  cout<<dfs(0,0);
}
