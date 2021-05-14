#include<bits/stdc++.h>
using namespace std;

int arr[16][16] = {0,};
int dx[3] = {0, -1, 0}, dy[4] = {-1, 0, 1};
int archer[15] = {0,};
int n,m,d,row,result=0;
int visited[16][15] = {0,};

void bfs(){
  int cnt = 0;
  int arr2[16][16];
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++)
      arr2[i][j] = arr[i][j];
  }

  while(row>0){
    vector<pair<int,int>> v;
  
    for(int i=0; i<m; i++){
      if(archer[i] == 0) continue;
      bool kill = false;
      memset(visited, 0, sizeof(visited));
      queue<pair<int,int>> q;
      q.push({row, i});
      visited[row][i] = 0;
      
      while(!q.empty() && !kill){
        int x = q.front().first;
        int y = q.front().second; q.pop();
      
        for(int j=0; j<3; j++){
          int nx = x + dx[j];
          int ny = y + dy[j];
          
          if(nx<0 || nx>n || ny<0 || ny>=m) continue;
          if(visited[nx][ny]!=0) continue;
          
          visited[nx][ny] = visited[x][y] + 1;
          if(visited[nx][ny] > d) continue;
          if(arr2[nx][ny] == 1 && row!=nx){
            v.push_back({nx,ny});
            kill = true;
            break;
          }
          q.push({nx,ny});
        }
      }   
    }
    v.erase(unique(v.begin(), v.end()), v.end());
    for(int i=0; i<v.size(); i++){
      arr2[v[i].first][v[i].second] = 0;
      cnt++;
    }
    row--;
  }
  result = max(result, cnt);
}


void selectArcher(int index, int cnt){
  if(cnt == 3){
    bfs();
    row = n;
    return;
  }
  for(int i=index; i<m; i++){
    if(archer[i]==0){
      archer[i] = 1;
      selectArcher(i+1, cnt+1);
      archer[i] = 0;
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m>>d;
  
  for(int i=0; i<n; i++){
    for(int j=0; j<m; j++)
      cin>>arr[i][j];
  }
  row = n;
  selectArcher(0,0);
  cout<<result;
}
