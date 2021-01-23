#include <string>
#include <unordered_map>

using namespace std;

bool isAlphabet(char& c){
    if(c >= 'a' && c <= 'z') c = c-'a'+'A';
    return (c >= 'A' && c <= 'Z');
}

int solution(string str1, string str2) {
    int intersection = 0, m = 0, n = 0;
    unordered_map<string, int> uom;
    string s = "";
    
    for(int i=0; i<str1.size()-1; i++){
        if(isAlphabet(str1[i]) && isAlphabet(str1[i+1])){
            s = str1[i];
            s += str1[i+1];
            m++;
            uom[s]++;
        }
    }
    
    for(int i=0; i<str2.size()-1; i++){
        if(isAlphabet(str2[i]) && isAlphabet(str2[i+1])){
            s = str2[i];
            s += str2[i+1];
            n++;
            if(uom[s] != 0){
                intersection++;
                uom[s]--;
            }
        }
    }
    
    int j = m + n - intersection;
    if(j == 0) return 65536;
    return (int)(intersection * 65536 / j);
}
