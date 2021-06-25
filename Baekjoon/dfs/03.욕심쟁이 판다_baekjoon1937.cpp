#include<bits/stdc++.h>
using namespace std;

int n,k=0;
int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};
bool visited[500][500] = {false,};
int dp[500][500] = {0,};

int dfs(int x, int y, int cnt, vector<vector<int>>& d){
  int result = cnt;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
    if(d[nx][ny]<=d[x][y]) continue;
    if(visited[nx][ny]) continue;
    if(dp[x][y] > 0) {
      result = max(cnt + dp[nx][ny], result);
      continue;
    }

    visited[nx][ny] = true;
    result = max(result, dfs(nx, ny, cnt+1, d));
    visited[nx][ny] = false;
  }
  dp[x][y] = result - cnt + 1;
  return result;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  vector<vector<int>> d(n, vector<int>(n));
  
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++)
      cin>>d[i][j];
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++){
      visited[i][j] = true;
      k = max(k, dfs(i, j, 1, d));
      visited[i][j] = false;
    }
  }
  cout<<k;
}
