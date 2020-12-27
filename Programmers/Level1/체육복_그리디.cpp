//https://codingwell.tistory.com/25

#include <algorithm>
#include <vector>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = n - lost.size();
    
    vector<int>::iterator it;
    
    for(int i=0; i<lost.size(); i++){
        int num = lost[i];
        it = find(reserve.begin(), reserve.end(), num);
        if(it != reserve.end()){
            reserve.erase(it);
            lost[i] = 0;
            answer++;
        }
    }
    
    
    
    for(int i=0; i<lost.size(); i++){
        int num = lost[i];
        if(num == 0) continue;
        
        it = find(reserve.begin(), reserve.end(), num-1);
        if(it != reserve.end()){
            reserve.erase(it);
            answer++;
        }
        else{
            it = find(reserve.begin(), reserve.end(), num+1);
            if(it != reserve.end()){
                reserve.erase(it);
                answer++;
            }
        }
    }
    
    return answer;
}
