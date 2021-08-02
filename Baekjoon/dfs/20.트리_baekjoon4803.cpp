#include<bits/stdc++.h>
using namespace std;

int n,m, cnt=0, result = 0;
vector<int> v[501];
bool visited[501];

bool dfs(int num, int post){
  visited[num] = true;
  
  for(int i=0; i<v[num].size(); i++){
    if(v[num][i] == post) continue;
    if(visited[v[num][i]]) return false;
    if(!dfs(v[num][i], num)) return false;
  }
  return true;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  while(true){
    cnt++;
    cin>>n>>m;
    if(n == 0 && m == 0)
      return 0;
    
    for(int i=0; i<m; i++){
      int a,b;
      cin>>a>>b;
      v[a].push_back(b);
      v[b].push_back(a);
    }
    
    for(int i=1; i<=n; i++){
      if(!visited[i]){
        if(dfs(i, 0)) result++;
      }
    }
    
    cout<<"Case "<<cnt<<": ";
    if(result > 1) cout<<"A forest of "<<result<<" trees.\n";
    else if(result == 1) cout<<"There is one tree.\n";
    else cout<<"No trees.\n";
    
    for(int i=1; i<=n; i++){
      visited[i] = false;
      v[i].clear();
    }
    result = 0;
  }
}
