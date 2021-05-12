#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int m=12, n=6, result=0;
  char arr[12][6];
  bool visited[12][6];
  queue<pair<int,int>> q;
  int dx[4] = {0, 1, 0, -1}, dy[4] = {1,0,-1,0};
  
  for(int i=0; i<m; i++){
    string s;
    cin>>s;
    for(int j=0; j<n; j++){
      arr[i][j] = s[j];
    }
  }

  while(1){
    bool bomb = false;
    memset(visited, false, sizeof(visited));
    
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(arr[i][j]=='.' || visited[i][j]) continue;
        vector<pair<int,int>> v;
        q.push({i,j});
        visited[i][j] = true;
        v.push_back({i,j});
        
        while(!q.empty()){
          int x = q.front().first;
          int y = q.front().second; q.pop();
          
          for(int k=0; k<4; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];
            
            if(nx<0 || nx>=m || ny<0 || ny>=n) continue;
            if(visited[nx][ny] || arr[nx][ny]!=arr[i][j]) continue;
            q.push({nx,ny});
            visited[nx][ny] = true;
            v.push_back({nx,ny});
          }
        }
        if(v.size()>=4){
          bomb = true;
          for(int k=0; k<v.size(); k++)
            arr[v[k].first][v[k].second] = '.';
        }
      }
    }

    if(!bomb){
      cout<<result; 
      return 0;
    }
    else result++;

    //¸Ê Á¤¸®
    for(int i=0; i<n; i++){
      bool blank = false;
      int cnt = 0;
      for(int j=m-1; j>=0; j--){
        if(arr[j][i] == '.'){
          blank = true;
          cnt++;
        }
        else if(blank && arr[j][i] != '.'){
          arr[j+cnt][i] = arr[j][i];
          arr[j][i] = '.';
        }
      }
    }
  }
}
