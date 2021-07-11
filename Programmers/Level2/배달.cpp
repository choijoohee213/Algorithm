#include <bits/stdc++.h>
using namespace std;

int solution(int N, vector<vector<int> > road, int K) {
    int visited[51] = {0,};
    vector<pair<int,int>> d[51];
    unordered_set<int> result;
    queue<pair<int,int>> q;
    
    for(int i=0; i<road.size(); i++){
        d[road[i][0]].push_back({road[i][1], road[i][2]});
        d[road[i][1]].push_back({road[i][0], road[i][2]});
    }
    
    for(int i=0; i<d[1].size(); i++)
        q.push({d[1][i].first, d[1][i].second});
    result.insert(1);
    
    while(!q.empty()){
        int node = q.front().first;
        int sum = q.front().second; q.pop();
        
        if(node == 1 || sum > K) continue;
        if(visited[node] == 0 || visited[node] > sum){
            visited[node] = sum;
            result.insert(node);
            for(int i=0; i<d[node].size(); i++)
                q.push({d[node][i].first, sum + d[node][i].second});
        }     
    }
    return result.size();
}
