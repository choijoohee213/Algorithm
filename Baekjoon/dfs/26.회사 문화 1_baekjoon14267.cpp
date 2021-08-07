#include<bits/stdc++.h>
using namespace std;
#define MAX 100001

int n,m;
vector<int> member[MAX];
int compliment[MAX] = {0,};
int result[MAX] = {0,};

void dfs(int num, int c){
  result[num] += c;
  for(int i=0; i<member[num].size(); i++)
    dfs(member[num][i], c);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n>>m;
  for(int i=1; i<=n; i++){
    int x;
    cin>>x;
    if(x != -1) member[x].push_back(i);
  }
    
  for(int i=0; i<m; i++){
    int a,b;
    cin>>a>>b;
    compliment[a] += b;
  }
  
  for(int i=2; i<=n; i++)
    if(compliment[i]>0) dfs(i, compliment[i]);
  
  for(int i=1; i<=n; i++)
    cout<<result[i]<<' ';
}
