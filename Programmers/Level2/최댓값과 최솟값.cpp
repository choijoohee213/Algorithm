#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string s) {
    string answer = "";
    vector<int> v;
    for(int i=0; i<s.size(); i++){
        if(s[i] == ' '){ 
            v.push_back(stoi(answer));
            answer = "";
        }
        else answer += s[i];
    }
    v.push_back(stoi(answer));
    
    sort(v.begin(), v.end());
    answer = to_string(v[0]) + " ";
    answer += to_string(v[v.size()-1]);
    return answer;
}
