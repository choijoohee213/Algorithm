#include<bits/stdc++.h>
using namespace std;

int result = 0;
vector<int> v[2000];
bool visited[2000] = {false,};

void dfs(int x, int cnt){
  if(result) return;
  if(cnt == 5){
    result = 1;
    return;    
  }
  
  for(int i=0; i<v[x].size(); i++){
    if(visited[v[x][i]]) continue;
    visited[v[x][i]] = true;
    dfs(v[x][i], cnt+1);
    visited[v[x][i]] = false;
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m;
  cin>>n>>m;;
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    v[a].push_back(b);
    v[b].push_back(a);
  }
  
  for(int i=0; i<n; i++){
    if(result) break;
    dfs(i, 0);
  }
    
  cout<<result;
}
