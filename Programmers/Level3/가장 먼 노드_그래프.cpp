#include <bits/stdc++.h>
using namespace std;

int solution(int n, vector<vector<int>> edge) {
    int answer = 0, maxValue = 0;
    int d[20001];
    bool checked[20001][20001];
    bool visited[20001];    
    queue<int> q;
    
    sort(edge.begin(),edge.end());
    
    for(int i=0; i<edge.size(); i++){
        int a = edge[i][0]-1;
        int b = edge[i][1]-1;
        checked[a][b] = true;
        checked[b][a] = true;
    }

    q.push(0);
    visited[0] = true;
    
    while(!q.empty()){
        int a = q.front(); q.pop();
        
        for(int i=1; i<n; i++){
            if(checked[a][i] && !visited[i]){
                visited[i] = true;
                d[i] = d[a] + 1;
                maxValue = max(d[i], maxValue);
                q.push(i);
            }
        }
    }
    
    for(int i=0; i<n; i++) 
        if(d[i] == maxValue) answer++;
    return answer;
}
