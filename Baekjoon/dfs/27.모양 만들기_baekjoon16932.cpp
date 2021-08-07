#include<bits/stdc++.h>
using namespace std;

int n,m,cnt=0, result = 0;
int arr[1000][1000];
int visited[1000][1000];
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
vector<int> area;

void bfs(int r, int c){
  queue<pair<int,int>> q;
  q.push({r,c});
  visited[r][c] = ++cnt;
  int areaCnt = 1;
  
  while(!q.empty()){
    int x = q.front().first; 
    int y = q.front().second; q.pop();
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
      if(visited[nx][ny]>0 || arr[nx][ny] == 0) continue;
      q.push({nx,ny});
      visited[nx][ny] = cnt;
      areaCnt++;
    }
  }
  area.push_back(areaCnt);
} 

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++)
      cin>>arr[i][j];
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(arr[i][j] == 1 && visited[i][j] == 0) 
        bfs(i,j);
    }
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(arr[i][j] == 1) continue;
      unordered_set<int> uos;
      
      for(int k=0; k<4; k++){
        int nx = i + dx[k];
        int ny = j + dy[k];
        
        if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
        if(arr[nx][ny] == 0) continue;
        uos.insert(visited[nx][ny]-1);
      }
      
      int sum = 1;
      for(int index : uos)
        sum += area[index];
      result = max(result, sum);
    }
  }
  cout<<result;
}
