#include <bits/stdc++.h>
using namespace std;

int MOD = 20170805;

int solution(int m, int n, vector<vector<int>> city_map) {
    vector<vector<int>> right(m+1, vector<int>(n+1, 0));
    vector<vector<int>> down(m+1, vector<int>(n+1, 0));
    
    for(int i=1; i<=m; i++){
        for(int j=1; j<=n; j++){
            int type = city_map[i-1][j-1];
            if(i==1 && j==1){
                right[i][j] = 1;
                down[i][j] = 1;
            }
            else if(type == 0) {
                right[i][j] = (down[i-1][j] + right[i][j-1]) % MOD;
                down[i][j] = right[i][j];
            }
            else if(type == 2){
                right[i][j] = right[i][j-1];
                down[i][j] = down[i-1][j];
            }
        }
    }
    return right[m][n] % MOD;
}
