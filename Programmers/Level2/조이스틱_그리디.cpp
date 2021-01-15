#include <string>

using namespace std;

int solution(string name) {
    int answer = 0, cursor = 0, right = 0, left = 0, i = 1;
    string str = "AAAAAAAAAAAAAAAAAAAAA";
    str = str.substr(0,name.size());

    while(str != name){
        right = name[cursor] - 65;
        left = (-1) * (right - 26);
        answer += right<left? right: left;
        str[cursor] = name[cursor]; 

        for(i=1; i<str.size(), str != name; i++){
            right = cursor + i;
            left = (cursor - i) == -1 ? str.size()-1 : cursor-i;
            if(str[right] != name[right]){
                cursor = right;
                answer += i;
                break;
            }
            else if(str[left] != name[left]){
                cursor = left;
                answer += i;
                break;
            }
        }
    }
    return answer;
}
