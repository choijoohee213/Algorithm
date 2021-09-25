#include<bits/stdc++.h>
using namespace std;

int parent[51] = {0,};

int getParent(int x){
  if(parent[x] == x) return x;
  else return parent[x] = getParent(parent[x]);
}

bool isSameParent(int x, int y){
  return getParent(x) == getParent(y);
}

void Union(int x, int y){
  x = getParent(x);
  y = getParent(y);
  if(x<y) parent[y] = x;
  else if(x>y) parent[x] = y;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m,k;
  cin>>n>>m>>k;
  if(k == 0) {
    cout<<m;
    return 0;
  }
  
  for(int i=1; i<=n; i++) parent[i] = i;
  int ms = 1;
  vector<int> people;
  for(int i=0; i<k; i++){
    int x;
    cin>>x;
    people.push_back(x);
  }
  
  vector<int> v[m];
  for(int i=0; i<m; i++){
    int x,y;
    cin>>x;
     
    for(int j=0; j<x; j++){
      if(j == 0) cin>>y;
      else{
        ms = y;
        cin>>y;
        Union(ms, y);
      }
      v[i].push_back(y);
    } 
  }
  
  int cnt = 0;
  for(int i=0; i<m; i++){
    bool flag = false;
    for(int j=0; j<v[i].size() && !flag; j++){
      for(int t = 0; t<k; t++){
        if(isSameParent(v[i][j], people[t])){
          flag = true;
          break;
        }
      }
    }
    if(flag) cnt++;
  }
  cout<<m-cnt;
}
