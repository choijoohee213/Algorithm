#include<bits/stdc++.h>
using namespace std;

int n,m,cnt=0;
vector<int> node[2][101];
bool visited[2][101];

void bfs(int num, int dir){
  queue<int> q;
  q.push(num);
  visited[dir][num] = true;
  
  while(!q.empty()){
    int x = q.front(); q.pop();
    
    for(int i=0; i<node[dir][x].size(); i++){
      int nx = node[dir][x][i];
      if(visited[dir][nx]) continue;
      visited[dir][nx] = true;
      q.push(nx);
      cnt++;
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    node[0][a].push_back(b);
    node[1][b].push_back(a);
  }
  
  for(int i=1; i<=n; i++){
    cnt = 1;
    memset(visited, false, sizeof(visited));
    bfs(i, 0);
    bfs(i, 1);
    cout<<n-cnt<<'\n';   
  }
}
