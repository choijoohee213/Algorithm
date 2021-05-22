#include<bits/stdc++.h>
using namespace std;

struct node{
  int x,y;
  int key;
  int time;
};

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m;
  cin>>n>>m;
  char maze[50][50];
  bool visited[50][50][64] = {false,};
  int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
  queue<node> q;
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      cin>>maze[i][j];
      if(maze[i][j] == '0'){
        node start;
        start.x = i;
        start.y = j;
        start.key = 0;
        start.time = 0;
        q.push(start);
      }
    }
  }
  
  while(!q.empty()){
    int x = q.front().x;
    int y = q.front().y;
    int key = q.front().key;
    int time = q.front().time; q.pop();
    
    if(maze[x][y] == '1'){
      cout<<time;
      return 0;
    }
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
      if(maze[nx][ny] == '#') continue;
      
      //¿­¼è 
      if(maze[nx][ny]>='a' && maze[nx][ny]<='f'){
        int nkey = key | (1 << (maze[nx][ny] - 'a'));
        
        if(visited[nx][ny][nkey]) continue;
        node next;
        next.x = nx;
        next.y = ny;
        next.time = time + 1;
        next.key = nkey;
        
        q.push(next);
        visited[nx][ny][nkey] = true;
      }
      
      //¹®
      else if(maze[nx][ny]>='A' && maze[nx][ny]<='Z'){
        int check = key & (1 << (maze[nx][ny] - 'A'));
        
        if(check == 0 || visited[nx][ny][key]) continue;    
        node next;
        next.x = nx;
        next.y = ny;
        next.time = time + 1;
        next.key = key;
        
        q.push(next);
        visited[nx][ny][key] = true;
      }
      
      else if(!visited[nx][ny][key]){
        node next;
        next.x = nx;
        next.y = ny;
        next.time = time + 1;
        next.key = key;
        q.push(next);
        visited[nx][ny][key] = true;
      } 
    }
  }
  cout<<-1;
}
