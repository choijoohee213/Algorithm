#include<bits/stdc++.h>
using namespace std;

int main(){
    int k, v, e;
    cin>>k;
    
    while(k--){
        cin>>v>>e;
        vector<int> node[v+1];
        vector<int> color(v+1, 0);
        queue<int> q;
        
        for(int i=0; i<e; i++){
            int a,b;
            cin>>a>>b;
            node[a].push_back(b);
            node[b].push_back(a);
        }

        for(int i=1; i<=v; i++){
            if(color[i]!=0) continue;
            
            q.push(i);
            color[i] = 1;
            
            while(!q.empty()){
                int x = q.front(); q.pop();
                for(int i=0; i<node[x].size(); i++){
                    int nx = node[x][i];
                    if(color[nx] == 0){
                        color[nx] = 3-color[x];
                        q.push(nx);
                    }
                }
            }
        }     
        
        bool isSame = false;
        for(int i=1; i<=v; i++){
            for(int j=0; j<node[i].size(); j++){
                if(color[i] == color[node[i][j]]){
                    isSame = true; 
                    goto stop;
                }
            }
        }  
        
        stop:
        if(isSame) cout<<"NO\n";
        else cout<<"YES\n";  
    }
}
