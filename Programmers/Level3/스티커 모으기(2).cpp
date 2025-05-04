#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> sticker)
{
    int n = sticker.size();
    int result = 0;
    if(n == 1) {
        return sticker[0];
    }
        
    vector<int> dp(n);
    dp[0] = dp[1] = sticker[0];

    for(int i=2; i<n-1; i++) {
        dp[i] = max(dp[i-1], dp[i-2] + sticker[i]);
    }

    result = max(result, dp[n-2]);

    fill(dp.begin(), dp.end(), 0);
    dp[1] = sticker[1];

    for(int i=2; i<n; i++) {
        dp[i] = max(dp[i-1], dp[i-2] + sticker[i]);
    }
    result = max(result, dp[n-1]);

    return result;
}