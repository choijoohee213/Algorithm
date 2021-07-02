#include<bits/stdc++.h>
using namespace std;

int r,c, sum_w=0, sum_s=0, wolf=0, sheep=0;
char d[250][250];
bool visited[250][250] = {false,};
int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

void dfs(int x, int y){
  if(d[x][y] == 'v') wolf++;
  else if(d[x][y] == 'o') sheep++;
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
    if(visited[nx][ny] || d[x][y] == '#') continue;
    visited[nx][ny] = true;
    dfs(nx,ny);
  }  
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>r>>c;
  
  for(int i=0; i<r; i++){
    for(int j=0; j<c; j++){
      cin>>d[i][j];
    }
  }
  
  for(int i=0; i<r; i++){
    for(int j=0; j<c; j++){
      if(!visited[i][j] && d[i][j] != '#'){
        visited[i][j] = true;
        dfs(i,j);
        if(wolf < sheep) sum_s += sheep;
        else sum_w += wolf;
        wolf = 0;
        sheep = 0;
      }
    }
  }
  cout<<sum_s<<' '<<sum_w;
}
