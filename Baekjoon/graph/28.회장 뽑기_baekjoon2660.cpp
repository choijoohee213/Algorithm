#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  cin>>n;
  vector<int> v[n+1];
  
  while(true){
    int a,b;
    cin>>a>>b;
    if(a == -1 && b == -1) break;
    v[a].push_back(b);
    v[b].push_back(a);
  }
  
  queue<int> q;
  vector<int> result(n+1, 0);
  
  for(int i=1; i<=n; i++){
    vector<int> d(n+1, 0);
    q.push(i);
    d[i] = 1;
    
    while(!q.empty()){
      int x = q.front(); q.pop();
      
      for(int k=0; k<v[x].size(); k++){
        int nx = v[x][k];
        if(d[nx]==0 || (d[nx]!=0 && d[nx]>d[x]+1)){
          d[nx] = d[x]+1;
          q.push(nx);
        } 
        
      }
    }
    int r = 0;
    for(int j=1; j<=n; j++){
      if(d[j] == 0){
        r = 0;
        break;
      }
      r = max(r, d[j]);
    }
    result[i] = r;
  }
  
  int cnt = n+1;
  vector<int> cand[n+1];
  for(int i=1; i<=n; i++){
    if(result[i]==0) continue;
    cnt = min(result[i], cnt);
    cand[result[i]].push_back(i);
  }
  
  cout<<cnt-1<<" "<<cand[cnt].size()<<'\n';
  for(int m : cand[cnt]) cout<<m<<" ";
}
