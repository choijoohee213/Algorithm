#include <bits/stdc++.h>
using namespace std;

int parent[101];

int getParent(int x) {
    if(x == parent[x]) return x;
    else return parent[x] = getParent(parent[x]);
}

bool isSameParent(int x, int y) {
    return getParent(x) == getParent(y); 
}

void Union(int x, int y) {
    x = getParent(x);
    y = getParent(y);
    if(x < y) {
        parent[y] = x;
    }
    else if(x > y) {
        parent[x] = y;
    }
}

int solution(int n, vector<vector<int>> wires) {
    int answer = n;
    
    for(int i=0; i<wires.size(); i++) {
        for(int j=1; j<=n; j++) {
            parent[j] = j;
        }
        for(int j=0; j<wires.size(); j++) {
            if(i == j) continue;
            int a = wires[j][0], b = wires[j][1];
            if(!isSameParent(a, b)) {
                Union(a,b);
            }
        }
        
        unordered_map<int,int> uom;
        vector<int> result;
        for(int j=1; j<=n; j++) {
            parent[j] = getParent(j);
            uom[parent[j]]++;
        }
        for(pair<int,int> p : uom) {
            result.push_back(p.second);
        }
        answer = min(answer, (abs)(result[0] - result[1]));
    }
    return answer;
}