#include<bits/stdc++.h>
#define MAX 100001
using namespace std;

int d[MAX];
bool visited[MAX];
bool done[MAX];
int cnt = 0;

void dfs(int x){
  visited[x] = true;
  int nx = d[x];
  
  if(!visited[nx])
    dfs(nx);
  else if(!done[nx]){
    for(int i=nx; i!=x; i=d[i]){
      cnt++;
    }
    cnt++;
  }
  done[x] = true;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t;
  cin>>t;
  
  while(t--){
    int n;
    cnt = 0;
    cin>>n;
    
    memset(d, 0, sizeof(d));
    memset(visited, false, sizeof(visited));
    memset(done, false, sizeof(done));
    
    for(int i=1; i<=n; i++){
      cin>>d[i];
    }
    
    for(int i=1; i<=n; i++){
      if(!visited[i]){
        dfs(i);
      }
    }
    cout<<n-cnt<<'\n';
  }
}
