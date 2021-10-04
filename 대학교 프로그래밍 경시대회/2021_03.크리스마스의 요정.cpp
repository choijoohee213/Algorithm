#include<bits/stdc++.h>
using namespace std;

int t,w,h;
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
int visited[30][30][64];
char board[30][30];

int bfs(){
  int allKey = 0;
  queue<pair<int, pair<int,int>>> q;
  for(int i=0; i<w; i++){
    for(int j=0; j<h; j++){
      cin>>board[i][j];
      if(board[i][j] == '?') {
        q.push({0,{i,j}});
        visited[i][j][0] = 0;
        board[i][j] = '.';
      }
      else if(board[i][j]>='a' && board[i][j]<='f'){
        allKey |= (1<<(board[i][j]-'a'));
      }
    }
  }
  
  while(!q.empty()){
    int key = q.front().first;
    int x = q.front().second.first;
    int y = q.front().second.second; q.pop();
    int cnt = visited[x][y][key];

    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];

      if(nx<0 || nx>=w || ny<0 || ny>=h) continue;
      
      if(board[nx][ny]>='a' && board[nx][ny]<='f'){
        int newKey = key | (1<< (board[nx][ny] - 'a'));
        
        if(visited[nx][ny][newKey]>=0) continue;
        if(newKey == allKey){
          return cnt+1;
        }
        q.push({newKey, {nx,ny}});
        visited[nx][ny][newKey] = cnt + 1;
      }
        
      else if(board[nx][ny]>='A' && board[nx][ny]<='F'){
        int check = key & (1 << (board[nx][ny]-'A')); 
        if(check != 0 && visited[nx][ny][key]==-1){
          q.push({key, {nx, ny}});
          visited[nx][ny][key] = cnt + 1;
        }
      }
        
      else if(board[nx][ny] == '.' && visited[nx][ny][key]==-1){
        q.push({key, {nx,ny}});
        visited[nx][ny][key] = cnt + 1;
      }
    }
  }
  return -1;
}


int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>t;
  while(t--){
    cin>>h>>w;
    memset(visited, -1, sizeof(visited));
    cout<<bfs()<<endl;
  }
}