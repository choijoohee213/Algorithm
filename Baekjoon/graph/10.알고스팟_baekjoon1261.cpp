#include<bits/stdc++.h>
using namespace std;

typedef pair<int,pair<int,int>> pp;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int m,n,result=INT_MAX;
  cin>>n>>m;
  vector<vector<int>> board(m, vector<int>(n, 0));
  vector<vector<bool>> visited(m, vector<bool>(n, false));
  int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
  
  for(int i=0; i<m; i++){
    string s;
    cin>>s;
    for(int j=0; j<n; j++)
      board[i][j] = s[j] - '0';
  }
  
  priority_queue<pp, vector<pp>, greater<pp>> q;
  q.push({0,{0,0}});
  visited[0][0] = true;

  while(!q.empty()){
    int x = q.top().second.first;
    int y = q.top().second.second; 
    int w = q.top().first; q.pop();
    
    if(x == m-1 && y == n-1){
      cout<<w;
      return 0;
    }
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
      if(visited[nx][ny]) continue;
      
      q.push({w + board[nx][ny], {nx,ny}});
      visited[nx][ny] = true;
    }
  }
}
