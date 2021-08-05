#include<bits/stdc++.h>
using namespace std;

int n;
vector<long long> node[123457];
long long animal[123457];

long long dfs(int num){
  long long result = 0;

  for(int i=0; i<node[num].size(); i++)
    result += dfs(node[num][i]);
    
  result += animal[num];
  if(result<0) result = 0;
  return result;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  for(int i=2; i<=n; i++){
    char c;
    long long a,b;
    cin>>c>>a>>b;
    if(c == 'W') a *= -1;
    animal[i] = a;
    node[b].push_back(i);
  }  
  cout<< dfs(1);
}
