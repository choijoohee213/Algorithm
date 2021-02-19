//https://codingwell.tistory.com/54

#include <bits/stdc++.h>
using namespace std;

int d[501][501];

int checkTriangle(vector<vector<int>>& tri, int x, int y){
    if(x == tri.size()-1) return tri[x][y];
    if(d[x][y] != -1) return d[x][y];
    return d[x][y] = max(checkTriangle(tri, x+1,y), checkTriangle(tri, x+1,y+1)) 
        + tri[x][y];
}

int solution(vector<vector<int>> triangle) {
    for(int i=0; i<triangle.size(); i++)
        for(int j=0; j<i+1; j++) d[i][j] = -1;   
    return checkTriangle(triangle,0,0);
}
