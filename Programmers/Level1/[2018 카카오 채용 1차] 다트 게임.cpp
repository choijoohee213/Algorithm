#include <string>
#include <cmath>

using namespace std;

int solution(string dartResult) {
    int answer = 0, prevNum = 0, curNum = 0;
    string s = "";
    for(int i=0; i<dartResult.length(); i++){
        char c = dartResult[i];
        if(c == 'S' || c == 'D' || c == 'T'){
            answer += prevNum;
            prevNum = curNum;
            curNum = stoi(s);
            s = "";
            if(c == 'D') curNum = pow(curNum, 2);
            else if(c == 'T') curNum = pow(curNum, 3);
        }        
        else if(c == '*'){
            prevNum *= 2;
            curNum *= 2;
        }
        else if(c == '#'){
            curNum *= -1;
        }
        else {
            s += c;
        }     
    }
    answer += prevNum + curNum;
    return answer;
}
