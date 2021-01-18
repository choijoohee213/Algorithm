#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(vector<int> a, vector<int> b){
    return a.size()<b.size();
}

vector<int> solution(string s) {
    vector<int> answer;
    vector<vector<int>> v1;
    vector<int> v2;
    int d[100001] = {0,};
    int i = 1, j = 0, n = 0;
    
    while(i<s.size()){
        if(s[i++] == '{'){
            string str = "";
            while(s[i] != '}'){
                if(s[i] == ','){
                    v2.push_back(stoi(str));
                    str = "";
                }
                else str += s[i];
                i++;
            }
            v2.push_back(stoi(str));
            v1.push_back(v2);
            v2.clear();
        }
    }
    
    sort(v1.begin(), v1.end(), compare);
    
    for(i=0; i<v1.size(); i++){
        for(j=0; j<v1[i].size(); j++){
            n = v1[i][j];
            if(!d[n]){
                answer.push_back(n);
                d[n] = 1;
                break;
            }
        }
    }
    return answer;
}
