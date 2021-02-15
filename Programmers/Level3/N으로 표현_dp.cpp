#include <bits/stdc++.h>
using namespace std;

int solution(int N, int number) {
    unordered_set<int> uos[8];   
    int answer = 0;
    
    for(int i=0; i<8; i++){
        answer = answer * 10 + N;
        uos[i].insert(answer);
    }
    if(uos[0].count(number)) return 1;
    
    for(int i=1; i<8; i++){
        for(int j=0; j<i; j++){
            for(auto& x : uos[j]){
                for(auto& y : uos[i-j-1]){
                    uos[i].insert(x+y);
                    uos[i].insert(x-y);
                    uos[i].insert(x*y);
                    if(y!=0) uos[i].insert(x/y);
                }
            }
        }
        if(uos[i].count(number)) return i+1;
    }
    return -1;
}
