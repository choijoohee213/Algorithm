#include<bits/stdc++.h>
using namespace std;

int n,m;
char d[50][50];
int dp[50][50];
bool visited[50][50] = {false,};
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

int dfs(int x, int y){
  if(x<0 || y<0 || x>=n || y>=m || d[x][y]=='H')
    return 0;
    
  if(visited[x][y]){
    cout<<-1;
    exit(0);
  }
  
  if(dp[x][y]!= -1)
    return dp[x][y];
  
  visited[x][y] = true;
  dp[x][y] = 0;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i] * (d[x][y] - '0');
    int ny = y + dy[i] * (d[x][y] - '0');
    
    dp[x][y] = max(dp[x][y], dfs(nx,ny) + 1);
  }
  visited[x][y] = false;
  return dp[x][y];
}

int main(){
  cin>>n>>m;
  memset(dp, -1, sizeof(dp));
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      cin>>d[i][j];
    }
  }
  cout<<dfs(0,0);
}
