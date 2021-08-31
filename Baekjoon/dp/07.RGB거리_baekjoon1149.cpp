#include<bits/stdc++.h>
using namespace std;

int d[1001][3];
int cost[3];

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  cin>>n;
  d[0][0] = 0;
  d[0][1] = 0;
  d[0][2] = 0;
  
  for(int i=1; i<=n; i++){
    cin>>cost[0]>>cost[1]>>cost[2];
    d[i][0] = min(d[i-1][1], d[i-1][2]) + cost[0];
    d[i][1] = min(d[i-1][0], d[i-1][2]) + cost[1];
    d[i][2] = min(d[i-1][0], d[i-1][1]) + cost[2];
  }
  cout<<min(min(d[n][0], d[n][1]), d[n][2]);
}
