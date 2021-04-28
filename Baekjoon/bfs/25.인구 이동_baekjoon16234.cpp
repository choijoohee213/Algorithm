#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int n,l,r,i=0,j=0,result=0;
    cin>>n>>l>>r;
    int country[50][50];
    int visited[50][50];
    int dx[4] = {0, -1, 0, 1}, dy[4] = {1, 0, -1, 0};
    
    for(i=0; i<n; i++){
        for(j=0; j<n; j++)
            cin>>country[i][j];
    }
    
    while(true){
        bool move = false;
        memset(visited, 0, sizeof(visited));
        
        for(i=0; i<n; i++){
            for(j=0; j<n; j++){
                if(!visited[i][j]){
                    vector<pair<int,int>> v;
                    queue<pair<int,int>> q;
                    int sum = country[i][j];
                    
                    visited[i][j] = true;
                    v.push_back(make_pair(i,j));
                    q.push(make_pair(i,j));
                    
                    while(!q.empty()){
                        int x = q.front().first; 
                        int y = q.front().second; q.pop();
                        for(int k=0; k<4; k++){
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            
                            if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                            if(visited[nx][ny]) continue;
                            
                            int diff = abs(country[x][y]-country[nx][ny]);
                            if(diff<l || diff>r) continue;
                            
                            v.push_back(make_pair(nx,ny));
                            q.push(make_pair(nx,ny));
                            visited[nx][ny] = true;
                            sum += country[nx][ny];
                        }
                    }
                    
                    if(v.size() > 1){
                        int people = sum / v.size();
                        for(int k=0; k<v.size(); k++)
                            country[v[k].first][v[k].second] = people;
                        move = true;
                    }
                }
            }
        }
        if(move) result++;
        else break;
    }
    cout<<result;
}
