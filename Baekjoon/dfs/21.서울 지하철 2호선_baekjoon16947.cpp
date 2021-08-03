#include<bits/stdc++.h>
using namespace std;

int n, cycleNum;
vector<int> v[3001];
bool visited[3001] = {false,};
bool cycle[3001] = {false,};
int result[3001] = {0,};

bool dfs(int num, int post){
  if(visited[num]) {
    cycleNum = num;
    return false;
  }
  visited[num] = true;
  
  for(int i=0; i<v[num].size(); i++){
    if(v[num][i] == post || cycle[v[num][i]]) continue;
    if(!dfs(v[num][i], num)){
      cycle[num] = true;
      if(cycleNum == num) return true;
      else return false;
    }
  }
  return true;
}

void bfs(){
  queue<int> q;
  memset(visited, false, sizeof(visited));
  for(int i=1; i<=n; i++){
    if(cycle[i])
      q.push(i);
  }
  
  while(!q.empty()){
    int x = q.front(); q.pop();

    for(int i=0; i<v[x].size(); i++){
      if(visited[v[x][i]] || cycle[v[x][i]]) continue;
      
      q.push(v[x][i]);
      visited[v[x][i]] = true;
      result[v[x][i]] = result[x] + 1;
    }    
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  
  for(int i=0; i<n; i++){
    int a,b;
    cin>>a>>b;
    v[a].push_back(b);
    v[b].push_back(a);
  }
  
  dfs(1,0);
  bfs();
  for(int i=1; i<=n; i++)
    cout<<result[i]<<' ';
}
