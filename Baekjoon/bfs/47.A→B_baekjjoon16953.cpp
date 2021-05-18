#include<bits/stdc++.h>
using namespace std;
int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int a,b;
  cin>>a>>b;
  queue<pair<long long int, long long int>> q;

  q.push({a,1});
  
  while(!q.empty()){
    long long int x = q.front().first; 
    long long int cnt = q.front().second; q.pop();
    
    if(x == b){
      cout<<cnt;
      return 0;
    }
    long long int nx = 2 * x;
    if(nx<=b) q.push({nx, cnt+1});
    nx = 10 * x + 1;
    if(nx<=b) q.push({nx, cnt+1});
  }
  cout<<-1; 
}
