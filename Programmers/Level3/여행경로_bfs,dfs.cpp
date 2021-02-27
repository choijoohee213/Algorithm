#include <bits/stdc++.h>
using namespace std;

vector<string> answer;
vector<vector<string>> t;

bool findPath(int n, int count, unordered_map<int,int> uom){
    if(count == t.size()-1) return true;
    uom[n] = 1;

    for(int j=0; j<t.size(); j++){
        if(uom[j]!=1 && t[n][1] == t[j][0]){
            if(findPath(j, count+1, uom)){
                answer.push_back(t[j][1]);
                return true;
            }
        }
    }
    return false;
}

vector<string> solution(vector<vector<string>> tickets) {
    vector<int> startList;
    unordered_map<int,int> uom;
    t = tickets;
    sort(t.begin(), t.end());
    
    for(int i=0; i<t.size(); i++)
        if(t[i][0] == "ICN") startList.push_back(i); 
    
    for(int i : startList){
        string end = t[i][1];
        if(findPath(i, 0, uom)){
            answer.push_back(end);
            answer.push_back(t[i][0]);
            reverse(answer.begin(), answer.end());
            return answer;
        }
        answer.clear();
    }
    return answer;   
}
