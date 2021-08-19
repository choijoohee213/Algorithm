#include<bits/stdc++.h>
using namespace std;

int n,k,s,cnt=0;
vector<int> node[2][401];
bool visited[2][401];

bool bfs(int num, int goal, int dir){
  queue<int> q;
  q.push(num);
  visited[dir][num] = true;
  
  while(!q.empty()){
    int x = q.front(); q.pop();
    
    for(int i=0; i<node[dir][x].size(); i++){
      int nx = node[dir][x][i];
      if(visited[dir][nx]) continue;
      if(nx == goal) return true;
      visited[dir][nx] = true;
      q.push(nx);
      cnt++;
    }
  }
  return false;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>k;
  for(int i=0; i<k; i++){
    int a,b;
    cin>>a>>b;
    node[0][a].push_back(b);
    node[1][b].push_back(a);
  }
  
  cin>>s;
  for(int i=0; i<s; i++){
    memset(visited, false, sizeof(visited));
    int a,b;
    cin>>a>>b;
    if(bfs(a, b, 0)) cout<<-1<<'\n';
    else if(bfs(a, b, 1)) cout<<1<<'\n';
    else cout<<0<<'\n';
  }
}
