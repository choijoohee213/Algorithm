#include<bits/stdc++.h>
using namespace std;

int d[5][5];
unordered_map<string, int> num;
int dx[4] = {1,0,-1,0}, dy[4] = {0,1,0,-1};

void dfs(int x, int y, string s){
  if(s.size() == 6){
    num[s] = 1;
    return;
  }
  
  for(int i=0; i<4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
    string tmp = s + to_string(d[nx][ny]);
    dfs(nx, ny, tmp);
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
  
  for(int i=0; i<5; i++){
    for(int j=0; j<5; j++){
      dfs(i,j, to_string(d[i][j]));
    }
  }
  cout<<num.size();
}
