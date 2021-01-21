#include <string>
using namespace std;

string solution(string s) {
    bool isFirst = true;
    for(int i=0; i<s.size(); i++){
        if(isFirst){
            if(s[i] >= 'a' && s[i] <= 'z') s[i] = s[i]-'a'+'A';
            isFirst = false;
        }
        else if(!isFirst && s[i] == ' ' && s[i+1] !=' ') isFirst = true;
        else if(!isFirst && s[i] >= 'A' && s[i] <= 'Z') s[i] = s[i]-'A'+'a';
    }
    return s;
}
