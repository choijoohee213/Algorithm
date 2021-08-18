#include<bits/stdc++.h>
using namespace std;
#define MAX 50001

int n,m;
vector<int> tree[MAX];
int depth[MAX];
int parent[MAX]; 
bool visited[MAX];

int LCA(int u, int v){
  if(depth[u]>depth[v]) swap(u,v);
  
  while(depth[u] != depth[v]) v = parent[v];
  
  while(u != v){
    u = parent[u];
    v = parent[v];
  }
  return u;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  
  for(int i=0; i<n-1; i++){
    int a,b;
    cin>>a>>b;
    tree[a].push_back(b);
    tree[b].push_back(a);
  }
  
  queue<int> q;
  q.push(1);
  visited[1] = true;
  
  while(!q.empty()){
    int x = q.front(); q.pop();
    
    for(int i = 0; i<tree[x].size(); i++){
      if(visited[tree[x][i]]) continue;
      depth[tree[x][i]] = depth[x] + 1;
      visited[tree[x][i]] = true;
      parent[tree[x][i]] = x;
      q.push(tree[x][i]);
    }
  }
  cin>>m;
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    cout<<LCA(a,b)<<'\n';
  }
}
