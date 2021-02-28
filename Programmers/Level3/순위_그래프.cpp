#include <bits/stdc++.h>
using namespace std;

int solution(int n, vector<vector<int>> results) {
    int answer = 0;
    bool d[101][101] = {false, };
    
    for(int i=0; i<results.size(); i++)
        d[results[i][0]-1][results[i][1]-1] = true;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            for(int k=0; k<n; k++){
                if(d[j][i] && d[i][k])
                    d[j][k] = true;
            }
        }
    }
    
    for(int i=0; i<n; i++){
        int count = 0;
        for(int j=0; j<n; j++)
            if(d[i][j] || d[j][i]) count++;
        if(count == n-1) answer++;
    }
    return answer;
}
