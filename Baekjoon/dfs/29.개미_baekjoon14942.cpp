#include<bits/stdc++.h>
using namespace std;
#define MAX 100001

int n;
int energy[MAX];
vector<pair<int,int>> node[MAX];

void dfs(int num, int e){
  
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  for(int i=1; i<=n; i++)
    cin>>energy[i];
    
  for(int i=0; i<n-1; i++){
    int a,b,c;
    cin>>a>>b>>c;
    node[a].push_back({b,c});
    node[b].push_back({a,c});
  }
  
  for(int i=1; i<=n; i++){
    dfs(i,);
  }
}
