//https://codingwell.tistory.com/29?category=917768

#include <queue>
#include <vector>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> q;
    for(int i=0; i<progresses.size(); i++){
        if((100 - progresses[i]) % speeds[i] != 0){
            q.push((100 - progresses[i]) / speeds[i] + 1);
        }
        else q.push((100 - progresses[i]) / speeds[i]);
    }
    
    int day = q.front(), count = 1;
    q.pop();
    while(!q.empty()){
        int curDay = q.front();
        q.pop();
        if(day >= curDay)
            count++;
        else {
            answer.push_back(count);
            count = 1;
            day = curDay;
        }
    }
    answer.push_back(count);
    return answer;
}
