#include <bits/stdc++.h>
using namespace std;

int d[201];
unordered_set<int> uos;

int getParent(int i){
    if(i == d[i]) return i;
    return d[i] = getParent(d[i]);
}

void unionParent(int i, int j){
    i = getParent(i);
    j = getParent(j);
    if(i<j) d[j] = i;
    else d[i] = j;
}

int solution(int n, vector<vector<int>> computers) {
    for(int i=0; i<n; i++) d[i] = i;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            if(i!=j && computers[i][j] && getParent(i)!=getParent(j)){
                unionParent(i,j);
            }
        }
    }
    
    for(int i=0; i<n; i++){
        getParent(i);
        uos.insert(d[i]);
    }
    return uos.size();
}
