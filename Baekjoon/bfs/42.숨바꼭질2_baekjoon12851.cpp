#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,k,time=10000000000,cnt=0;
  cin>>n>>k;
  queue<pair<int,int>> q;
  int visited[100001] = {0,};
  
  q.push({n,0});
  visited[n] = 1;
  
  while(!q.empty()){
    int x = q.front().first;
    int y = q.front().second; q.pop();
    
    if(time < y) continue;
    
    visited[x] = 1;

    if(x == k){
      time = min(time, y);
      cnt++;
      continue;
    }
    
    for(int nx : {2*x, x-1, x+1}){
      if(nx<0 || nx>100000 || visited[nx]!=0) continue;    
      q.push({nx, y+1});
    }
  }
  cout<<time<<'\n'<<cnt<<endl;
}

