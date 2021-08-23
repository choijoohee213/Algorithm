#include<bits/stdc++.h>
using namespace std;

int n,m,sum=0;
vector<pair<int, pair<int,int>>> node;
int parent[1001];

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
  if(x>y) parent[x] = y;
  else if(x<y) parent[y] = x;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=0; i<m; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node.push_back({c,{a,b}});
  }
  
  sort(node.begin(), node.end());
  
  for(int i=1; i<=n; i++)
    parent[i] = i;
  
  for(int i=0; i<m; i++){
    int a = node[i].second.first;
    int b = node[i].second.second;
    int c = node[i].first;
    
    if(!sameParent(a,b)){
      Union(a,b);
      sum += c;
    }
  }
  cout<<sum;
}
