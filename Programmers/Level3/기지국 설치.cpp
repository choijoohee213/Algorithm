#include <bits/stdc++.h>
using namespace std;

int solution(int n, vector<int> stations, int w)
{
    int cnt = 0, s = 1, idx = 0;
    
    while(s <= n){
        if(idx<stations.size() && s >= stations[idx] - w && s <= stations[idx] + w){
            s = stations[idx] + w;
            idx++;
        }
        else {
            s += w * 2;
            cnt++;
        }
        s++;
    }
    return cnt;
}
