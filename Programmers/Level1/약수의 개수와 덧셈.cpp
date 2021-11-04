#include <bits/stdc++.h>
using namespace std;

int cal(int x){
    int cnt = 0;
    bool flag = false;
    for(int i=1; i<=sqrt(x); i++){
        if(x%i == 0){
            if(x/i == i) flag = true;
            cnt++;
        }
    }
    cnt *= 2;
    if(flag) cnt--;
    return cnt;
}

int solution(int left, int right) {
    int answer = 0, result;
    for(int i=left; i<=right; i++){
        result = cal(i);
        if(result % 2 == 0) answer += i;
        else answer -= i;
    }
    return answer;
}