#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    int n,m,a,b;
    cin>>n>>a>>b>>m;
    vector<int> relation[n+1]; 
    vector<int> answer(n+1, 0);
    queue<int> q;
    
    for(int i=0; i<m; i++){
        int x,y;
        cin>>x>>y;
        relation[x].push_back(y);
        relation[y].push_back(x);
    }
    
    q.push(a);
    while(!q.empty()){
        int parent = q.front(); q.pop();
        
        for(int i=0; i<relation[parent].size(); i++){
            int child = relation[parent][i];
            if(answer[child] != 0) continue;
            q.push(child);
            answer[child] = answer[parent] + 1;
        } 
    }    
    
    if(answer[b] != 0) cout<<answer[b];
    else cout<<-1;
}
