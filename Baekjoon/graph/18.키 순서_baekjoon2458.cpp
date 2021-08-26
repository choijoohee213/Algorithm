#include<bits/stdc++.h>
using namespace std;
#define INF 987654321

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m,result=0;
  cin>>n>>m;
  vector<vector<int>> v(n+1, vector<int>(n+1, INF));
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    v[a][b] = 1;
  }
  
  for(int k=1; k<=n; k++){
    for(int i=1; i<=n; i++){
      for(int j=1; j<=n; j++)
        v[i][j] = min(v[i][j], v[i][k] + v[k][j]);
    }
  }
  
  for(int i=1; i<=n; i++){
    int cnt = 0;
    for(int j=1; j<=n; j++){
      if(v[i][j] != INF || v[j][i] != INF)
        cnt++;
    }
    if(cnt == n-1) result++;
  }
  cout<<result;
}
