#include <bits/stdc++.h>
using namespace std;

int n,m,bsize;

bool checkKey(int x, int y, vector<vector<int>>& key, vector<vector<int>> board){
    for(int i=x; i<x+m; i++){
        for(int j=y; j<y+m; j++)
            board[i][j] += key[i-x][j-y];
    }
    
    for(int i=m-1; i<m-1+n; i++){
        for(int j=m-1; j<m-1+n; j++){
            if(board[i][j] == 2 || board[i][j] == 0) 
                return false;
        }
    }
    return true;
}

void rotate(vector<vector<int>>& key){
    vector<vector<int>> newKey(m, vector<int>(m, 0));
    
    for(int i=0; i<m; i++){
        for(int j=0; j<m; j++)
            newKey[j][m - i - 1] = key[i][j];  
    }
    key = newKey;
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    n = lock.size(); 
    m = key.size();
    bsize = n + ((m-1) * 2);
    vector<vector<int>> board(bsize, vector<int>(bsize, 0));
    
    for(int i=m-1; i<m-1+n; i++){
        for(int j=m-1; j<m-1+n; j++)
            board[i][j] = lock[i-(m-1)][j-(m-1)];
    }
    
    for(int r=0; r<4; r++){
        for(int i=0; i<=bsize-m; i++){
            for(int j=0; j<=bsize-m; j++){
                if(checkKey(i,j,key,board)) return true;
            }   
        }
        rotate(key);
    }
    
    return false;
}
