#include<bits/stdc++.h>
using namespace std;

int t,n;
int dp[41] = {0,};

int fibonachi(int num){
  if(num == 0 || num == 1) {
    dp[num] = num;
    return num;
  }
  if(dp[num]!=0) return dp[num];
  return dp[num] = fibonachi(num-1) + fibonachi(num-2);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>t;
  
  while(t--){
    cin>>n;
    if(n == 0) cout<<"1 0"<<'\n';
    else if(n == 1) cout<<"0 1"<<'\n';
    else cout<<fibonachi(n-1)<<" "<<fibonachi(n)<<'\n';
    memset(dp, 0, sizeof(dp));
  }
}
