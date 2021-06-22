#include<bits/stdc++.h>
using namespace std;

bool visited[1000];

void dfs(int x, vector<int> d[]){ //dfs
  cout<<x+1<<" ";
  
  for(int i=0; i<d[x].size(); i++){
    if(visited[d[x][i]]) continue;
    visited[d[x][i]] = true;
    dfs(d[x][i], d);
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n, m, v;
  cin>>n>>m>>v;
  v--;
  vector<int> d[n];

  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    d[a-1].push_back(b-1);
    d[b-1].push_back(a-1);
  }
  
  for(int i=0; i<n; i++){
    if(d[i].size() == 0) continue;
    sort(d[i].begin(), d[i].end());
  }
  
  visited[v] = true;
  dfs(v, d);
  cout<<'\n';

  //bfs
  memset(visited, false, sizeof(visited));
  queue<int> q;
  q.push(v);
  visited[v] = true;
  
  while(!q.empty()){
    int x = q.front(); q.pop();
    cout<<x+1<<" ";
    for(int i=0; i<d[x].size(); i++){
      if(visited[d[x][i]]) continue;
      q.push(d[x][i]);
      visited[d[x][i]] = true;
    }
  }
}
