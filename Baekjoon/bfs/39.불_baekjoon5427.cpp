#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int dx[4] = {0, 1, 0, -1}, dy[4] = {1, 0, -1, 0};
  int t,w,h;
  cin>>t;
   
  while(t--){
    cin>>h>>w;
    vector<vector<char>> map(w, vector<char>(h));
    vector<vector<int>> visitP(w, vector<int>(h, 0));
    queue<pair<int,int>> qP;
    queue<pair<int,int>> qF;
    
    for(int i=0; i<w; i++){
      for(int j=0; j<h; j++){
        cin>>map[i][j];
        if(map[i][j]=='@') {
          qP.push({i,j});
          visitP[i][j] = 1;
          map[i][j] = '.';
        }
        else if(map[i][j]=='*'){
          qF.push({i,j});
        } 
      }
    }
    
    bool escape = false;
    while(!escape && !qP.empty()){
      int fire = qF.size();
      //불 이동 
      while(fire--){
        int x = qF.front().first;
        int y = qF.front().second; qF.pop();
        
        for(int i=0; i<4; i++){
          int nx = x + dx[i];
          int ny = y + dy[i];
          
          if(nx<0 || nx>=w || ny<0 || ny>=h) continue;
          if(map[nx][ny]!='.') continue;
          qF.push({nx,ny});
          map[nx][ny] = '*';
        }  
      }   
    
      //사람 이동 
      int people = qP.size();
      while(!escape && people--){
        int x = qP.front().first;
        int y = qP.front().second; qP.pop();
        if(x==0 || x==w-1 || y==0 || y==h-1){
            cout<<visitP[x][y]<<'\n';
            escape = true;
            break;
        }
  
        for(int i=0; i<4; i++){
          int nx = x + dx[i];
          int ny = y + dy[i];
          
          if(nx<0 || nx>=w || ny<0 || ny>=h) continue;
          if(map[nx][ny]!='.') continue;
          if(visitP[nx][ny] != 0) continue;
          if(nx==0 || nx==w-1 || ny==0 || ny==h-1){
            cout<<visitP[x][y] + 1<<'\n';
            escape = true;
            break;
          }
          qP.push({nx,ny});
          visitP[nx][ny] = visitP[x][y] + 1;
        }
      }     
    }
    
    if(!escape) cout<<"IMPOSSIBLE\n";
  }
}
