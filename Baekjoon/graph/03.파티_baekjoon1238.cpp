#include<bits/stdc++.h>
using namespace std;

struct edge {
  int endNode;
  int weight;
  edge(int a, int b){
    endNode = a;
    weight = b;
  }
  bool operator<(const edge& e)const {
    return weight > e.weight;
  }
};

int n,m,x,ans = 0;
vector<edge> node[1001];

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m>>x;
  for(int i=0; i<m; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back(edge(b,c));
  }
  
  vector<vector<int>> arr(n+1, vector<int>(n+1, INT_MAX));
  
  for(int i=1; i<=n; i++){
    priority_queue<edge> pq;
    pq.push(edge(i, 0));
    arr[i][i] = 0;
    
    while(!pq.empty()){
      int e = pq.top().endNode;
      int w = pq.top().weight; pq.pop();
      
      if(w > arr[i][e]) continue;
      
      for(int j=0; j<node[e].size(); j++){
        int ne = node[e][j].endNode;
        int nw = node[e][j].weight;
        
        if(arr[i][ne] > arr[i][e] + nw){
          arr[i][ne] = arr[i][e] + nw;
          pq.push(edge(ne, arr[i][ne]));
        }
      }
    }
  }
  
  for(int i=1; i<=n; i++)
    ans = max(ans, arr[i][x] + arr[x][i]);
  cout<<ans;
}
