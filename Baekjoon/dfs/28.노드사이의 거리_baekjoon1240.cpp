#include<bits/stdc++.h>
using namespace std;

int n,m;
vector<pair<int,int>> node[1001];
bool visited[1001];

void dfs(int num, int end, int cnt){
  if(visited[num]) return;
  if(num == end){
    cout<<cnt<<'\n';
    return;
  }
  visited[num] = true;
  for(int i=0; i<node[num].size(); i++)
    dfs(node[num][i].first, end, cnt + node[num][i].second);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<n-1; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
    node[b].push_back({a,c});
  }
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    dfs(a,b,0);
    memset(visited, false, sizeof(visited));
  }
}
