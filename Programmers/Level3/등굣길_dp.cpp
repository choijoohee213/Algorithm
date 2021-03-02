#include <bits/stdc++.h>
using namespace std;

int solution(int m, int n, vector<vector<int>> puddles) {
    vector<vector<int>> routes(n+1, vector<int>(m+1, 1));
    
    for(int i = 0; i < puddles.size(); i++) {
        int x = puddles[i][1];
        int y = puddles[i][0];
        routes[x][y] = 0;

        if(y == 1) {
            for(int j = x; j <=n; j++)
                routes[j][1] = 0;
        }

        if(x == 1) {
            for(int j = y; j <= m; j++)
                routes[1][j] = 0;
        }
    }
    
    for(int x=2; x<=n; x++){
        for(int y=2; y<=m; y++){
            if(routes[x][y] == 0) continue;
            routes[x][y] = (routes[x-1][y] + routes[x][y-1]) % 1000000007;
        }
    }
    
    return routes[n][m];
}
