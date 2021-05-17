#include<bits/stdc++.h>
using namespace std;

int arr[3][3];

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
  int ans = -1;
  queue<pair<string,int>> q;
  set<string> check;
  
  string s = "";
  for(int i=0; i<3; i++){
    for(int j=0; j<3; j++){
      char c;
      cin>>c;
      s += c;
    }
  }
  check.insert(s);
  q.push({s,0});

  while(!q.empty()){
    string now = q.front().first;
    int cnt = q.front().second;
    q.pop();
    
    if(now == "123456780" && (ans == -1 || ans > cnt))
      ans = cnt;
    
    int zero = now.find('0');
    int x = zero/3, y = zero%3;
    
    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];
      
      if(nx<0 || nx>=3 || ny<0 || ny>=3) continue;
      string ns = now;
      swap(ns[x*3+y], ns[nx*3+ny]);
      
      if(check.find(ns) == check.end()){
        check.insert(ns);
        q.push({ns,cnt+1});
      }
    }
  }
  cout<<ans;
}

