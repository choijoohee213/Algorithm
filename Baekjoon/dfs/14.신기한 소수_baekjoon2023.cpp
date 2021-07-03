#include<bits/stdc++.h>
using namespace std;

int n;

bool isPrime(int num){
  for(int i=2; i<=sqrt(num); i++){
    if(num % i == 0)
      return false;
  }
  return true;
}

void dfs(int x, int len){
  if(len == n){
    cout<<x<<'\n';
    return;
  }
  
  for(int i=1; i<=9; i++){
    if(isPrime(x * 10 + i))
      dfs(x * 10 + i, len+1);
  }
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  dfs(2, 1);
  dfs(3, 1);
  dfs(5, 1);
  dfs(7, 1);
}
