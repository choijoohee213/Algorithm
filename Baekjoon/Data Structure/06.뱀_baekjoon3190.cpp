#include<bits/stdc++.h>
using namespace std;

int n,k,l,curDir=0,cnt=-1;

void rotate(char c){
  if(c == 'L'){
    curDir--;
    if(curDir<0) curDir = 3;
  }
  else if(c == 'D'){
    curDir++;
    if(curDir>3) curDir = 0;
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);

  cin>>n>>k;
  vector<vector<int>> board(n+1, vector<int>(n+1, 0));
  vector<vector<bool>> snakePos(n+1, vector<bool>(n+1, false));
  unordered_map<int,char> dir;
  int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
  
  for(int i=0; i<k; i++){
    int r,c;
    cin>>r>>c;
    board[r][c] = 1;
  }
  cin>>l;
  for(int i=0; i<l; i++){
    int x; 
    char c;
    cin>>x>>c;
    dir[x] = c;
  }

  queue<pair<int,int>> q;
  queue<pair<int,int>> snake;
  q.push({1,1});
  snake.push({1,1});
  snakePos[1][1] = true;
  
  while(!q.empty()){
    int x = q.front().first;
    int y = q.front().second; q.pop();
    cnt++;
    
    if(dir[cnt] != ' ')
      rotate(dir[cnt]);
    int nx = x + dx[curDir];
    int ny = y + dy[curDir];
    
    if(nx<1 || nx>n || ny<1 || ny>n || snakePos[nx][ny]){
      cout<<cnt+1;
      return 0;
    }
    
    q.push({nx,ny});
    
    if(board[nx][ny] == 1) //´ÙÀ½Ä­ »ç°ú
      board[nx][ny] = 0;
    else {
      snakePos[snake.front().first][snake.front().second] = false;
      snake.pop();
    }
    snake.push({nx,ny});
    snakePos[nx][ny] = true;
  }
}