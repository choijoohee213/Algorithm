#include<bits/stdc++.h>
using namespace std;

int n,m;
char board[500][500];
bool dp[500][500] = {false,};
bool visited[500][500] = {false,};

bool dfs(int r, int c){
  visited[r][c] = true;
  
  int nx = r, ny = c;
  if(board[r][c] == 'U') nx--;
  else if(board[r][c] == 'R') ny++;
  else if(board[r][c] == 'D') nx++;
  else ny--;
  
  if(nx<0 || nx>=n || ny<0 || ny>=m){
    dp[r][c] = true;
    return true;
  }
  if(visited[nx][ny]){
    dp[r][c] = dp[nx][ny];
    return dp[r][c];
  }
  if(dfs(nx, ny)){
    dp[r][c] = true;
    return true;
  }
  else return false;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      cin>>board[i][j];
    }
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(!visited[i][j])
        dfs(i,j);
    }
  }
  
  int cnt = 0;
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++)
      if(dp[i][j]) cnt++;
  }
  cout<<cnt;
}
