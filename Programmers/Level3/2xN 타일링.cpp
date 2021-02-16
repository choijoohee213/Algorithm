#include <bits/stdc++.h>
using namespace std;

int dp[60001];

int checkTile(int k){
    if(k<=3) return k;
    if(dp[k]!=0) return dp[k];
    dp[k] = (checkTile(k-1)+checkTile(k-2)) % 1000000007;
    
    return dp[k];
}

int solution(int n) {
    return checkTile(n);
}
