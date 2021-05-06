#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,k;
  cin>>n>>k;
  if(n==k) {cout<<0; return 0;}
  queue<int> q;
  int d[100001] = {0,};

  d[n] = 1;
  q.push(n);
  
  while(d[k]==0){
    int x = q.front(); q.pop();
    
    for(int nx : {2*x, x-1, x+1}){
      if(nx<0 || nx>100000 || d[nx]!=0) continue;
      d[nx] = nx == 2*x? d[x] : d[x] + 1;
      q.push(nx);
    }
  }
  cout<<d[k]-1;
}
