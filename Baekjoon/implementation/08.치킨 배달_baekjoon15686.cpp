#include<bits/stdc++.h>
using namespace std;
typedef pair<int,int> p;

int n,m, result_min = INT_MAX;
vector<p> home;
vector<p> chicken;
vector<int> result;

void dfs(int index, int cnt) {
    if(cnt >= m) return;
    result.push_back(index);
    for(int i=index; i<chicken.size(); i++) {
        dfs(i, cnt+1);
    }
}

void calDistance() {
    int sum = 0;

    for(int i=0; i<home.size(); i++) {
        int x = home[i].first;
        int y = home[i].second;
        int minDistance = n*n*n;

        for(int j=0; j<result.size(); j++) {
            minDistance = min(minDistance, (abs)(chicken[result[j]].first - x) + (abs)(chicken[result[j]].second - y));
        }
        sum += minDistance;
    }
    result_min = min(result_min, sum);
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

    for(int i=0; i<chicken.size(); i++) {
        result.clear();
        dfs(i, 0);
        calDistance();
    }
    cout<<result_min;
}