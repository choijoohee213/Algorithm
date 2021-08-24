#include<bits/stdc++.h>
using namespace std;

int node[1001];
bool visited[1001];

void dfs(int num){
  visited[num] = true;
  if(!visited[node[num]]) dfs(node[num]);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,n;
  cin>>t;
  
  while(t--){
    int cnt = 0;
    cin>>n;
    memset(visited, false, sizeof(visited));
    
    for(int i=1; i<=n; i++){
      int x;
      cin>>x;
      node[i] = x;
    }
    
    for(int i=1; i<=n; i++){
      if(!visited[i]){
        cnt++;
        dfs(i);
      }
    }
    cout<<cnt<<'\n';
  }
}
