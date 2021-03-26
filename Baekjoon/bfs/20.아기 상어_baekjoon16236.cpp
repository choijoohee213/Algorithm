#include<bits/stdc++.h>
using namespace std;
#define MIN 99999999

int n, shark=2, eatDistance=MIN, eatCnt=0, answer=0;
int sea[21][21];
int d[21][21];
vector<pair<int,pair<int,int>>> fishes;
pair<int,int> sharkPos;
const int dx[4] = {-1, 0, 1, 0};
const int dy[4] = {0, -1, 0, 1};
    
void bfs(){
    queue<pair<int,int>> q;
    fishes.clear();
    for(int i=0; i<n; i++) fill(d[i], d[i]+n, 0);
    eatDistance = MIN;
    q.push(make_pair(sharkPos.first, sharkPos.second));
    
    while(!q.empty()){
        pair<int,int> pos = q.front(); q.pop();
        
        for(int i=0; i<4; i++){
            int nx = pos.first + dx[i];
            int ny = pos.second + dy[i];
            
            if(nx>=0 && nx<n && ny>=0 && ny<n 
                && d[nx][ny]==0 && sea[nx][ny]<=shark){
                d[nx][ny] = d[pos.first][pos.second]+1;
                if(sea[nx][ny]>0 && sea[nx][ny]<shark && eatDistance>=d[nx][ny]){
                    eatDistance = d[nx][ny];
                    fishes.push_back(make_pair(eatDistance, make_pair(nx,ny)));
                }
                q.push(make_pair(nx,ny));
            }
        }
    }
}    

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
      
    cin>>n;

   	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
            cin >> sea[i][j];
            if(sea[i][j] == 9) {
               sharkPos = make_pair(i,j);
               sea[i][j] = 0; 
            }
        }
    }
    
    while(1){
        bfs();
        
        if(fishes.empty()) break;
        else{
            sort(fishes.begin(), fishes.end());
            eatCnt++;
            if(eatCnt == shark){
                shark++; 
                eatCnt = 0;
            }
            int x = fishes[0].second.first, y=fishes[0].second.second;
            answer += fishes[0].first;
            sea[x][y] = 0;
            sharkPos.first = x;
            sharkPos.second = y;
        }
    }
    cout<<answer;
}
