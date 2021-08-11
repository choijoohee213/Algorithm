#include<bits/stdc++.h>
using namespace std;

int t,n,k,w;
int ntime[1001];
vector<int> node[1001];
int entry[1001];
int result[1001];

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>t;
  while(t--){
    memset(result, 0, sizeof(result));
    cin>>n>>k;
    for(int i=1; i<=n; i++){
      cin>>ntime[i];
      node[i].clear();
    }
    for(int i=0; i<k; i++){
      int x,y;
      cin>>x>>y;
      node[x].push_back(y);
      entry[y]++;
    }
    cin>>w;
    
    queue<int> q;
    for(int i=1; i<=n; i++){
      if(entry[i] == 0){
        q.push(i);
        result[i] = ntime[i];
      }
    }
    
    while(!q.empty()){
      int x = q.front(); q.pop();
      
      for(int i=0; i<node[x].size(); i++){
        int nx = node[x][i];
        result[nx] = max(result[nx], result[x] + ntime[nx]);
        entry[nx]--;
        if(entry[nx] == 0) q.push(nx);
      }
    }
    cout<<result[w]<<endl;
  }
}
