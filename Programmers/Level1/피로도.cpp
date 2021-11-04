#include <bits/stdc++.h>
using namespace std;

int solution(int k, vector<vector<int>> dungeons) {
    int n = dungeons.size(), result=0;
    sort(dungeons.begin(), dungeons.end());
    do{
        int hp = k;
        int cnt = 0;
        for(int i=0; i<n; i++){
            if(hp < dungeons[i][0]) break;
            hp -= dungeons[i][1];
            cnt++;
        }
        result = max(result, cnt);
    } while(next_permutation(dungeons.begin(), dungeons.end()));
    return result;
}