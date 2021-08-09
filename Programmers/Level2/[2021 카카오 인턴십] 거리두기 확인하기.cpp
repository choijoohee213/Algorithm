#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    int visited[5][5];
    int dx[4] = {0,1,0,-1}, dy[4] = {1,0,-1,0};

    for(int i=0; i<5; i++){
        bool keepDis = true;
        for(int j=0; j<5 && keepDis; j++){
            for(int k=0; k<5 && keepDis; k++){
                if(places[i][j][k] != 'P') continue;
                queue<pair<int,int>> q;
                q.push({j,k});
                memset(visited, -1, sizeof(visited));
                visited[j][k] = 0;
                
                while(!q.empty() && keepDis){
                    int x = q.front().first;
                    int y = q.front().second; q.pop();
                    if(visited[x][y]>=2) continue;
                    
                    for(int t=0; t<4; t++){
                        int nx = x + dx[t];
                        int ny = y + dy[t];
                        
                        if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
                        if(visited[nx][ny]>=0) continue;
                        if(places[i][nx][ny] == 'X') continue;
                        if(places[i][nx][ny] == 'P'){
                            keepDis = false;
                            break;
                        }
                        visited[nx][ny] = visited[x][y] + 1;
                        q.push({nx,ny});
                    }
                } 
            }
        }
        answer.push_back(keepDis);
    }
    return answer;
}
