#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,k;
  cin>>n>>k;
  queue<int> q;
  int d[100001] = {0,};
  bool visited[100001] = {false,};

  q.push(n);
  d[n] = n;
  visited[n] = true;
  if(n==k) { cout<<0<<'\n'<<k; return 0;}
  
  while(!visited[k]){
    int x = q.front(); q.pop();
    
    for(int nx : {2*x, x-1, x+1}){
      if(nx<0 || nx>100000 || visited[nx]) continue;
      q.push(nx);      
      d[nx] = x;
      visited[nx] = true;
    }
  }
  
  stack<int> v;
  v.push(k);
  for(; k!=n; ){
    k = d[k];
    v.push(k);
  }
  cout<<v.size()-1<<'\n';
  while(!v.empty()){
    cout<<v.top()<<" ";
    v.pop();
  }
}
