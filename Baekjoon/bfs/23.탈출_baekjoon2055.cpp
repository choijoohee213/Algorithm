#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    
    int r,c,time=-1;
    char forest[51][51];
    int result[51][51] = {0,};
    int dx[4] = {0, 1, 0, -1};
    int dy[4] = {1, 0, -1, 0};
    cin>>r>>c;
    
    queue<pair<int,int>> hq, wq;
    
    for(int i=0; i<r; i++){
        string s;
        cin>>s;
        for(int j=0; j<c; j++){
            forest[i][j] = s[j];    
            if(s[j] == 'S'){
              hq.push(make_pair(i,j));
              forest[i][j] = '.';  
            } 
            else if(s[j] == '*') wq.push(make_pair(i,j)); 
        }
    }
    
    while(!hq.empty()){
        pair<int,int> hp = hq.front(); hq.pop();
        int curTime = result[hp.first][hp.second];
        
        if(time < curTime){
            time = curTime;
            int wqSize = wq.size();
            
            //물 움직이기
            for(int i=0; i<wqSize; i++){
                pair<int,int> wp = wq.front(); wq.pop();
                for(int j=0; j<4; j++){
                    int nx = wp.first + dx[j];
                    int ny = wp.second + dy[j];
                    
                    if(nx>=0 && nx<r && ny>=0 && ny<c
                        && forest[nx][ny] == '.'){
                        forest[nx][ny] = '*';
                        wq.push(make_pair(nx,ny));            
                    }
                }
            }
        }
        
        for(int j=0; j<4; j++){
            int nx = hp.first + dx[j];
            int ny = hp.second + dy[j];
            
            if(nx<0 || nx>=r || ny<0 || ny>=c) continue;
            if(forest[nx][ny] == 'D'){
                cout<<curTime + 1;
                return 0;
            }
            else if(forest[nx][ny] == '.' && result[nx][ny] == 0){
                result[nx][ny] = curTime + 1;
                hq.push(make_pair(nx,ny));   
            }
        }   
    }    
    cout<<"KAKTUS";
}
