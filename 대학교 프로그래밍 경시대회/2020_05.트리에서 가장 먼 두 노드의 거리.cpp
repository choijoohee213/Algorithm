#include<bits/stdc++.h>
using namespace std;

int result=0, endN=0;
bool visited[10001];
vector<int> node[10001];

void dfs(int num, int len){
  if(visited[num]) return;
  visited[num] = true;
  
  if(result < len){
    result = len;
    endN = num;
  }
  
  for(int i=0; i<node[num].size(); i++)
    dfs(node[num][i], len+1);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,n;
  cin>>t;
  
  while(t--){
    cin>>n;
    for(int i=0; i<=n+1; i++)
      node[i].clear();
      
    for(int i=0; i<n-1; i++){
      int a,b;
      cin>>a>>b;
      node[a].push_back(b);
      node[b].push_back(a);
    }
    
    memset(visited, false, sizeof(visited));
    result = 0;
    dfs(1, 0);
    
    memset(visited, false, sizeof(visited));
    result = 0;
    dfs(endN, 0);
    cout<<result<<'\n';
  }
}
