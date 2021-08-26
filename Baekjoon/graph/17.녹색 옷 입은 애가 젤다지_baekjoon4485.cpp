#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,cnt=0;
  int d[126][126];
  int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};
  
  while(true){
    cnt++;
    cin>>n;
    if(n == 0) return 0;
    vector<vector<int>> v(n, vector<int>(n, INT_MAX));
    vector<vector<bool>> visited(n, vector<bool>(n, false));
      
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++)
        cin>>d[i][j];
    }
    
    priority_queue<pair<int,pair<int,int>>> pq;
    pq.push({-d[0][0], {0, 0}});
    visited[0][0] = true;
    
    while(!pq.empty()){
      int w = -pq.top().first;
      int x = pq.top().second.first;
      int y = pq.top().second.second; pq.pop();
      
      if(x == n-1 && y == n-1){
        cout<<"Problem "<<cnt<<": "<<v[n-1][n-1]<<'\n';
        break;
      }
      
      for(int i=0; i<4; i++){
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny]) continue;
        if(w + d[nx][ny] < v[nx][ny]){
          v[nx][ny] = w + d[nx][ny];
          visited[nx][ny] = true;
          pq.push({-v[nx][ny], {nx, ny}});
        }
      }
    }
  }
}
