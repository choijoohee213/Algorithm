#include<bits/stdc++.h>
using namespace std;

int n,m;
vector<pair<int,int>> node;
int plan[1000];
int parent[201];

int getParent(int x){
  if(x == parent[x]) return x;
  else return parent[x] = getParent(parent[x]);
}

bool sameParent(int x, int y){
  return getParent(x) == getParent(y);
}

void Union(int x, int y){
  x = getParent(x);
  y = getParent(y);
  if(x<y) parent[y] = x;
  else parent[x] = y;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=1; i<=n; i++){
    for(int j=1; j<=n; j++){
      int connect;
      cin>>connect;
      if(connect) node.push_back({i,j});
    }
    parent[i] = i;
  }
  for(int i=0; i<m; i++)
    cin>>plan[i];
  
  for(int i=0; i<node.size(); i++){
    int a = node[i].first;
    int b = node[i].second;
    
    if(!sameParent(a,b))
      Union(a,b);
  }
  
  for(int i=1; i<m; i++){
    int prev = parent[plan[i-1]];
    if(prev != parent[plan[i]]){
      cout<<"NO";
      return 0;
    }
  }
  cout<<"YES";
}
