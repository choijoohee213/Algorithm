#include<bits/stdc++.h>
using namespace std;
#define INF 987654321

int t,p,q,r,maxN,maxV=0;
vector<pair<int,int>> node[5001];
vector<int> v;
int d[5001];

void dijkstra(){
  for(int i=1; i<=p; i++) d[i] = INF;
  priority_queue<pair<int,int>> pq;
  
  for(int i=0; i<v.size(); i++){
    pq.push({0, v[i]});
    d[v[i]] = 0;
  }
  
  while(!pq.empty()){
    int w = -pq.top().first;
    int x = pq.top().second; pq.pop();
    
    if(d[x] < w) continue;
    for(int i=0; i<node[x].size(); i++){
      int nx = node[x][i].first;
      int nw = node[x][i].second + w;
      if(nw < d[nx]){
        d[nx] = nw;
        pq.push({-nw, nx});
      }
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>t;
  
  while(t--){
    cin>>p>>q>>r;
    v.clear();
    for(int i=1; i<=p; i++)
      node[i].clear();
    maxV=0;
    
    for(int i=0; i<q; i++){
      int a,b,c;
      cin>>a>>b>>c;
      node[b].push_back({a,c});
    }
    
    for(int i=1; i<=r; i++){
      int x;
      cin>>x;
      v.push_back(x);
    }
    
    dijkstra();
    for(int i=1; i<=p; i++){
      if(d[i] == INF) continue;
      if(maxV < d[i]){
        maxV = d[i];
        maxN = i;
      }
    }
    cout<<maxN<<" "<<maxV<<'\n';
  }
}
