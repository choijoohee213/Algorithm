#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0, first = 0, second = 0;
    priority_queue<int, vector<int>, greater<> > pq(scoville.begin(), scoville.end());

    while(pq.top() < K){
        if(pq.size() == 1) return answer = -1;
        first = pq.top(); 
        pq.pop();
        second = pq.top(); 
        pq.pop();
        pq.push(first + second * 2);
        answer++;
    }
    return answer;
}
