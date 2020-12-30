#include <queue>
#include <vector>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 1, totalWeight = truck_weights[0], i = 1, frontIndex = 0;
    queue<int> bridge;
    
    bridge.push(answer + bridge_length);
    
    while(!bridge.empty()){
        answer++;    
        int endtime = bridge.front();
        if(answer == endtime){
            totalWeight -= truck_weights[frontIndex];
            bridge.pop();
            frontIndex++;
        }
        if(i < truck_weights.size() && totalWeight + truck_weights[i] <= weight){
            bridge.push(answer+bridge_length);
            totalWeight += truck_weights[i];
            i++;
        }
    }
    return answer;
}
