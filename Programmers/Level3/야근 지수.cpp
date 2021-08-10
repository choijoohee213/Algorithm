#include <bits/stdc++.h>
using namespace std;

long long solution(int n, vector<int> works) {
    long long answer = 0;
    priority_queue<int> pq(works.begin(), works.end());

    while(n>0){
        if(pq.top() > 0){
            int x = pq.top();
            pq.pop();
            pq.push(x-1);
            n--;
        }
        else {
            break;
        }
    }
    
    while(!pq.empty()){
        int x = pq.top(); pq.pop();
        answer += x * x;
    }
    return answer;
}
