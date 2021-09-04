#include<bits/stdc++.h>
using namespace std;
#define INF 987654321

int n,m,s,e;
vector<pair<int,int>> node[1001];
pair<int,int> d[1001]; //비용, 이전 노드 

void dijkstra(int num){
  for(int i=1; i<=n; i++){
    d[i].first = INF;
    d[i].second = 0;
  }
  
  priority_queue<pair<int,int>> pq;
  pq.push({0,num});
  d[num] = {0, 0};
  
  while(!pq.empty()){
    int w = -pq.top().first;
    int x = pq.top().second; pq.pop();
    
    if(d[x].first < w) continue;
    
    for(int i=0; i<node[x].size(); i++){
      int nx = node[x][i].first;
      int nw = node[x][i].second + w;
      
      if(nw < d[nx].first){
        d[nx].first = nw;
        d[nx].second = x;
        pq.push({-nw, nx});
      }
    }
  }  
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<m; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
  }
  cin>>s>>e;
  dijkstra(s);
  cout<<d[e].first<<'\n';
  
  vector<int> v;
  int px = e, cnt=0;
  while(px != 0){
    v.push_back(px);
    cnt++;
    px = d[px].second;
  }
  cout<<cnt<<'\n';
  for(int i=v.size()-1; i>=0; i--) cout<<v[i]<<" ";
}
