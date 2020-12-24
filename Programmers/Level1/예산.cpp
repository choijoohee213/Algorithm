#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<int> d, int budget) {
    int answer = 0, i = 0;
    sort(d.begin(), d.end());
    while(i!=d.size() && (budget -= d[i]) >= 0){
        i++;
        answer++;
    }
    return answer;
}
