#include <string>

using namespace std;

string solution(string s) {
    string answer = "";
    int i = s.length() / 2;
    if(s.length() % 2)
        answer = s[i];
    else {
        answer = s[i-1];
        answer += s[i];
    }
    return answer;
}
