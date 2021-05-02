#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int f,s,g,u,d; //s¿¡¼­ gÃþÀ¸·Î 
    cin>>f>>s>>g>>u>>d;
    vector<bool> visited(f+1, false);
    vector<int> v(f+1, 0);
    queue<int> q;
    
    q.push(s);
    visited[s] = true;
    
    while(!q.empty()){
        int x = q.front(); q.pop();
        
        if(x == g){
            cout<<v[x];
            return 0;
        }
        if(x+u<=f && !visited[x+u]){
            q.push(x+u);
            v[x+u] = v[x] + 1;
            visited[x+u] = true;
        }
        if(x-d>0 && !visited[x-d]){
            q.push(x-d);
            v[x-d] = v[x] + 1;
            visited[x-d] = true;
        }
    }
    cout<<"use the stairs";
}
