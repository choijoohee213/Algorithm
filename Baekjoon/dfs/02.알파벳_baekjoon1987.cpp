#include <bits/stdc++.h>
using namespace std;

int r,c, result = 1;
int board[20][20] = {0,};
bool visited[26];
int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};

void dfs(int x, int y, int cnt){
  bool end = true;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
    if(visited[board[nx][ny]]) continue;
    visited[board[nx][ny]] = true;
    end = false;
    dfs(nx, ny, cnt+1);
    visited[board[nx][ny]] = false;
  }
  
  if(end)
    result = max(result, cnt);
}
  
int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>r>>c;
  
  for(int i=0; i<r; i++){
    for(int j=0; j<c; j++){
      char c;
      cin>>c;
      board[i][j] = (int)c- 65;
    }
  } 
  visited[board[0][0]] = true;
  dfs(0, 0, 1);
  cout<<result;
}
