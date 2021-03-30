#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    
    int n;
    cin>>n;
    vector<int> node[n+1];
    vector<int> answer(n+1, 0);
    queue<int> q;
    
    for(int i=0; i<n-1; i++){
        int a, b;
        cin>>a>>b;
        node[a].push_back(b);
        node[b].push_back(a);
        if(a == 1){
            q.push(b); 
            answer[b] = 1;
        }
        if(b == 1){
            q.push(a); 
            answer[a] = 1;
        }
    }
    
    while(!q.empty()){
        int x = q.front(); q.pop();
        
        for(int i=0; i<node[x].size(); i++){
            if(answer[node[x][i]] != 0) continue;        
            q.push(node[x][i]);
            answer[node[x][i]] = x;
        }
    }
    
    for(int i=2; i<=n; i++)
        cout<<answer[i]<<'\n';    
}
