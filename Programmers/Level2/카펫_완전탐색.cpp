//다시 풀기 

#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    int w = brown + yellow, m, n;
    for(int i=1; i<=w/2; i++){
        if(w % i == 0){
            n = i;
            m = w/n;
            if(m<n) continue;
            
            int t1 = (m-2)*(n-2);
            int t2 = w - t1;
            if(t1 == yellow && t2 == brown){
                answer.push_back(m);
                answer.push_back(n);
                break;
            }
        }
    }
    return answer;
}
