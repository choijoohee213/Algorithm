#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(int n) {
    string answer = "";
    int d[3] = {1, 2, 4};
    while(n > 2){
        n--; 
        answer += to_string(d[n % 3]);
        n = n/3;
    }
    answer += to_string(n); 
    reverse(answer.begin(), answer.end());
    
    while(answer[0] == '0'){
        answer.erase(0,1);
    }
    
    return answer;
}
