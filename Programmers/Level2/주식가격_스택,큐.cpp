#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> answer;
    int n = prices.size(), prev = 0;
    for(int i=0; i<n; i++){
        int count = 0;
        for(int j=i+1; j<n; j++){
            count++; 
            if(prices[i] > prices[j]) break;
        }
        answer.push_back(count);
    }
    return answer;
}
