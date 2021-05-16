#include<bits/stdc++.h>
using namespace std;

int s;
int d[1002][1002];
queue<pair<int,int>> q;  //화면, 클립보드 

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>s;
  q.push({1, 1});
  memset(d, -1, sizeof(d));
  d[1][0] = 0;
  d[1][1] = 1;
  
  while(!q.empty()){
    int display = q.front().first;
    int clipboard = q.front().second;
    q.pop();
        
    for(int i=0; i<3; i++){
      int nd, nc;
      if(i==0){
        nd = display;
        nc = display;
      }
      else if(i==1){
        nd = display + clipboard;
        nc = clipboard;
      }
      else{
        nd = display - 1;
        nc = clipboard;
      }
      if(nd < 1 || nd > 1000) continue;
      if(d[nd][nc] > 0) continue;
      d[nd][nc] = d[display][clipboard] + 1;
      q.push({nd,nc});
    }
  }
  
  int result = 1000000;
  for(int i=1; i<1001; i++)
    result = min(result, d[s][i]);
  cout<<result;
}
