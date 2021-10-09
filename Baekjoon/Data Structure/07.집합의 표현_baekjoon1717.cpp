#include<bits/stdc++.h>
using namespace std;

int n,m;
int parent[1000001];

int getParent(int a){
  if(a == parent[a]) return a;
  else return parent[a] = getParent(parent[a]);
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

  cin>>n>>m;
  
  for(int i=0; i<=n; i++)
    parent[i] = i;

  for(int i=0; i<m; i++){
    int x,a,b;
    cin>>x>>a>>b;
    if(x == 0) Union(a,b);
    else {
      if(sameParent(a,b)) cout<<"YES\n";
      else cout<<"NO\n";
    }
  }
}