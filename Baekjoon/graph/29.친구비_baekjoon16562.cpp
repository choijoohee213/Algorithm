#include<bits/stdc++.h>
using namespace std;

int parent[10001];
int a[10001];

int getParent(int x){
  if(x == parent[x]) return x;
  else return parent[x] = getParent(parent[x]);
}

void Union(int x, int y){
  x = getParent(x);
  y = getParent(y);
  if(a[x] <= a[y]) parent[y] = x;
  else if(a[x] > a[y]) parent[x] = y;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m,k,sum=0;
  cin>>n>>m>>k;
  vector<bool> visited(n+1, false);
  
  for(int i=1; i<=n; i++){
    cin>>a[i];
    parent[i] = i;
  } 
  
  for(int i=0; i<m; i++){
    int x,y;
    cin>>x>>y;
    Union(x,y);
  }
  
  for(int i=1; i<=n; i++){
    int x = getParent(i);
    if(!visited[x]){
      sum += a[x];
      visited[x] = true;
    }
  }
  if(sum <= k) cout<<sum;
  else cout<<"Oh no";
}
