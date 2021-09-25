#include<bits/stdc++.h>
using namespace std;

#define INF 987654321

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m,k,x;
  cin>>n>>m>>k>>x;
  
  queue<int> q;
  vector<int> v[n+1];
  vector<int> d(n+1, 0);
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    v[a].push_back(b);
  }
  
  q.push(x);
  d[x] = 1;
  while(!q.empty()){
    int a = q.front(); q.pop();
    
    for(int i=0; i<v[a].size(); i++){
      int nx = v[a][i];
      if(d[nx] != 0) continue;
      d[nx] = d[a] + 1;
      q.push(nx);
    }
  }
  int result = 0;
  for(int i=1; i<=n; i++){
    if(d[i]-1 == k){
      cout<<i<<'\n';
      result++;
    } 
  }
  if(result == 0) cout<<-1;
}
