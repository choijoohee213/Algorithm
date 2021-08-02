#include<bits/stdc++.h>
using namespace std;

vector<int> heavy[100];
vector<int> light[100];
bool visited[2][100];
int result = 0;

int dfs(int num, vector<int> v[100], int row){
  int x = 1;
  
  for(int i=0; i<v[num].size(); i++){
    if(!visited[row][v[num][i]]){
      visited[row][v[num][i]] = true;
      x += dfs(v[num][i], v, row);
    }
  }
  return x;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m;
  cin>>n>>m;
  
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    heavy[a].push_back(b);
    light[b].push_back(a);
  }
  
  for(int i=1; i<=n; i++){
    memset(visited, false, sizeof(visited));
    visited[0][i] = visited[1][i] = true;
    
    int h = dfs(i, heavy, 0);
    int l = dfs(i, light, 1);
    
    if(h > (n+1)/2 || l > (n+1)/2)
      result++;
  }
  cout<<result;
}
