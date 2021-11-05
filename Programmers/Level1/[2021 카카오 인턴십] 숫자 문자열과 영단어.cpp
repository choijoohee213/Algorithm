#include <bits/stdc++.h>
using namespace std;

unordered_map<string, char> uom = {{"zero", '0'}, {"one", '1'}, {"two", '2'}, {"three", '3'}, {"four", '4'},
                                  {"five", '5'}, {"six", '6'}, {"seven", '7'}, {"eight", '8'}, {"nine", '9'}, {"ten", '10'}};

int solution(string s) {
    string str = "", result = "";
    for(int i=0; i<s.size(); i++){
        if(s[i]>='a' && s[i]<='z'){
            str += s[i];
            if(uom[str]>0){
                result += uom[str];
                str = "";
            }
        }
        else result += s[i];
    }
    return stoi(result);
}