#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m;
  cin>>n>>m;
  vector<vector<int>> d(n+1, vector<int>(n+1, INT_MAX));
  
  for(int i=0; i<m; i++){
    int a,b,c;
    cin>>a>>b>>c;
    if(d[a][b] > c) d[a][b] = c;  
  }
  
  for(int k=1; k<=n; k++){
    for(int i=1; i<=n; i++){
      for(int j=1; j<=n; j++){
        if(d[i][k] != INT_MAX && d[k][j] != INT_MAX)
          d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
      }
    }
  }
  
  for(int i=1; i<=n; i++){
    for(int j=1; j<=n; j++){
      if(d[i][j] == INT_MAX || i == j) cout<<0<<' ';
      else cout<<d[i][j]<<' ';
    }
    cout<<'\n';
  }
}
