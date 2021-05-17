#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int l,r,c;
  int dx[6] = {0,0,0,0,1,-1}, dy[6] = {0,1,0,-1,0,0}, dz[6] = {1,0,-1,0,0,0};
  int visited[30][30][30];
  
  while(true){
    cin>>l>>r>>c;
    if(l==0 && r==0 && c==0) return 0;
    vector<vector<vector<char>>> arr(l, vector<vector<char>>(r, vector<char>(c)));
    queue<pair<pair<int,int>,int>> q;
    memset(visited, 0, sizeof(visited));
    
    for(int i=0; i<l; i++){
      for(int j=0; j<r; j++){
        for(int k=0; k<c; k++){
          cin>>arr[i][j][k];
          if(arr[i][j][k] == 'S'){
            q.push({{i,j},k});
            visited[i][j][k] = 1;
          }
        }
      }
    }
    
    bool goal = false;
    while(!q.empty()){
      int x = q.front().first.first;
      int y = q.front().first.second;
      int z = q.front().second; q.pop();
      
      if(arr[x][y][z] == 'E'){
        cout<<"Escaped in "<<visited[x][y][z]-1<<" minute(s).\n";
        goal = true;
        break;
      }
      
      for(int i=0; i<6; i++){
        int nx = x + dx[i];
        int ny = y + dy[i];
        int nz = z + dz[i];
        
        if(nx<0 || nx>=l || ny<0 || ny>=r || nz<0 || nz>=c) continue;
        if(visited[nx][ny][nz]!=0) continue;
        if(arr[nx][ny][nz] == '.' || arr[nx][ny][nz] == 'E'){
          q.push({{nx,ny},nz});
          visited[nx][ny][nz] = visited[x][y][z] + 1;
        }     
      }
    }
    if(!goal) cout<<"Trapped!\n";
  }
}
