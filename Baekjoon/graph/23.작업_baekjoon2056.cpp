#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,w;
  vector<int> d(10001, 0);
  vector<int> node[10001];
  vector<int> indegree(10001, 0);
  priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
  cin>>n;
  
  for(int i=1; i<=n; i++){
    int a;
    cin>>d[i]>>a;
    for(int j=0; j<a; j++){
      int b;
      cin>>b;
      node[b].push_back(i);
    }
    indegree[i] += a;
    if(indegree[i] == 0)
      pq.push({d[i],i});
  }
  
  while(!pq.empty()){
    w = pq.top().first;
    int x = pq.top().second; pq.pop();
    
    for(int i=0; i<node[x].size(); i++){
      int nx = node[x][i];
      indegree[nx]--;
      if(indegree[nx] == 0)
        pq.push({w+d[nx], nx});
    }  
  }
  cout<<w;
}
