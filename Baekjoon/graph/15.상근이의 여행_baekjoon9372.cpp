#include<bits/stdc++.h>
using namespace std;

int parent[1001];
vector<pair<int,int>> node;

int getParent(int x){
  if(x == parent[x]) return x;
  else return parent[x] = getParent(parent[x]); 
}

bool sameParent(int x, int y){
  return getParent(x) == getParent(y);
}

void Union(int a, int b){
  a = getParent(a);
  b = getParent(b);
  if(a<b) parent[b] = a;
  else if(a>b) parent[a] = b;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t,n,m;
  cin>>t;
  
  while(t--){
    cin>>n>>m;
    
    for(int i=1; i<=n; i++) parent[i] = i;
    
    for(int i=0; i<m; i++){
      int a,b;
      cin>>a>>b;
      node.push_back({a,b});
    }
    
    for(int i=0; i<node.size(); i++){
      int a = node[i].first;
      int b = node[i].second;
      
      if(!sameParent(a,b))
        Union(a,b);
    }
    
    unordered_set<int> s;
    for(int i=1; i<=n; i++) s.insert(parent[i]);
    cout<<n-s.size()<<'\n';
  }
}
