#include <bits/stdc++.h>

using namespace std;

bool comp(pair<int,int> a, pair<int,int> b) {
    return a.second > b.second;
}

int solution(vector<vector<int>> scores) {
    int answer = 0;
    int n = scores.size();
    vector<pair<int, int>> employees;
    bool removed[n];
    fill(removed, removed + n, false);

    for(int i=1; i<n; i++) {
        int a = scores[i][0];
        int b = scores[i][1];
        if(a+b <= scores[0][0] + scores[0][1]) {
            removed[i] = true;
        }
    }

    for(int i=0; i<n; i++) {
        int a = scores[i][0];
        int b = scores[i][1];

        bool flag = (removed[i] != true);
        for(int j=i+1; j<n && flag; j++) {
            int otherA = scores[j][0];
            int otherB = scores[j][1];

            if(otherA < a && otherB < b) {
                removed[j] = true;
            }

            if(a < otherA && b < otherB) {
                flag = false;
                break;
            }
        }

        if(!flag) {
            removed[i] = true;
            if(i == 0) {
                return -1;
            }
            continue;
        }
        employees.push_back({i, a+b});
    }
    
    sort(employees.begin(), employees.end(), comp);

    if(employees.at(0).first == 0) {
        return 1;
    }

    int num = 1;
    int cnt = 0;

    for(int i=1; i<employees.size(); i++) {
        int prevScore = employees.at(i-1).second;
        int curScore = employees.at(i).second;

        if(prevScore > curScore) {
            num += cnt + 1;
            cnt = 0;
        } else if(prevScore == curScore) {
            cnt++;
        }

        if(employees.at(i).first == 0) {
            return num;
        }
    }


    return answer;
}