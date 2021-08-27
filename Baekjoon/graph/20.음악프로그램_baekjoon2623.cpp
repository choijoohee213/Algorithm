#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m;
  int indegree[1001] = {0,};
  vector<int> node[1001];
  vector<int> result;
  cin>>n>>m;
  
  for(int i=0; i<m; i++){
    int x;
    cin>>x;
    int prev;
    for(int j=0; j<x; j++){
      int num;
      cin>>num;
      if(j!=0) {
        indegree[num]++;
        node[prev].push_back(num);  
      }
      prev = num;
    }
  }
  
  queue<int> q;
  for(int i=1; i<=n; i++){
    if(indegree[i] == 0) q.push(i); 
  }
  
  while(!q.empty()){
    int x = q.front(); q.pop();
    result.push_back(x);
    
    for(int i=0; i<node[x].size(); i++){
      int nx = node[x][i];
      indegree[nx]--;
      if(indegree[nx] <= 0) q.push(nx);
    }
  }
  
  if(result.size() == n){
    for(int i=0; i<result.size(); i++) 
      cout<<result[i]<<'\n';
  }
  else cout<<0;
}
