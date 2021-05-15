#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m,result=0;
  cin>>n>>m;
  vector<int> v[n+1];
  vector<int> visited(n+1, -1);
  queue<int> q;
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    v[a].push_back(b);
    v[b].push_back(a);
  }
  
  q.push(1);
  visited[1] = 0;
  
  while(!q.empty()){
    int x = q.front(); q.pop();
    
    for(int i=0; i<v[x].size(); i++){
      int nx = v[x][i];
      if(visited[nx]!=-1) continue;
      visited[nx] = visited[x] + 1;
      if(visited[nx] <= 2) result++;
      q.push(nx);
    }
  }
  cout<<result;
}
