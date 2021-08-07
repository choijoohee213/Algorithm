#include<bits/stdc++.h>
using namespace std;
#define MAX 100001

int n,m,x,k=0;
vector<int> ranking[2][MAX];
bool visited[MAX];

int dfs(int num){
  visited[num] = true;
  int r = 1;
  for(int i : ranking[k][num])
    if(!visited[i]) r += dfs(i);
  return r;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m>>x;
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    ranking[0][a].push_back(b);
    ranking[1][b].push_back(a);
  }
  int low = n - dfs(x) + 1;
  k = 1;
  int high = dfs(x);
  cout<<high<<' '<<low;
}
