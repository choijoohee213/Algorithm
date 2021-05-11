#include<bits/stdc++.h>
using namespace std;

int n,m, secureCnt=0, result=2500;
int lab[50][50] = {0,};
int visited[50][50];
int dx[4] = {0,-1,0,1}, dy[4] = {1,0,-1,0};
bool virus_visited[10] = {false,};
vector<pair<int,int>> virus;

void bfs(int size){
  queue<pair<int,int>> q;
  memset(visited, -1, sizeof(visited));
  
  for(int i=0; i<size; i++){
    if(virus_visited[i]){
      q.push({virus[i].first, virus[i].second});
      visited[virus[i].first][virus[i].second] = 0;
    }
  }
  
  int cnt=0, time=0;
  
  while(!q.empty()){
    int x = q.front().first;
    int y = q.front().second; q.pop();
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
      if(lab[nx][ny]!=1 && visited[nx][ny]==-1){
        q.push({nx,ny});
        visited[nx][ny] = visited[x][y] + 1;
        if(lab[nx][ny] == 0){
          cnt++;
          time = visited[nx][ny];
        }
      }
    }
  }
  if(cnt == secureCnt) result = min(result, time);
}

void selectVirus(int index, int cnt, int size){
  if(cnt == m){
    bfs(size);
    return ;
  }
  for(int i=index; i<size; i++){
    if(!virus_visited[i]){
      virus_visited[i] = true;
      selectVirus(i+1, cnt+1, size);
      virus_visited[i] = false;
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  cin>>n>>m;
  
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++){
      cin>>lab[i][j];
      if(lab[i][j]==2) virus.push_back({i,j});
      else if(lab[i][j]==0) secureCnt++;
    }
  }
  
  selectVirus(0, 0, virus.size());
  if(result != 2500) cout<<result;
  else cout<<-1;
}
  
