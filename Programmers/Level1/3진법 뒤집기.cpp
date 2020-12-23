#include <cmath>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    vector<int> v;

    while(n){
        v.push_back(n % 3);
        n /= 3;
    }
    
    for(int i=v.size()-1, j=0; i>=0; i--, j++){
        if(v[i] == 0) continue;
        answer += v[i] * (pow(3, j));
    }
    
    return answer;
}
