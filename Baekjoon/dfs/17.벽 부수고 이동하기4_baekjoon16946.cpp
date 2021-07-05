#include<bits/stdc++.h>
using namespace std;

int n,m, cnt=1, num=1;
int d[1000][1000];
bool visited[1000][1000];
int result[1000] = {0,};
int group[1000][1000] = {0,};
int dx[4] = {1,0,-1,0}, dy[4] = {0,1,0,-1};

void dfs(int x, int y){
  visited[x][y] = true;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
    if(d[nx][ny ]== 1 || visited[nx][ny]) continue;
    cnt++;
    dfs(nx,ny);
  }
  group[x][y] = num;
}

int main(){
  cin>>n>>m;
  for(int i=0; i<n; i++){
    string s;
    cin>>s;
    for(int j=0; j<m; j++)
      d[i][j] = s[j] - '0';
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      if(d[i][j] == 0 && group[i][j]==0){
        dfs(i,j);
        result[num] = cnt;
        num++;
        cnt = 1;
      }
    }
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++){
      int sum = 0;
      if(d[i][j] == 1){
        sum++;
        unordered_map<int,int> uom;
        for(int k=0; k<4; k++){
          int nx = i + dx[k];
          int ny = j + dy[k];
          
          if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
          if(d[nx][ny] == 1) continue;
          if(uom[group[nx][ny]]++ == 0){
            sum += result[group[nx][ny]]; 
          }
        }   
      }
      cout<<sum % 10;
    }
    cout<<'\n';
  }
}
