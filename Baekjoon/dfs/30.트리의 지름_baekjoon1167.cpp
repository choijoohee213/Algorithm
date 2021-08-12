#include<bits/stdc++.h>
using namespace std;

int v, maxdist = 0, maxnode = 0;
vector<pair<int,int>> node[100001];
bool visited[100001];

void dfs(int num, int dist){
  if(visited[num]) return;
  if(maxdist < dist){
    maxdist = dist;
    maxnode = num;
  }
  visited[num] = true;
  
  for(int i=0; i<node[num].size(); i++){
    dfs(node[num][i].first, dist + node[num][i].second);
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>v;
  
  for(int i=1; i<=v; i++){
    int c,a,b;
    cin>>c;
    while(true){
      cin>>a;
      if(a==-1) break;
      cin>>b;
      node[c].push_back({a,b});
    }
  }
  
  dfs(1,0);
  memset(visited, 0, sizeof(visited));
  maxdist = 0;
  dfs(maxnode, 0);
  cout<<maxdist;
}
