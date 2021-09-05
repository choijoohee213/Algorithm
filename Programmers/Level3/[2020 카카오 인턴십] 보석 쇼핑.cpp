#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<string> gems) {
    vector<int> answer = {0,0};
    unordered_map<string,int> uom;
    unordered_set<string> s(gems.begin(), gems.end());
    int cnt, startN=0, endN=0;
    for(int i=0; i<gems.size(); i++){
        uom[gems[i]]++;
        if(uom.size() == s.size()) break;
        endN++;
    }
    
    cnt = endN-startN;
    answer[0] = startN + 1;
    answer[1] = endN + 1;
    
    while(endN < gems.size()){
        string key = gems[startN];
        uom[key]--;
        startN++;
        
        if(uom[key] == 0){
            endN++;
            for(; endN<gems.size(); endN++){
                uom[gems[endN]]++;
                if(key == gems[endN]) break;
            }
            if(endN == gems.size()) break;
        }
        if(cnt > endN - startN){
            cnt = endN - startN;
            answer[0] = startN + 1;
            answer[1] = endN + 1;
        }
    }
    return answer;
}
