#include<bits/stdc++.h>
using namespace std;

int n;
int arr[50][50]= {0,};
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
int visited[50][50];

int main(){  
  cin>>n;
  
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++)
      visited[i][j] = 2500;
  }
  
  for(int i=0; i<n; i++){
    string s;
    cin>>s;
    for(int j=0; j<n; j++)
      arr[i][j] = s[j] - '0';
  }
  
  queue<pair<int,int>> q;
  q.push({0,0});
  visited[0][0] = 0;
  
  while(!q.empty()){
    int x = q.front().first;
    int y = q.front().second; q.pop();
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
      if(arr[nx][ny] == 1 && visited[nx][ny] > visited[x][y]){
        visited[nx][ny] = visited[x][y];
        q.push({nx,ny});
      }
      else if(arr[nx][ny] == 0 && visited[nx][ny] > visited[x][y]+1){
        visited[nx][ny] = visited[x][y] + 1;
        q.push({nx,ny});
      }
    }
  }
  cout<<visited[n-1][n-1];
}
