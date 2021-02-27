#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer(2,0);
    vector<int> v;
    
    for(int i=0; i<operations.size(); i++){
        string command;
        int data;
        stringstream sst(operations[i]);
        sst>>command;
        sst>>data;
        
        if(command == "I"){
            v.push_back(data);
            sort(v.begin(), v.end());
        }
        else if(command == "D" && !v.empty()){
            if(data == 1)
                v.erase(v.begin()+v.size()-1, v.begin()+v.size());
            else if(data == -1)
                v.erase(v.begin(), v.begin()+1);
        }
    }

    if(!v.empty()){
        answer[0] = v[v.size()-1];
        answer[1] = v[0];
    }

    return answer;
}
