#include<bits/stdc++.h>
using namespace std;
#define INF 987654321

int n,e,v1,v2;  
vector<pair<int,int>> node[801];

vector<int> dijkstra(int num){
  vector<int> d(n+1, INF);
  priority_queue<pair<int, int>> pq;
  pq.push({0, num});
  d[num] = 0;
  
  while(!pq.empty()){
    int x = pq.top().second;
    int w = -pq.top().first; 
    pq.pop();
    
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
  return d;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>e;
  
  for(int i=0; i<e; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
    node[b].push_back({a,c});
  }
  cin>>v1>>v2;
  
  vector<int> result = dijkstra(1);
  vector<int> v1_result = dijkstra(v1);
  vector<int> v2_result = dijkstra(v2);
  
  //1 -> v1 -> v2-> N or 1 -> v2 -> v1 -> N
  int minVal = min({result[v1] + v1_result[v2] + v2_result[n], result[v2] + v2_result[v1] + v1_result[n]});
  
  if(minVal >= INF || minVal < 0) cout<<-1;
  else cout<<minVal;
}
