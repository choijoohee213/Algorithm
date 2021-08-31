#include<bits/stdc++.h>
using namespace std;

int n;
int dp[21] = {0,};

int fibonachi(int num){
  if(num == 0 || num == 1) return num;
  if(dp[num]!=0) return dp[num];
  
  return dp[num] = fibonachi(num-1) + fibonachi(num-2);
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  cin>>n;
  cout<<fibonachi(n);
}
