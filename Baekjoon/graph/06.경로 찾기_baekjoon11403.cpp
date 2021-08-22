#include<bits/stdc++.h>
using namespace std;

int n;
int d[101][101];
bool visited[101];

void dfs(int i){
  for(int j=0; j<n; j++){
    if(d[i][j] == 1 && !visited[j]){
      visited[j] = true;
      dfs(j);
    }
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++){
      cin>>d[i][j];
    }
  }
  
  for(int i=0; i<n; i++){
    memset(visited,false,sizeof(visited));
    dfs(i);
    for(int j = 0; j<n; j++)
      if(visited[j]) d[i][j] = 1;
  }
  
  for(int i=0; i<n; i++){
    for(int j=0; j<n; j++){
      cout<<d[i][j]<<' ';
    } cout<<endl;
  }
}
