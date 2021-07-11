#include <bits/stdc++.h>
using namespace std;

int calRank(int n){
    if(n>1) return 7-n;
    else return 6;
}

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
    vector<int> answer;
    int dontknow = 0, cnt = 0;
    
    for(int i=0; i<6; i++){
        if(lottos[i] == 0) dontknow++;
        else{
            for(int j=0; j<6; j++){
                if(lottos[i] == win_nums[j]){
                    cnt++;
                    break;
                }
            }
        }
    }
    answer.push_back(calRank(cnt+dontknow));
    answer.push_back(calRank(cnt));
    return answer;
}
