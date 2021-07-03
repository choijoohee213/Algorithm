#include<bits/stdc++.h>
using namespace std;

int result;
char d[5][5]; 
bool visited[25] = {false,};
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

bool checkS(){
  int cnt = 0;
  for(int i=0; i<25; i++){
    if(visited[i]){
      int x = i/5;
      int y = i%5;
      if(d[x][y] == 'S') cnt++;
    }
  }
  if(cnt >= 4) return true;
  else return false;
}

bool bfs(){
  queue<pair<int,int>> q;
  bool select[5][5] = {false,};
  bool qvisited[5][5] = {false,};
  
  bool qpush = false;
  for(int i=0; i<25; i++){
    if(visited[i]){
      int x = i/5;
      int y = i%5;
      select[x][y] = true;
      
      if(!qpush){
        q.push({x,y});
        qvisited[x][y] = true;
        qpush = true;
      }
    }
  }
  
  int cnt = 1;
  while(!q.empty()){
    int x = q.front().first;
    int y = q.front().second; q.pop();
    
    if(cnt == 7)
      return true;
      
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
      if(select[nx][ny] && !qvisited[nx][ny]){
        qvisited[nx][ny] = true;
        q.push({nx,ny});
        cnt++;
      }
    }
  }
  return false;
}

void dfs(int index, int cnt){
  if(cnt == 7){
    if(checkS() && bfs()) result++;
    return;
  }
  
  for(int i=index; i<25; i++){
    if(visited[i]) continue;
    visited[i] = true;
    dfs(i, cnt+1);
    visited[i] = false;
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  for(int i=0; i<5; i++){
    for(int j=0; j<5; j++){
      cin>>d[i][j];
    }
  }
  dfs(0,0);
  cout<<result;
}
