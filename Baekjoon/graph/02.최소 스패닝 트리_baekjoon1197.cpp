#include<bits/stdc++.h>
using namespace std;

int parent[10001];

int find(int x){
  if(parent[x] == x) return x;
  else return parent[x] = find(parent[x]);
}

void Union(int x, int y){
  x = find(x);
  y = find(y);
  
  if(x<y) parent[y] = x;
  else if(x>y) parent[x] = y;
}

bool sameParent(int x, int y){
  x = find(x);
  y = find(y);
  return x == y;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int V,E,cnt=0;
  long long sum=0;
  cin>>V>>E;
  vector<pair<int, pair<int,int>>> node;
  
  for(int i=0; i<E; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node.push_back({c,{a,b}});
  }
  
  sort(node.begin(), node.end());
  
  for(int i=1; i<=V; i++)
    parent[i] = i;
  
  for(int i=0; i<E; i++){
    int a = node[i].first;
    int b = node[i].second.first;
    int c = node[i].second.second;

    if(!sameParent(b,c)){
      Union(b,c);
      sum += a;
    }
  }
  cout<<sum;
}
