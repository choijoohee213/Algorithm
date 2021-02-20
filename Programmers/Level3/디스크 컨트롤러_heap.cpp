#include <bits/stdc++.h>
using namespace std;

struct compare {
    bool operator()(vector<int>& a, vector<int>& b) {
        return a[1] > b[1];
    }
};

int solution(vector<vector<int>> jobs) {
    int answer = 0, i=0, curTime = 0;
    priority_queue<vector<int>, vector<vector<int>>, compare> pq;
    sort(jobs.begin(), jobs.end());
    
    while(i<jobs.size() || !pq.empty()){
        if(i<jobs.size() && curTime >= jobs[i][0]){
            pq.push(jobs[i++]);
            continue;
        }
        if(!pq.empty()){
            int waitTime = curTime - pq.top()[0]; 
            curTime += pq.top()[1];
            answer += pq.top()[1] + waitTime;
            pq.pop();
        }
        else curTime = jobs[i][0];
    }
    return answer/jobs.size();
}
