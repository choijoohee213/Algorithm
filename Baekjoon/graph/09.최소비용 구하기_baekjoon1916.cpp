#include<bits/stdc++.h>
using namespace std;

int n,m,startCity, endCity;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  vector<pair<int,int>> node[n+1];
  vector<int> d(n+1, INT_MAX);

  for(int i=0; i<m; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
  }
  cin>>startCity>>endCity;
  
  priority_queue<pair<int,int>> pq;
  pq.push({0, startCity});
  d[startCity] = 0;
  
  while(!pq.empty()){
    int x = pq.top().second;
    int w = -pq.top().first; pq.pop();
    
    if(d[x] < w) continue;
    
    for(int i=0; i<node[x].size(); i++){
      int nx = node[x][i].first;
      int nw = w + node[x][i].second;
      
      if(d[nx] > nw){
        d[nx] = nw;
        pq.push({-nw, nx});
      }
    }
  }
  if(d[endCity] == INT_MAX) cout<<"INF";
  else cout<<d[endCity];
}
