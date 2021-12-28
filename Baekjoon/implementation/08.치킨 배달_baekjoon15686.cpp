#include<bits/stdc++.h>
using namespace std;
typedef pair<int,int> p;

int n,m;
long long result_min = LONG_LONG_MAX;
vector<p> home;
vector<p> chicken;
vector<int> result;
bool selected[15] = {false,};

void calDistance() {
    long long sum = 0;

    for(int i=0; i<home.size(); i++) {
        int x = home[i].first;
        int y = home[i].second;
        int minDistance = INT_MAX;

        for(int j=0; j<result.size(); j++) {
            minDistance = min(minDistance, (abs)(chicken[result[j]].first - x) + (abs)(chicken[result[j]].second - y));
        }
        sum += minDistance;
    }
    result_min = min(result_min, sum);
}

void dfs(int index, int cnt) {
    if(cnt >= m) {
        calDistance();
        return;
    }
    for(int i=index; i<chicken.size(); i++) {
        if(selected[i]) continue;
        selected[i] = true;
        result.push_back(i);
        dfs(i, cnt+1);
        selected[i] = false;
        result.pop_back();
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    cin>>n>>m;
    
    for(int i=0; i<n; i++) {
        for (int j = 0; j < n; j++)
        {
            int x;
            cin>>x;
            if(x == 1) {
                home.push_back({i,j});
            }
            else if(x == 2) {
                chicken.push_back({i,j});
            }
        }
    }
    dfs(0, 0);
    cout<<result_min;
}