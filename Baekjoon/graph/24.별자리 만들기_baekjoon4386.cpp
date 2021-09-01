#include<bits/stdc++.h>
using namespace std;

int parent[101];

int getParent(int x){
  if(x == parent[x]) return x;
  else return parent[x] = getParent(parent[x]);
}

bool sameParent(int a, int b){
  return getParent(a) == getParent(b);
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
  
  int n;
  float sum=0;
  float d[101][2];
  vector<pair<float,pair<int,int>>> node;
  cin>>n;
  for(int i=0; i<n; i++){
    float x,y;
    cin>>d[i][0]>>d[i][1];
    parent[i] = i;
  }
  
  for(int i=0; i<n; i++){
    for(int j=i+1; j<n; j++){
      float dist = sqrt(pow(d[i][0]-d[j][0],2) + pow(d[i][1]-d[j][1],2));
      node.push_back({dist, {i,j}});
    }
  }
  sort(node.begin(), node.end());
  
  for(int i=0; i<node.size(); i++){
    float w = node[i].first;
    int a = node[i].second.first;
    int b = node[i].second.second;
    
    if(!sameParent(a,b)){
      Union(a,b);
      sum += w;
    }
  }
  cout<<fixed;
  cout.precision(2);
  cout<<sum;
}
