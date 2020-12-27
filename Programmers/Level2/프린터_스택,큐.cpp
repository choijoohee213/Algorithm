//https://codingwell.tistory.com/28

#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    queue<int> q;
    int d[10] = {0,};
    
    for(int i=0; i<priorities.size(); i++){
        q.push(i);
        d[priorities[i]]++;
    }
    
    bool complete = true;
    while(!q.empty()){
        complete = true;
        int n = q.front();
        q.pop();
        
        for(int i=priorities[n]+1; i<10; i++){
            if(d[i]!=0){
                complete = false;
                break;
            }
        }
        
        if(!complete){
            q.push(n);
        }
        else{
            answer++;
            d[priorities[n]]--;
            if(n == location) return answer;
        }
    }
}
