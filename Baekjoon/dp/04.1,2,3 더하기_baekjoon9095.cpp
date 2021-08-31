#include<bits/stdc++.h>
using namespace std;

int n, cnt = 0;
int d[3] = {1,2,3};

void dfs(int num){
  if(num >= n) {
    if(num == n) cnt++;
    return;
  }
  for(int i=0; i<3; i++)
    dfs(num+d[i]);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int t;
  cin>>t;
  
  while(t--){
    cin>>n;
    dfs(0);
    cout<<cnt<<'\n';
    cnt = 0;
  }
}
