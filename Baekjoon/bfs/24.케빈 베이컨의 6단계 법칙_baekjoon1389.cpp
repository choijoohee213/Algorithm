#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    
    int n,m,result,minKB;
    cin>>n>>m;
    result = n*n;
    vector<int> friends[n+1];
    
    for(int i=0; i<m; i++){
        int a,b;
        cin>>a>>b;
        friends[a].push_back(b);
        friends[b].push_back(a);
    }
    
    for(int i=1; i<=n; i++){
        int sum = 0;
        for(int j=1; j<=n; j++){
            if(i == j) continue;
            vector<int> visited(n+1, -1);
            queue<int> q;
            bool finished = false;
            int cnt = 0;
            
            q.push(i);
            visited[i] = 0;
            
            while(!q.empty() && !finished){
                int x = q.front(); q.pop();
                
                for(int k=0; k<friends[x].size(); k++){
                    int nx = friends[x][k];
                    if(nx == j){
                        finished = true;
                        cnt = visited[x] + 1;
                        break;            
                    }
                    
                    if(visited[nx] == -1){
                       q.push(nx);
                       visited[nx] = visited[x] + 1; 
                    }
                }
            }   
            sum += cnt;
        }
        if(result > sum){
            result = sum;
            minKB = i;
        }
    }
    cout<<minKB;
}
