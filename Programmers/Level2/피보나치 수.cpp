#include <vector>
#include <iostream>
using namespace std;

int dp[100001] = {0,};

int fibonachi(int n){
    if(n==0 || n==1) return n;
    if(dp[n] != 0) return dp[n];
    return dp[n] = fibonachi(n-2) % 1234567 + fibonachi(n-1) % 1234567;
}

int solution(int n) {
    int answer = 0;
    answer = fibonachi(n) % 1234567;
    return answer;
}
