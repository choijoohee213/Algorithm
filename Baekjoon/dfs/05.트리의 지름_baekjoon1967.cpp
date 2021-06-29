#include<bits/stdc++.h>
using namespace std;

vector<pair<int,int>> node[10002];
bool visited[10002] = {false,};
int result = 0;
int endPoint = 0;

void dfs(int p, int len){
  if(visited[p]) return;
  
  visited[p] = true;
  if(result < len){
    result = len;
    endPoint = p;
  }
  
  for(int i=0; i<node[p].size(); i++){
    dfs(node[p][i].first, len + node[p][i].second);
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n;
  cin>>n;
  
  for(int i=0; i<n-1; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
    node[b].push_back({a,c});
  }
  
  dfs(1,0);  //���� �ָ��ִ� �� ���ϱ�
  
  result = 0;
  memset(visited, false, sizeof(visited));
  
  dfs(endPoint, 0);  //���� ���ϱ�
  cout<<result;
}
