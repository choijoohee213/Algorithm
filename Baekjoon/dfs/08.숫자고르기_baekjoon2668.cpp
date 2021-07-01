#include<bits/stdc++.h>
using namespace std;

int d[101] = {0,};
bool visited[101] = {false,};
vector<int>result;

void dfs(int cur, int start){
  if(visited[cur]){
    if(start == cur)
      result.push_back(start);    
  }
  else{
    visited[cur] = true;
    dfs(d[cur], start);
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  cin>>n;
  
  for(int i=1; i<=n; i++)
    cin>>d[i];
  
  for(int i=1; i<=n; i++){
    memset(visited, false, sizeof(visited));
    dfs(i, i);
  }
  
  cout<<result.size()<<'\n';
  for(int i : result)
    cout<<i<<'\n';
}
