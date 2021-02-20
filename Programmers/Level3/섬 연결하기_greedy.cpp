#include <bits/stdc++.h>
using namespace std;

int d[101] = {0,};

bool compare(vector<int>& a, vector<int>& b) {return a[2]<b[2];}

int getParent(int i){
    if(i == d[i]) return i;
    return d[i] = getParent(d[i]);
}

void unionParent(int i, int j){
    i = getParent(i);
    j = getParent(j);
    if(i<j) d[j] = d[i];
    else d[i] = d[j];
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    for(int i=0; i<n; i++) d[i] = i;
    
    sort(costs.begin(), costs.end(), compare);    
    for(int i=0; i<costs.size(); i++){
        int a = costs[i][0];
        int b = costs[i][1];
        if(getParent(a) != getParent(b)){
            unionParent(a,b);
            answer += costs[i][2];
        }
    }
    return answer;
}
