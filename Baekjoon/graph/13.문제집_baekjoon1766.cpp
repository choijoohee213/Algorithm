#include<bits/stdc++.h>
using namespace std;

int n,m;
int indegree[32001] = {0,};
vector<int> v[32001];

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    v[a].push_back(b);
    indegree[b]++;
  }
  
  priority_queue<int, vector<int>, greater<int>> pq;
  
  for(int i=1; i<=n; i++){
    if(indegree[i] == 0)
      pq.push(i);
  }
  
  while(!pq.empty()){
    int x = pq.top(); pq.pop();
    cout<<x<<' ';
    
    for(int i=0; i<v[x].size(); i++){
      int nx = v[x][i];
      indegree[nx]--;
      if(indegree[nx] == 0) pq.push(nx); 
    }
  }
}
