#include <bits/stdc++.h>
using namespace std;
#define INF 987654321

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    long long answer = 0;
    vector<vector<int>> arr(n+1, vector<int>(n+1, INF));
    
    for(int i=0; i<fares.size(); i++){
        if(arr[fares[i][0]][fares[i][1]] > fares[i][2]){
            arr[fares[i][0]][fares[i][1]] = fares[i][2];
            arr[fares[i][1]][fares[i][0]] = fares[i][2];
        }
    }
    
    for(int k=1; k<=n; k++){
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(arr[i][k] == INF || arr[k][j] == INF) continue;
                arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j]);
            }
        }
    }
    long long r1 = arr[s][a] + arr[a][b];
    long long r2 = arr[s][b] + arr[b][a];
    long long r3 = arr[s][a] + arr[s][b];
    answer = min(min(r1,r2),r3);
    
    for(int i=1; i<=n; i++){
        if(i == s || i == a || i == b) continue;
        answer = min(answer, (long long)(arr[s][i] + arr[i][a] + arr[i][b]));
    }
    return answer;
}
