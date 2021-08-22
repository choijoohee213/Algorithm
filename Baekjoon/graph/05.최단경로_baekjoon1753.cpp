#include<bits/stdc++.h>
using namespace std;
#define INF 987654321

int v,e, k;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>v>>e>>k;
  vector<pair<int,int>> node[v+1];
  vector<int> d(v+1, INF);
  priority_queue<pair<int,int>> pq;
  
  for(int i=0; i<e; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
  }
  
  pq.push({0, k});
  d[k] = 0;
  
  while(!pq.empty()){
    int w = -pq.top().first; 
    int num = pq.top().second; pq.pop();
    
    if(d[num] < w) continue;
    
    for(int i=0; i<node[num].size(); i++){
      int nx = node[num][i].first;
      int nxcost = w + node[num][i].second;
      
      if(nxcost < d[nx]){
        d[nx] = nxcost;
        pq.push({-nxcost, nx});
      }
    }
  }
  
  for(int i=1; i<=v; i++){
    if(d[i] == INF) cout<<"INF\n";
    else cout<<d[i]<<'\n';
  }
}
