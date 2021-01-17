#include <vector>
using namespace std;

int answer = 0, t = 0;

void dfs(vector<int>& numbers, int i, int sum){
    if(i == numbers.size()){
        if(sum == t) answer++;
        return;
    }
    
    int n = numbers[i];
    dfs(numbers, i+1, sum+n);
    dfs(numbers, i+1, sum-n);
}

int solution(vector<int> numbers, int target) {
    t = target;
    dfs(numbers,0,0);
    return answer;
}
