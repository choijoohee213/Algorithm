#include<bits/stdc++.h>
using namespace std;

int n,l,cnt=0;
int road[100][100];
bool bridge[100][100];

bool checkPath(int x, int y, int dirx, int diry){
  int sx = x, sy = y;
  int w = road[x+dirx][y+diry]; 
  
  for(int i=0; i<l; i++){
    x+= dirx; y += diry;
    if(y<0 || y>=n) return false;
    if(w != road[x][y]) return false;
    if(bridge[x][y]) return false;
  }
  
  for(int i=0; i<l; i++){
    sx += dirx;
    sy += diry;
    bridge[sx][sy] = true;
  }
  return true;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>l;
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++)
      cin>>road[i][j];
  }
  
  for(int i=0; i<n; i++){
    bool pass = true;
    for(int j=0; j<n-1; j++){
      int x = abs(road[i][j]-road[i][j+1]); 
      if(x > 1) pass = false;
      else if(x == 1){
        if(road[i][j] > road[i][j+1]) pass = checkPath(i, j, 0, 1);
        else pass = checkPath(i, j+1, 0, -1);
      }
      if(!pass) break;
    }
    if(pass) cnt++;
  }
  
  memset(bridge, false, sizeof(bridge));
  for(int i=0; i<n; i++){
    bool pass = true;
    for(int j=0; j<n-1; j++){
      int x = abs(road[j][i]-road[j+1][i]); 
      if(x > 1) pass = false;
      else if(x == 1){
        if(road[j][i] > road[j+1][i]) pass = checkPath(j, i, 1, 0);
        else pass = checkPath(j+1, i, -1, 0);
      }
      if(!pass) break;
    }
    if(pass) cnt++;
  }
  cout<<cnt;
}
