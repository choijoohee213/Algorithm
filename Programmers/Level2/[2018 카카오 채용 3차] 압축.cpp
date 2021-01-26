#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> solution(string msg) {
    vector<int> answer;
    unordered_map<string,int> dictionary;
    int i=0, j=0, n=0, c=1;
    string wc = "";
    
    for(; c<=26; c++){
        wc += c-1+'A';
        dictionary[wc] = c;
        wc = "";
    }

    while(i<msg.size()){
        string w = "";
        for(j=i; j<msg.size(); j++){
            wc = w + msg[j];
            n = dictionary[wc];
            if(n == 0){ //사전에 없음
                answer.push_back(dictionary[w]);
                dictionary[wc] = c++;
                break;
            }
            else w = wc;
        }
        if(j == msg.size()){
            answer.push_back(dictionary[w]);
            dictionary[wc] = c++;
        }
        i = j;
    }
    return answer;
}
